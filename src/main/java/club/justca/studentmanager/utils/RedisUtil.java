package club.justca.studentmanager.utils;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    RedisTemplate<String,String> template;
    public void set(String key,String value,Integer minutes){
        if(get(key)!=null){
            update(key, value, minutes);
        }
        template.opsForValue().set(key,value,minutes, TimeUnit.MINUTES);
    }

    public String get(String key){
        return template.opsForValue().get(key);
    }

    public void update(String key,String value,Integer minutes){
        template.delete(key);
        template.opsForValue().set(key,value,minutes, TimeUnit.MINUTES);
    }

    public Boolean delete(String key){
        return template.delete(key);
    }
}
