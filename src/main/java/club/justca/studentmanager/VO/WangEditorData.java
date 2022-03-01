package club.justca.studentmanager.VO;

import lombok.Data;

@Data
public class WangEditorData {
    private String url;     //图片地址
    private String alt;     //图片文字说明
    private String href;    //跳转链接

    public WangEditorData() {
        url = "/upload/";
        alt = "";
        href = "";
    }

    public void appendUrl(String fileName) {
        url += fileName;
    }

    public void appendUrl(String dirName, String fileName) {
        url += dirName + "/" + fileName;
    }
}
