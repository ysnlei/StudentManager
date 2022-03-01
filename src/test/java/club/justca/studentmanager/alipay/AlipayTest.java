package club.justca.studentmanager.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class AlipayTest {
    @Autowired
    AlipayClient alipayClient;

    @Test
    public void transferTest() {
        AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
        request.setBizContent("{" +
                "  \"out_biz_no\":\"" + UUID.randomUUID().toString() + "\"," +
                "  \"trans_amount\":2.00," +
                "  \"product_code\":\"TRANS_ACCOUNT_NO_PWD\"," +
                "  \"biz_scene\":\"DIRECT_TRANSFER\"," +
                "  \"order_title\":\"班费提现\"," +
                "  \"payee_info\":{" +
                "    \"identity\":\"owfmgh0159@sandbox.com\"," +
                "    \"identity_type\":\"ALIPAY_LOGON_ID\"," +
                "    \"name\":\"owfmgh0159\"" +
                "  }," +
                "  \"remark\":\"班费提现\"," +
                //"  \"business_params\":\"{\\\"sub_biz_scene\\\":\\\"REDPACKET\\\"}\"" +
                "  \"business_params\":\"{\\\"payer_show_name\\\":\\\"学生信息管理系统\\\"}\"" +
                "}");
        AlipayFundTransUniTransferResponse response = null;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
            String body = response.getBody();
            System.out.println(body);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tradeTest(){
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "97b9c136-43fb-4d32-8651-55f89ea6e787");

        request.setBizContent(bizContent.toString());
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println(response.getBody());
            if(response.getBody().contains("SUCCESS")){
                System.out.println("已支付");
            }
            String body = response.getBody();
            Integer start = body.indexOf("\"trade_no\":\"");
            System.out.println(body.subSequence(start+12,start+40));       //2021120122001493150502250926
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
