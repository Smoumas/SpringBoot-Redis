package redis.example.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.example.redis.service.ServiceImpl;

@Controller
public class RedisController {

    @Autowired
    private ServiceImpl serviceImpl;

    @RequestMapping("/message")
    @ResponseBody
    public String message(){
        return serviceImpl.message();
    }
}
