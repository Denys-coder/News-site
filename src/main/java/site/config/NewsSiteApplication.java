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
