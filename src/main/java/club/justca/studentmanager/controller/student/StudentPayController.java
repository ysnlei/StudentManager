package club.justca.studentmanager.controller.student;

import club.justca.studentmanager.VO.AlipayReturn;
import club.justca.studentmanager.config.AlipayConfig;
import club.justca.studentmanager.entity.Account;
import club.justca.studentmanager.entity.ClassFundDetail;
import club.justca.studentmanager.entity.ClassFundList;
import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.service.AccountService;
import club.justca.studentmanager.service.ClassFundDetailService;
import club.justca.studentmanager.service.ClassFundListService;
import club.justca.studentmanager.utils.AlipayUtil;
import com.alipay.api.AlipayApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/student")
public class StudentPayController {
    @Autowired
    AlipayUtil alipayUtil;
    @Autowired
    ClassFundListService classFundListService;
    @Autowired
    AccountService accountService;
    @Autowired
    ClassFundDetailService classFundDetailService;

    @RequestMapping("/pay")
    @ResponseBody
    public String test(Integer classFundListId, HttpSession session) throws AlipayApiException {
        Student student = (Student) session.getAttribute("loginUser");
        Account account = accountService.findByMajor(student.getMajor());
        if (account == null) {
            account = new Account();
            account.setMajor(student.getMajor());
            accountService.insert(student.getMajor());
        }
        String studentNum = student.getStudentNum();
        ClassFundList classFundList = classFundListService.findById(classFundListId);
        String money = classFundList.getMoney().toString();
        String reason = classFundList.getTitle();
        ClassFundDetail classFundDetail = classFundDetailService.findByClassFundListIdAndStudentNum(classFundListId, studentNum);
        if (classFundDetail == null) {
            String uuid = UUID.randomUUID().toString();
            classFundDetail = new ClassFundDetail();
            classFundDetail.setClassFundListId(classFundListId);
            classFundDetail.setStudentNum(studentNum);
            classFundDetail.setOutTradeNo(uuid);
            classFundDetail.setStatus("未支付");
            classFundDetailService.insert(classFundDetail);
            return alipayUtil.pay(
                    uuid,
                    money,
                    reason,
                    AlipayConfig.notify_url,
                    AlipayConfig.return_url);
        } else {
            if (classFundDetail.getStatus().equals("已支付")) {
                return "已支付";
            } else {
                String s = alipayUtil.judgePay(classFundDetail.getOutTradeNo());
                if (s != null) {
                    BigDecimal oldMoney = account.getAccount();
                    BigDecimal newMoney = oldMoney.add(classFundList.getMoney());
                    account.setAccount(newMoney);
                    classFundDetail.setTradeNo(s);
                    classFundDetail.setStatus("已支付");
                    classFundDetailService.update(classFundDetail);
                    accountService.update(account);
                    return "已支付";
                }
            }
            return alipayUtil.pay(
                    classFundDetail.getOutTradeNo(),
                    money,
                    reason,
                    AlipayConfig.notify_url,
                    AlipayConfig.return_url);
        }
    }

    @RequestMapping("/notice")
    @ResponseBody
    public String notice(HttpServletRequest request) {
        return "";
    }

    @RequestMapping("/return")
    public String returns(AlipayReturn alipayReturn,HttpSession session) {
        ClassFundDetail classFundDetail = classFundDetailService.findByOutTradeNo(alipayReturn.getOut_trade_no());
        classFundDetail.setStatus("已支付");
        classFundDetail.setTradeNo(alipayReturn.getTrade_no());
        classFundDetail.setPayTime(alipayReturn.getTimestamp());
        classFundDetailService.update(classFundDetail);

        //更新专业账户
        String outTradeNo = classFundDetail.getOutTradeNo();
        Integer classFundListId = classFundDetailService.findByOutTradeNo(outTradeNo).getClassFundListId();
        BigDecimal money = classFundListService.findById(classFundListId).getMoney();
        Student student = (Student) session.getAttribute("loginUser");
        Account account = accountService.findByMajor(student.getMajor());
        BigDecimal oldMoney = account.getAccount();
        BigDecimal newMoney = oldMoney.add(money);
        account.setAccount(newMoney);
        accountService.update(account);

        return "redirect:/student/fundList";
    }
}
