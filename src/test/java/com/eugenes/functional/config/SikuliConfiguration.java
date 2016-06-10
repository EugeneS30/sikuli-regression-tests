package com.eugenes.functional.config;

import java.io.File;

import javax.annotation.PostConstruct;

import org.sikuli.basics.Settings;
import org.sikuli.script.IScreen;
import org.sikuli.script.ImagePath;
import org.sikuli.script.RunTime;
import org.sikuli.script.Screen;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.eugenes.functional.util.SikuliSupport;

/**
 * @author eugene.shragovich
 */

@Configuration
public class SikuliConfiguration {

    // TODO - defining path like that doesn't work. Should be investigated.
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
        // ImagePath.setBundlePath("C:/workspace/sikuli-regression-tests/src/test/resources/patterns/");
        ImagePath.setBundlePath(new File(RunTime.get().fSxProject, "target/test-classes/patterns/").getAbsolutePath());
    }
    
    @Bean
    public SikuliSupport sikuliSupport() {
        return new SikuliSupport();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
