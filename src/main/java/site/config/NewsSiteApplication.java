// ЗАДАЧА
// сделать новостной сайт используя
// Spring Boot MVC,
// Beans,
// основы Spring Boot Security,
// JDBC,
// DAO,
// JUnit


// 1) создать JDBC запросы в функциях PostDAO


package site.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("site")
public class NewsSiteApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(NewsSiteApplication.class, args);
    }
}
