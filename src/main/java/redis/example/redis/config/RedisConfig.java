package redis.example.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 指定Redis的序列化方式，若不指定，则key的字符会在指定的字符前多加几个字符
 * 如指定key为message，实际存储为"\xac\xed\x00\x05t\x00\amessage"，但是通过程序是可以正常取值的，只是在客户端中查看需要使用实际的key查询
 */

/**
 * 使用这种方法生成RedisTemplate的Bean会存在没有指定password的问题
 * 因为只是为了保证key的正常显示，不需要再单独生成RedisTemplate，而由SpringBoot根据配置注入即可
 * 在Service层添加代码，即可完成key的序列化
 *         RedisSerializer redisSerializer = new StringRedisSerializer();
 *         redisTemplate.setKeySerializer(redisSerializer);
 *  //不一定位于service层，只是该示例是service层使用到了redis
 *
 *  注意：使用这种方式后，因为value是没进行序列化方式指定的，value也会有多字符的问题
 */


//@Configuration
public class RedisConfig {

    /*
    @Bean(name = "lettuceConnectionFactory")
    public LettuceConnectionFactory lettuceConnectionFactory(){
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<Object,Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
    */
}
