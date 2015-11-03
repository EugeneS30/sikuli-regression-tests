package com.eugenes.functional;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class WebDriverSupport extends EventFiringWebDriver {

    public WebDriverSupport() {
        super(new FirefoxDriver());
        System.out.println("hello!!!!!!");

    }

}
