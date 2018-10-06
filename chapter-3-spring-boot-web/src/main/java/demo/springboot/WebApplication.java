package demo.springboot;

import com.byedbl.spring.config.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring Boot 应用启动类
 * <p>
 * Created by bysocket on 26/09/2017.
 */
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WebApplication.class, args);
        if (context.containsBean("helloService")) {
            HelloService helloService = context.getBean(HelloService.class);
            System.out.println(helloService.sayHello());
        }else{
            System.out.println("without helloService");
        }

    }
}
