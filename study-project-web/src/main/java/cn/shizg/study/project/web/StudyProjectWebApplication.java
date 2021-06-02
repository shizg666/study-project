package cn.shizg.study.project.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "cn.shizg.study.project.*")
@SpringBootApplication
public class StudyProjectWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyProjectWebApplication.class, args);
    }

}
