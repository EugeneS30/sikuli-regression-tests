package com.eugenes.functional.glue.steps;

import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eugenes.functional.util.SikuliSupport;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
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

    @Inject
    private SikuliSupport sikuli;

    @Setter
    private Pattern storedPattern;
    
    @Setter
    private boolean observationOutcome;
    
    @Value("${sikuli.observeTimeout.time:5}")
    private float observeTimeout;

    @When("^I wait for pattern \"([^\"]*)\"$")
    public void i_wait_for_pattern(final String patternName) throws FindFailed {

        log.info("Waiting for pattern: " + patternName + ". . .");

        setStoredPattern(new Pattern(patternName));
        setStoredMatch(screen.wait(patternName));

    }

    @When("^I observe the screen for pattern \"(.*?)\" to \"(.*?)\"$")
    public void i_observe_the_screen_for_pattern_to(final String pattern, final String eventType) throws Throwable {

        log.info("Observing for pattern: {}", pattern);

        Region region = screen;

        switch (ObserverEvent.fromString(eventType)) {
            case ON_APPEAR:
                region.onAppear(pattern);
                break;

            case ON_VANISH:
                region.onVanish(pattern);
                break;

            case ON_CHANGE:
                region.onChange();

            default:
                throw new PendingException("event type not supported: " + eventType);
        }
        
        observationOutcome = region.observe(observeTimeout);

    }

    @Then("^the event fires$")
    public void the_onAppear_event_fires() throws Throwable {
        
        assertThat(observationOutcome).isTrue();

//        setStoredPattern(new Pattern(pattern));
//        setStoredMatch(screen.find(pattern));
 
    }

    @When("^I observe the screen for pattern \"(.*?)\" to change$")
    public void i_observe_the_screen_for_pattern_to_change(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I observe the screen for pattern \"([^\"]*)\" to vanish$")
    public void i_observe_the_screen_for_element_to_vanish(final String patternName) throws Throwable {

        log.info("Observing for pattern: " + patternName + ". . .");

        Region region = screen;
        region.onVanish(patternName);

        // assertThat(result).isTrue();

        boolean result = region.observe(100);

        assertThat(result).isTrue();

        setStoredPattern(new Pattern(patternName));
        setStoredMatch(screen.find(patternName));

    }

    @Given("^the pattern \"(.*?)\" (is|is not) visible on the screen$")
    public void the_pattern_is_visible_on_the_screen(final String pattern, final String maybe) throws Throwable {

        boolean isShown = "is".equals(maybe);

        assertThat(sikuli.doesExist(pattern)).isEqualTo(isShown);

    }

    @Then("^the pattern \"([^\"]*)\" exists$")
    public void the_element_exists(final String patternName) throws Throwable {

        log.info("Checking that not null");
        assertThat(screen.exists(storedPattern)).isNotNull();
        log.info("Checked");
        // assertThat(storedMatch.getScore()).isGreaterThan(0.95);
        assertThat(storedMatch.getScore()).isGreaterThan(0.9);
        storedMatch = null;

    }
}
