package com.eugenes.functional.steps;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@Slf4j
public class searchSteps {
    
    @Autowired
    private WebDriver driver;
    
//    @Autowired
//    private Screen screen;
    
    @When("^I wait for element \"([^\"]*)\"$")
    public void i_wait_for_element(final String patternName) throws Throwable {
//        screen.wait(patternName, 1000);
        log.info("Pattern name: " + patternName);

    }

    @Then("^the element is located$")
    public void the_element_is_located() throws Throwable {
        

    }

    @When("^I search for element \"([^\"]*)\"$")
    public void i_search_for_element() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I observe the screen for element \"([^\"]*)\"$")
    public void i_observe_the_screen_for_element() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
    

}
