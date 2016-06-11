package com.eugenes.functional.glue.steps;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class navigationSteps extends AbstractSteps {
    
    @Autowired
    private WebDriver driver;

    @When("^I navigate to \"(.*?)\"$")
    @Given("^I have navigated to \"(.*?)\"$")
    public void i_have_navigated_to(String url) throws Throwable {
        
        driver.get(url);
        
    }
    
    @When("^navigate to a blank page$")
    public void navigate_to_blank_page() throws Throwable {
    	
    	i_have_navigated_to("about:blank");

    }


}
