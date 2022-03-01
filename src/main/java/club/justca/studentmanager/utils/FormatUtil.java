package club.justca.studentmanager.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatUtil {
    public static String getFormatImgName() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format(new Date());
        return time + calendar.getTimeInMillis();
    }

    public static String getFormatImgDirName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }

    public static String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            return fileName.substring(index);
        }
        return "";
    }

    public static String getFormatFileDirName(){
        return getFormatImgDirName();
    }

    public static String getFormatFileName(){
        return getFormatImgName();
    }
}
