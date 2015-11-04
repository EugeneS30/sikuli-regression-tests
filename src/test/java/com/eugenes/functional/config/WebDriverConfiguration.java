package com.eugenes.functional.config;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@Configuration
@PropertySource("classpath:feature.properties")
public class WebDriverConfiguration {
    
    @Value("${driver.implicitlyWait.time:500}")
    private long implicitWaitTime; //Should be long

    @Value("${driver.implicitlyWait.unit:MILLISECONDS}")
    private TimeUnit implicitWaitUnit; //Should be TimeUnit
    
    @Autowired
    private WebDriver webDriver;

//    @Value("${driver.pageLoadTimeout.time:30}")
//    private long pageLoadTimeoutTime;
//
//    @Value("${driver.pageLoadTimeout.unit:SECONDS}")
//    private TimeUnit pageLoadTimeoutUnit;
//
//    @Value("${wait.pollingEveryTime.time:500}")
//    private int pollingEveryTime;
//
//    @Value("${wait.pollingEveryTime.unit:MILLISECONDS}")
//    private TimeUnit pollingEveryUnit;
//
//    @Value("${driver.scriptTimeout.time:5}")
//    private long scriptTimeoutTime;
//
//    @Value("${driver.scriptTimeout.unit:SECONDS}")
//    private TimeUnit scriptTimeoutUnit;
//
//    @Value("${browser.window.height:0}")
//    private int windowHeight;
//
//    @Value("${browser.window.width:0}")
//    private int windowWidth;
//
//    @Value("${wait.withTimeout.time}")
//    private int withTimeoutTime;
//
//    @Value("${wait.withTimeout.unit}")
//    private TimeUnit withTimeoutUnit;
//
//    @Value("${wait.short.withTimeout.time:5}")
//    private int shortTimeoutTime;
//
//    @Value("${wait.short.withTimeout.unit:SECONDS}")
//    private TimeUnit shortTimeoutTimeUnit;
//
//    @Value("${test.server.protocol:http}")
//    private String serverProtocol;
//
//    @Value("${test.server.host:localhost}")
//    private String serverHost;
//
//    @Value("${test.server.port:80}")
//    private String serverPort;
//
////    @Value("${driver.remote.capability.browser:firefox}")
////    private String browser;

    @Bean(destroyMethod = "quit")
    public FirefoxDriver driver() {
        return new FirefoxDriver();
    }
        
//    @PostConstruct
//    public void setWebDriverTimeouts() {
//
//        driver.manage().timeouts().implicitlyWait(implicitWaitTime, implicitWaitUnit)
//                .setScriptTimeout(scriptTimeoutTime, scriptTimeoutUnit);
//        try {
//            driver.manage().timeouts().pageLoadTimeout(pageLoadTimeoutTime, pageLoadTimeoutUnit);
//        } catch (WebDriverException e) {
//            // probably running Safari, currently doesn't support page timeouts.
//            // https://code.google.com/p/selenium/issues/detail?id=6015
//        }
//
//    }

    @PostConstruct
    public void setWebDriverWindowSize() {

        Window window = webDriver.manage().window();
        window.setPosition(new Point(0, 0));
        window.maximize();

    }

}