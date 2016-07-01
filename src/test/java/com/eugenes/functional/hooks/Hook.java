package com.eugenes.functional.hooks;

import javax.inject.Inject;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.eugenes.functional.config.SikuliConfiguration;
import com.eugenes.functional.config.WebDriverConfiguration;
import com.eugenes.functional.domain.Scenario;

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
    
    @Inject
    private Scenario scenario;
    
    @Inject
    private cucumber.api.Scenario cucumberScenario;

    @Before
    public void prepareTest() {

    	scenario.setName(cucumberScenario.getName());
        driver.get("about:blank");

    }

    @After(order=1)
    public void cleanStoredVariables() {
    	
    }
    
    
    @After(order=100)
    public void finaliseTest() {

        driver.manage().deleteAllCookies();

    }

}
