package com.eugenes.functional;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java.After;
import cucumber.api.java.Before;

@ContextConfiguration(classes=WebDriverConfig.class)
public class Hook {
    
    @Autowired
    private InterfaceStub beansClass;
    
    @Autowired
    private WebDriver driver;

    @Before
    public void setUp() {
        driver.get("http://www.google.com");
        beansClass.play();

        System.out.println("hello");
    }

    @After
    public void tearDown() {
        System.out.println("bye");
    }

}
