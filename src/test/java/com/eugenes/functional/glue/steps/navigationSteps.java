package com.eugenes.functional.glue.steps;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.en.Given;

public class navigationSteps extends AbstractSteps {
    
    @Autowired
    private WebDriver driver;
    
    @Given("^I have navigated to \"(.*?)\"$")
    public void i_have_navigated_to(String url) throws Throwable {
        
        driver.get(url);
        
    }

}
