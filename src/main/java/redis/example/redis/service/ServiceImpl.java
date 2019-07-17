package redis.example.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

//实际开发中的Service，此处只是写个示例
@Service
public class ServiceImpl {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    public String message(){
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        //在高并发条件下，存在缓存穿透问题
        //也就是多线程下，防止多个用户都查询数据库的情况
        //正常情况下，应该只有单个用户查询，其余用户等待
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

    //解决缓存穿透最简便的方法就是直接在方法上加上Synchronized修饰符
    //为了尽量提升性能，可以将synchronized修饰符放入方法内
    public String messageSynchronized(){
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        String message = (String)redisTemplate.opsForValue().get("message");
        if(message == null) {
            synchronized (this) {   //ServiceBean为单例对象，可以用作同步锁
                message = (String) redisTemplate.opsForValue().get("message");
                if (message == null) {
                    System.out.println("NULL MESSAGE");
                    message = "helloworld";  //模拟数据库的查询
                    redisTemplate.opsForValue().set("message", message);
                }
            }
        }
        return message;
    }
}
