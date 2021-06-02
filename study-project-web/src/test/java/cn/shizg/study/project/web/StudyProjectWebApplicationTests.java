package cn.shizg.study.project.web;

import cn.shizg.study.project.util.redis.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudyProjectWebApplication.class)
public class StudyProjectWebApplicationTests {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void contextLoads() {
        redisUtils.set("aaaaaaaaaaaaaaaa","11111111111111111");
        System.out.println("555555555555");
    }

}
