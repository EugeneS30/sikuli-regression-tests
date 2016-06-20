package com.eugenes.functional.glue.steps;

import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.ObserveEvent;
import org.sikuli.script.ObserverCallBack;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eugenes.functional.util.SikuliSupport;
import com.google.common.base.Predicate;

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

    @Inject
    private FluentWait<WebDriver> wait;

    // If the pattern was found by one of the steps it will be stored in this
    // variable.
    @Setter
    private Match storedMatch;

    @Setter
    private Pattern storedPattern;

    @Setter
    private boolean observationOutcome;

    @Setter
    private Region region;

    @Value("${sikuli.observeTimeout.time:100}")
    private int observeTimeout;

    @Value("${sikuli.waitTimeout.time:10}")
    private float waitTimeout;

    // Threshold value in pixels
    @Value("${sikuli.onChange.threshold:50}")
    private float onChangeThreshold;

    @When("^I wait for pattern \"([^\"]*)\"$")
    public void i_wait_for_pattern(final String patternName) throws FindFailed {

        log.info("Waiting for pattern: " + patternName + ". . .");

        setStoredPattern(new Pattern(patternName));
        setStoredMatch(screen.wait(patternName, waitTimeout));

    }

    @When("^I observe the screen for pattern \"(.*?)\" to \"(.*?)\"$")
    public void i_observe_the_screen_for_pattern_to(final String pattern, final String eventType) throws Throwable {
        log.info("Going to observer region for event: {}", eventType);

        log.info("Setting initial outcome to False");
        setObservationOutcome(false);

        region = screen;

        log.info("Checking event type");
        switch (ObserverEvent.fromString(eventType)) {

            case ON_APPEAR:
                log.info("Evenet type: ON_APPEAR");
                log.info("Assigning event to the area");

                region.onAppear(pattern, new ObserverCallBack() {

                    @Override
                    public void appeared(ObserveEvent e) {
                        eventFired(e);
                    }
                });

                break;

            case ON_VANISH:
                log.info("Evenet type: ON_VANISH");

                region.onVanish(pattern, new ObserverCallBack() {

                    @Override
                    public void vanished(ObserveEvent e) {
                        eventFired(e);
                    }
                });

                break;

            case ON_CHANGE:
                log.info("Evenet type: ON_CHANGE");

                region.onChange(50, new ObserverCallBack() {

                    @Override
                    public void changed(ObserveEvent e) {
                        eventFired(e);
                    }
                });

            default:
                throw new PendingException("event type not supported: " + eventType);
        }

        log.info("Starting the observation with timeout: {}", observeTimeout);
        region.observeInBackground(observeTimeout);

    }

    @Then("^the event have been fired$")
    public void the_onAppear_event_fires() throws Throwable {

        log.info("Verifying that the event has fired");

        wait.until(new Predicate<WebDriver>() {

            @Override
            public boolean apply(WebDriver input) {
                log.info("Current observation result: {}", observationOutcome);

                return observationOutcome;

            }
        });

    }

    @Given("^the pattern \"(.*?)\" (exists|does not exist) on the screen$")
    public void the_pattern_maybe_visible_on_the_screen(final String pattern, final String maybe) throws Throwable {
        log.info("Checking pattern existence");

        boolean isShown = "exists".equals(maybe);

        if (isShown) {
            log.info("Expecting pattern to exist on the screen");

            resetStoredMatch();
            setStoredMatch(screen.exists(pattern, waitTimeout));
            assertThat(storedMatch).isNotNull();

            log.info("The pattern exists on the screen!");

        } else {
            log.info("Expecting pattern to not exist on the screen");

            assertThat(screen.exists(pattern, 0)).isNull();
            assertThat(sikuli.doesExist(pattern)).isFalse();
            log.info("The pattern does not exist on the screen!");

        }
    }

    /**
     * WARNING: This step will reset the stored Match to avoid false positives in subsequent
     * verification steps.
     */
    @Then("^the pattern matches with score (\\d+\\.\\d+)$")
    public void the_pattern_matches_with_score(final double score) throws Throwable {

        assertThat(storedMatch.getScore()).isGreaterThan(score);
        resetStoredMatch();

    }

    /**
     * This method must be used after each verification so that it a subsequent tests will not be
     * affected by past results.
     */
    private void resetStoredMatch() {
        log.debug("Resetting stored Match object to null.");
        setStoredMatch(null);

    }

    private void eventFired(ObserveEvent event) {
        
        log.info("EVENT FIRED --> {}", event);
        log.info("Setting ObservationOutcome to True");
        setObservationOutcome(true);
        log.info("Stopping observation for event --> {}", event);
        region.stopObserver("Stopping observation on this region");
        
        assertThat(region.isObserving()).isFalse();

    }

}
