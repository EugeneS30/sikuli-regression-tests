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

    @Inject
    private SikuliSupport sikuli;

    // If the pattern was found by one of the steps it will be stored in this
    // variable.
    @Setter
    private Match storedMatch;

    @Setter
    private Pattern storedPattern;

    @Setter
    private boolean observationOutcome;

    @Value("${sikuli.observeTimeout.time:10}")
    private float observeTimeout;

    @Value("${sikuli.waitTimeout.time:10}")
    private float waitTimeout;

    @When("^I wait for pattern \"([^\"]*)\"$")
    public void i_wait_for_pattern(final String patternName) throws FindFailed {

        log.info("Waiting for pattern: " + patternName + ". . .");

        setStoredPattern(new Pattern(patternName));
        setStoredMatch(screen.wait(patternName, waitTimeout));

    }

    @When("^I observe the screen for pattern \"(.*?)\" to \"(.*?)\"$")
    public void i_observe_the_screen_for_pattern_to(final String pattern, final String eventType) throws Throwable {

        log.info("Setting initial outcome to False");
        setObservationOutcome(false);

        Region region = screen;

        switch (ObserverEvent.fromString(eventType)) {
            case ON_APPEAR:
                log.info("ON_APPEAR");
                region.onAppear(pattern);
                break;

            case ON_VANISH:
                log.info("ON_VANISH");
                region.onVanish(pattern);
                break;

            case ON_CHANGE:
                log.info("ON_CHANGE");
                region.onChange();

            default:
                throw new PendingException("event type not supported: " + eventType);
        }

        log.info("Starting the observation");
        boolean outcome = region.observeInBackground(observeTimeout);
        log.info("Observation completed with result: {}! Setting the outcome accordingly", outcome);
        setObservationOutcome(outcome);

    }

    @Then("^the event fires$")
    public void the_onAppear_event_fires() throws Throwable {

        assertThat(observationOutcome).isTrue();

    }

    @Given("^the pattern \"(.*?)\" (exists|does not exist) on the screen$")
    public void the_pattern_maybe_visible_on_the_screen(final String pattern, final String maybe) throws Throwable {

        boolean isShown = "exists".equals(maybe);

        if (isShown) {
            assertThat(sikuli.doesExist(pattern)).isTrue();
            assertThat(storedMatch.getScore()).isGreaterThan(0.9);
            
            resetStoredMatch();
            
        } else {
            assertThat(sikuli.doesExist(pattern)).isFalse();
        }
        
        
    }

    /**
     * This method must be used after each verification so that it a subsequent tests will not be
     * affected by past results.
     */
    private void resetStoredMatch() {
        log.debug("Resetting stored Match object to null.");
        setStoredMatch(null);

    }

}
