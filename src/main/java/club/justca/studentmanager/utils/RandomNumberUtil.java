package club.justca.studentmanager.utils;

import java.util.Random;

public class RandomNumberUtil {
    public static String getCode(){
        StringBuilder num= new StringBuilder();
        Random random =  new Random();
        for(int i=0;i<6;i++){
            num.append(random.nextInt(10));
        }
        return num.toString();
    }
}
