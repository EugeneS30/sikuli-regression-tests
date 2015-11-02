package com.eugenes.functional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ComponentScan
@Configuration
public class WebDriverConfig{

    @Bean
    public FirefoxDriver driver() {
        return new FirefoxDriver();
    }


}
