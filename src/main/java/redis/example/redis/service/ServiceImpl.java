package redis.example.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

//实际开发中的Service，此处只是写个示例
@Service
public class ServiceImpl {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    public String message(){
        String message = (String)redisTemplate.opsForValue().get("message");
        if(message == null){
            //若缓存中message为空，则从数据库中查询，并放入缓存中
            System.out.println("NULL MESSAGE");
            message = "helloworld";  //模拟数据库的查询
            //查询数据放入redis
            redisTemplate.opsForValue().set("message",message);
        }
        return message;
    }
}
