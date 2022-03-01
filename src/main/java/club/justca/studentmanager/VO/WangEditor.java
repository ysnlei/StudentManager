package club.justca.studentmanager.VO;

import lombok.Data;

import java.util.List;

@Data
public class WangEditor {
    private Integer errno; //错误代码，0 表示没有错误。
    private List<WangEditorData> data; //已上传的图片路径

    public WangEditor(){
        this.errno = 1;     //有错误
    }

    public WangEditor(List<WangEditorData> data) {
        this.errno = 0;
        this.data = data;
    }
}
