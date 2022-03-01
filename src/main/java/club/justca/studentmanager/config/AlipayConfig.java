package club.justca.studentmanager.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AlipayConfig {
    // 商户appid
    public static String APPID = "2021000118648798";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDCB9CJQI7OcvN/iSP73H/prF8n88m5HvcSAkfZ+WQZea/X2t/rQkeo79nc/OG5G9ud3UftuDxArYD3jXupdsQWU0H4yUFxWAZ03UbgkxcJ/TjPvllkcN+fYom/LeU51fzVRpn4Uoszn9T8v6dSrEzzKu6B5KsiFJPW3vbPZuX3fNyp/x5i/c2RSDlA5EdsXupUSSmYfrf9fHovzPejFrFVMlPdXbnt3ZbhVLU1VnQo1R6FLLnKfB16uQ5GZCpLtzavBtFiL2YxasWP14BtOkFYZjpdD7BPA8gFh3UyyEEcRAS+ONE65pk3ZJ80KarnfQ1a2Hc4sMrd6ReaikWJ2MDDAgMBAAECggEBAL1JoGB10ZuIOxJ9eNRZjZQqa/5u76mgkspH3U7B5KyRsUF0rqQ1WfFuZG0ZayAKFFtSp7w60tUKLtnSdgSh77QT7zVFKYnopQvXXzDxbjV5irXobg71qsMz/0u5+3lL0EdEhCrkZBat47vbQOH/Y2aKyHmB2o354yxpAgDd0pV3zWXfG0Hd/ad6T4Pa3srVMLPEFMEaS1/92EFn2jFyoQpsAU9h6DJbFdk7yH//zgU8FyXepeU9dG9v8NzOr76ftGoVBbY+KmUSx12+jlmhaqJW3dlOmntvJG4io7GSPS7XFaHQoZwaWH8cpNRd7dpgvDGPwEnVFvnVlzemttzW0rkCgYEA+vgYEg3UqIeYhsyoiCKQ4LfRotCTL2X4CT3nXJv41k6aZa9fjZ+Q62WyFKhUtbhEezWweWWXgw4KjjqM1hT9tXNNwaEVg+e31KLFzPOafDtjGcf8uDKbCfgheRnPsNPU6kU39jOoHjXQyUfl5LeCruZwiMtifu+PCxc9N/eb2t8CgYEAxeuG7Ao7TAohg0DhM18TEtIbMtnYl6h0I7/fqDpekDask5DqnWljzvxY8wsgLLtHylimx5iLRhdgo9DKfFdeNDU5/1ACv/jaKMCuLPUts3jue0cZ4YxDpjYE9HPznZWKC31nGxD/hrI6c/3MrSQIEY82o0zjLTRDtcF9CSLzOp0CgYADyoeiji7uNM4rCyBong0ON/ikuv3eM24hxZufHIQPyLQwYQvSedyYkbq08S4GaLgwHEmEK/Kz+hxStbO28C489XbxO9u1WGTAg4z1YaCty7/dfdXlem45IlRvHeKzSx/mQhFsWK9rdWioAbwJTnRc7AI7LsdYH4pLopg8JjNmhQKBgFLFfVeZr9LNKdbKe2QQjtmAJP/PO1WxjWCmDdM9Fe8Z7nKIWJYcTTFzN3/tVzdd21g0Wwt+MEDP5gqcdxIpN6x91hOAUqYWXKZObok+wC4elR3ZTmmVT3Vjgg75rxhoQaMsfsJDCZo7T1bS/ZYrS3SMgD0rGpb9FJQepzv68d3BAoGAXvNuoLtKxP2v5dq2sbd0QVgJx3K6N05ytnBqr+KijUDVG0Dli43guu7vFsD+k4yU1IgvXqt5LC/p0jOwW5NmAj0LqqqTXma5LGu0lELlLWx/t0+GfUqySY8m3TlHuio+bm7dEX4e/awpoJrVbcd9ke3sNFz+21V1eQQMfwqb1lk=";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/student/notice";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://localhost:8080/student/return";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvxDYg9yo210SZs50ya+j2I12iSmiZM6/78KW/pCJIk62kLntuSDJcL2ThBP+MFaLGrfy9G/8mvmDfj370Sst1KmNaZvx1b6bk4At8tryDArqumwN/+wFaFUgwtp0B3I3wDryCgyUu3cUmag/4X6Y+9mLkEDwk2uuC+cDywIG8LWieUIfFkC6T8K6RPuhTghq3mNeP4dl3bNizuP9mibfH3B64ICojS0aHPqqsUHaO3/kUA/GIl0m811n7LdyOSVfGAwxl9YDjQhO8l8aVykExzBQ5oUSHETSbcOAP8xU4RRp0zTE84Mo16feuIPp39GD0x4paLQLMNzs6SyGbcM3SwIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";

    @Bean
    public AlipayClient alipayClient(){
        return new DefaultAlipayClient(
                AlipayConfig.URL,
                AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGNTYPE);
    }
}
