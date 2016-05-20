package com.eugenes.functional.glue.steps;

import static org.fest.assertions.Assertions.assertThat;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.eugenes.functional.config.SikuliConfiguration;
import com.eugenes.functional.config.WebDriverConfiguration;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author eugene.shragovich
 */

@Slf4j
public class searchSteps extends AbstractSteps {

    @Autowired
    private Screen screen;

    @When("^I wait for element \"([^\"]*)\"$")
    public void i_wait_for_element(final String patternName) throws Throwable {
        
        log.info("Waiting for element: " + patternName + ". . .");

        screen.wait(patternName);
    }

    @Then("^the element \"([^\"]*)\" exists$")
    public void the_element_exists(final String patternName) throws Throwable {

        assertThat(screen.exists(patternName)).isNotNull();

    }

    @When("^I search for element \"([^\"]*)\"$")
    public void i_search_for_element(final String patternName) throws Throwable {
        
        screen.observe();
        
    }

    @When("^I observe the screen for element \"([^\"]*)\"$")
    public void i_observe_the_screen_for_element() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
