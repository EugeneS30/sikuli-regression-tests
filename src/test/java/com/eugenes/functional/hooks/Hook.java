package com.eugenes.functional.hooks;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.eugenes.functional.config.SikuliConfiguration;
import com.eugenes.functional.config.WebDriverConfiguration;

import cucumber.api.java.After;
import cucumber.api.java.Before;

@ContextConfiguration(classes = {WebDriverConfiguration.class, SikuliConfiguration.class})
public class Hook {

    @Autowired
    private WebDriver firefoxDriver;
    
    @Autowired 
    private Screen screen;

    @Before
    public void setUp() {
        firefoxDriver.get("http://www.sikulix.com");
    }

    @After
    public void tearDown() {
        // driver.close();

    }

}
