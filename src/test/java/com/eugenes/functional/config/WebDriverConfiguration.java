package com.eugenes.functional.config;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@ComponentScan
@Configuration
@PropertySource({"classpath:feature.properties", "classpath:feature-ext.properties"})
public class WebDriverConfiguration {

    @Value("${driver.implicitlyWait.time:500}")
    private long implicitWaitTime; // Should be long

    @Value("${driver.implicitlyWait.unit:MILLISECONDS}")
    private TimeUnit implicitWaitUnit; // Should be TimeUnit

    @Autowired
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

    @Configuration
    @Profile({"local"})
    public static class LocalFirefoxDriverConfiguration {

        @Value("${http.proxy.host:none}")
        private String proxyHost;

        @Value("${http.proxy.port:8080}")
        private int proxyPort;

        @Bean(destroyMethod = "quit")
        public FirefoxDriver firefoxDriver() {
            FirefoxProfile profile = new FirefoxProfile();

            // Force the use of native events to prevent focus issues
            profile.setEnableNativeEvents(true);

            boolean useProxy = !"none".equals(proxyHost);

            // Configure proxy if required
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

        // @Bean
        // public WebDriverEnvironment localEnvironment() {
        // return new LocalWebDriverEnvironment();
        // }

    }

    @PostConstruct
    public void setWebDriverTimeouts() {

        webDriver.manage().timeouts().implicitlyWait(implicitWaitTime, implicitWaitUnit)
                .setScriptTimeout(scriptTimeoutTime, scriptTimeoutUnit);
        try {
            webDriver.manage().timeouts().pageLoadTimeout(pageLoadTimeoutTime, pageLoadTimeoutUnit);
        } catch (WebDriverException e) {
            // probably running Safari, currently doesn't support page timeouts.
            // https://code.google.com/p/selenium/issues/detail?id=6015
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