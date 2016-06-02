package com.eugenes.functional.glue.steps;

import static org.fest.assertions.Assertions.assertThat;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author eugene.shragovich
 */

@Slf4j
@Component
public class searchSteps extends AbstractSteps {

    @Autowired
    private Screen screen;

    /**
     * If the pattern was found by one of the steps it will be stored in this variable.
     */
    @Setter
    private Match storedMatch;

    @Setter
    private Pattern storedPattern;

    @When("^I wait for pattern \"([^\"]*)\"$")
    public void i_wait_for_pattern(final String patternName) throws FindFailed {

        log.info("Waiting for pattern: " + patternName + ". . .");

        setStoredPattern(new Pattern(patternName));
        setStoredMatch(screen.wait(patternName));
        
    }

    @When("^I observe the screen for pattern \"([^\"]*)\"$")
    public void i_observe_the_screen_for_element(final String patternName) throws Throwable {

        log.info("Observing for pattern: " + patternName + ". . .");

        Region region = screen;
        region.onAppear(patternName);
        
        boolean result = region.observe(100); 

        assertThat(result).isTrue();
        
        
        setStoredPattern(new Pattern(patternName));
        setStoredMatch(screen.find(patternName));
        
        

    }

    @Then("^the pattern \"([^\"]*)\" exists$")
    public void the_element_exists(final String patternName) throws Throwable {

        log.info("Checking that not null");
        assertThat(screen.exists(storedPattern)).isNotNull();
        log.info("Checked");
        assertThat(storedMatch.getScore()).isGreaterThan(0.95);
        storedMatch = null;

    }
}
