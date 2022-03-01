package club.justca.studentmanager.utils;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class AlipayUtil {
    @Autowired
    AlipayClient alipayClient;

    public String pay(String outTradeNo, String totalAmount, String subject,
                      String notifyUrl, String returnUrl) throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", outTradeNo);
        bizContent.put("total_amount", totalAmount);
        bizContent.put("subject", subject);     //交易主题
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        if (response.isSuccess()) {
            return alipayClient.pageExecute(request).getBody();
        } else {
            return "系统繁忙";
        }
    }

    public Boolean withdrew(String account, String name, String money) {
        AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
        request.setBizContent("{" +
                "  \"out_biz_no\":\"" + UUID.randomUUID().toString() + "\"," +
                "  \"trans_amount\":" + money + "," +
                "  \"product_code\":\"TRANS_ACCOUNT_NO_PWD\"," +
                "  \"biz_scene\":\"DIRECT_TRANSFER\"," +
                "  \"order_title\":\"班费提现\"," +
                "  \"payee_info\":{" +
                "    \"identity\":\"" + account + "\"," +   //owfmgh0159@sandbox.com
                "    \"identity_type\":\"ALIPAY_LOGON_ID\"," +
                "    \"name\":\"" + name + "\"" +   //owfmgh0159
                "  }," +
                "  \"remark\":\"班费提现\"," +
                //"  \"business_params\":\"{\\\"sub_biz_scene\\\":\\\"REDPACKET\\\"}\"" +
                "  \"business_params\":\"{\\\"payer_show_name\\\":\\\"学生信息管理系统\\\"}\"" +
                "}");
        AlipayFundTransUniTransferResponse response = null;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                log.info("提现调用成功");
                return true;
            } else {
                log.warn("提现调用失败");
                return false;
            }
            //String body = response.getBody();
            //System.out.println(body);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String judgePay(String outTradeNo) {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", outTradeNo);

        request.setBizContent(bizContent.toString());
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            String body = response.getBody();
            if (body.contains("SUCCESS")) {
                int start = body.indexOf("\"trade_no\":\"");
                return body.subSequence(start + 12, start + 40).toString();       //2021120122001493150502250926
            }
        }
        return null;
    }
}
