package club.justca.studentmanager.VO;

import lombok.Data;

@Data
public class FileUpload {
    private String status;  //上传是否成功
    private String url;     //图片存储地址

    public FileUpload(){
        status = "error";       //默认图片存储失败
    }

    public FileUpload(String dirName, String fileName){
        status = "success";
        url = "/upload/file/"+dirName+"/"+fileName;
    }
}
