// ЗАДАЧА
// сделать новостной сайт используя
// Spring Boot MVC,
// Beans,
// основы Spring Boot Security,
// JDBC,
// DAO,
// JUnit
// (если не получается то подсматривать у Дударя или Алишева)


// 1) создать ДАО для класса Post с нужными функциями
// 2) создать JDBC запросы в функциях PostDAO

// 1) получить все строки из таблици (когда печатается)
// 2)

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
