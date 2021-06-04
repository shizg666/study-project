package cn.shizg.study.project.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "cn.shizg.study.project.*")
@SpringBootApplication
public class StudyProjectOauthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyProjectOauthServerApplication.class, args);
    }

}
