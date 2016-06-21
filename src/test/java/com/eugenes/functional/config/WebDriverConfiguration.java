package com.eugenes.functional.config;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author eugene.shragovich
 */

@ComponentScan(basePackages = {"com.eugenes.functional"})
@Configuration
@PropertySource({"classpath:feature.properties", "classpath:feature-ext.properties"})
public class WebDriverConfiguration {

    @Value("${driver.implicitlyWait.time:500}")
    private long implicitWaitTime;

    @Value("${driver.implicitlyWait.unit:MILLISECONDS}")
    private TimeUnit implicitWaitUnit;

    @Inject
    private WebDriver webDriver;

    @Value("${driver.pageLoadTimeout.time:30}")
    private long pageLoadTimeoutTime;

    @Value("${driver.pageLoadTimeout.unit:SECONDS}")
    private TimeUnit pageLoadTimeoutUnit;

    @Value("${wait.pollingEveryTime.time:500}")
    private int pollingEveryTime;

    @Value("${wait.pollingEveryTime.unit:MILLISECONDS}")
    private TimeUnit pollingEveryUnit;

    @Value("${driver.scriptTimeout.time:5}")
    private long scriptTimeoutTime;

    @Value("${driver.scriptTimeout.unit:SECONDS}")
    private TimeUnit scriptTimeoutUnit;

    @Value("${browser.window.height:0}")
    private int windowHeight;

    @Value("${browser.window.width:0}")
    private int windowWidth;

    @Value("${wait.withTimeout.time}")
    private int withTimeoutTime;

    @Value("${wait.withTimeout.unit}")
    private TimeUnit withTimeoutUnit;

    @Value("${wait.short.withTimeout.time:5}")
    private int shortTimeoutTime;

    @Value("${wait.short.withTimeout.unit:SECONDS}")
    private TimeUnit shortTimeoutTimeUnit;

    @Value("${test.server.protocol:http}")
    private String serverProtocol;

    @Value("${test.server.host:localhost}")
    private String serverHost;

    @Value("${test.server.port:80}")
    private String serverPort;

    @Value("${driver.remote.capability.browser:firefox}")
    private String browser;

//    @Bean
//    public WaitConfiguration waitConfig() {
//        return WaitConfiguration.builder().timeoutTime(withTimeoutTime).timeoutUnit(withTimeoutUnit).build();
//    }

    @Bean
    @Primary
    public FluentWait<WebDriver> fluentWait() {
        
        return new FluentWait<WebDriver>(webDriver).pollingEvery(pollingEveryTime, pollingEveryUnit)
                .withTimeout(withTimeoutTime, withTimeoutUnit).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
    }

    @Slf4j
    @Configuration
    @Profile({"local"})
    public static class LocalFirefoxDriverConfiguration {

        @Value("${http.proxy.host:none}")
        private String proxyHost;

        @Value("${http.proxy.port:8080}")
        private int proxyPort;

        @Bean(destroyMethod = "quit")
        public FirefoxDriver firefoxDriver() {
            log.info("Creating firefoxDriver bean");
            FirefoxProfile profile = new FirefoxProfile();

            // Force the use of native events to prevent focus issues
            profile.setEnableNativeEvents(true);

            boolean useProxy = !"none".equals(proxyHost);

            if (useProxy) {

                profile.setPreference("network.proxy.type", 1);
                profile.setPreference("network.proxy.http", proxyHost);
                profile.setPreference("network.proxy.http_port", proxyPort);
                profile.setPreference("network.proxy.ssl", proxyHost);
                profile.setPreference("network.proxy.ssl_port", proxyPort);

            }

            // profile.setEnableNativeEvents(false);
            return new FirefoxDriver(profile);
        }

    }

    @Slf4j
    @Profile({"chrome"})
    public static class LocalChromeDriverConfiguration {

        @Value("${http.proxy.host:none}")
        private String proxyHost;

        @Value("${http.proxy.port:8080}")
        private int proxyPort;

        @Bean(destroyMethod = "quit")
        public ChromeDriver chromeDriver() {

            Capabilities capabilities = new DesiredCapabilities();

            log.info("Creating chromeDriver bean");

            return new ChromeDriver(capabilities);
        }

    }

    @PostConstruct
    public void setWebDriverTimeouts() {

        webDriver.manage().timeouts().implicitlyWait(implicitWaitTime, implicitWaitUnit)
                .setScriptTimeout(scriptTimeoutTime, scriptTimeoutUnit);
        try {
            webDriver.manage().timeouts().pageLoadTimeout(pageLoadTimeoutTime, pageLoadTimeoutUnit);
        } catch (WebDriverException e) {

        }

    }

    @PostConstruct
    public void setWebDriverWindowSize() {

        Window window = webDriver.manage().window();
        window.setPosition(new Point(0, 0));
        window.maximize();

    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}