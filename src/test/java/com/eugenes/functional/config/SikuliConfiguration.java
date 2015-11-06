package com.eugenes.functional.config;

import javax.annotation.PostConstruct;

import org.sikuli.basics.Settings;
import org.sikuli.script.IScreen;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class SikuliConfiguration {

    @Value("${sikuli.bundlePath:classpath:src/test/resources/patterns}")
    private String sikuliBundlePath;

    @Value("${sikuli.autoWaitTimeout:5}")
    private float autoWaitTimeout;

    @Bean
    public IScreen screen() {
        return new Screen();
    }

    @PostConstruct
    public void setSikuliSettings() {
        Settings.AutoWaitTimeout = autoWaitTimeout;
        ImagePath.setBundlePath("C:/workspace/sikuli-regression-tests/src/test/resources/patterns/");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
