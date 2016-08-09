package com.eugenes.functional.hooks;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.eugenes.functional.config.SikuliConfiguration;
import com.eugenes.functional.config.WebDriverConfiguration;

import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * @author eugene.shragovich
 */

@ContextConfiguration(classes = {WebDriverConfiguration.class, SikuliConfiguration.class})
public class Hook {

    @Autowired
    private WebDriver driver;

    @Autowired
    private Screen screen;

    @Before
    public void prepareTestEnvironment() {

        if (!isUsingPrimaryMonitor()) {
            driver.manage().window().setPosition(new Point(0, 0));
            driver.manage().window().maximize();
        }

        driver.get("about:blank");

    }

    @After
    public void finaliseTest() {

        driver.manage().deleteAllCookies();

    }

    private boolean isUsingPrimaryMonitor() {

        if (driver.manage().window().getPosition().equals(new Point(0, 0))) {
            return true;
        }

        return false;
    }

}
