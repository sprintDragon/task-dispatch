package org.sprintdragon.task.dispatch;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by wangdi on 16-11-30.
 */
@SpringBootApplication
@EnableAdminServer
@EnableScheduling
@EnableAsync
@ImportResource({"classpath:/META-INF.spring/spring-config*.xml"})
public class DispatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(DispatchApplication.class, args);
    }
}