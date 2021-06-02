package cn.shizg.study.project.web.controller;

import cn.shizg.study.project.util.redis.RedisUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description: TODO
 * @Author shizg
 * @Date 2021/6/2
 * @Version V1.0
 **/
@RestController("test")
public class TestController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("hello")
    public String hello(){
        redisUtils.set("aaaaaaaaaaaaaaaa","11111111111111111");
        System.out.println("555555555555");
        return "hello";
    }

    @GetMapping("hello2")
    public String hello2(){

//        // 默认连接地址 127.0.0.1:6379
//        RedissonClient redisson = Redisson.create();
//
//        Config config = new Config();
//        config.useSingleServer().setAddress("myredisserver:6379");
//        RedissonClient redisson = Redisson.create(config);


        RLock lock = redissonClient.getLock("123");
        try {
            lock.lock();
            Thread.sleep(10000);
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
        return "已解锁";
    }
}
