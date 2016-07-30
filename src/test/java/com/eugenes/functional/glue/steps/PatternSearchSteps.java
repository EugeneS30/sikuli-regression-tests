package com.eugenes.functional.glue.steps;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.ObserveEvent;
import org.sikuli.script.ObserverCallBack;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.SikuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eugenes.functional.data.RegionExpandDirection;
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
public class PatternSearchSteps extends AbstractSteps {

	@Autowired
	private Screen screen;

	@Inject
	private SikuliSupport sikuli;

	@Inject
	private FluentWait<WebDriver> wait;
	
	@Inject
	private WebDriver driver;

	// If the pattern was found by one of the steps it will be stored in this
	// variable.
	@Setter
	private Match storedMatch;

	@Setter
	private Pattern storedPattern;

	@Setter
	private boolean observationOutcome;

	@Setter
	@Getter
	private Region region;

	@Setter
	@Getter
	private Region relativeRegion;

	@Value("${sikuli.observeTimeout.time:100}")
	private int observeTimeout;

	@Value("${sikuli.waitTimeout.time:10}")
	private float waitTimeout;

	// Threshold value in pixels
	@Value("${sikuli.onChange.threshold:50}")
	private int onChangeThreshold;

	@Given("^I am using screen number (\\d+)$")
	public void i_am_using_screen_number(int screenNumber) throws SikuliException {
		if (screenNumber > Screen.getNumberScreens()) {
			throw new SikuliException("Invalid screen");
		}

		/**
		 * Here we denote the screens as 1 and 2 but in Sikuli, the default
		 * Screen is 0 and the secondary one is 1 accordingly.
		 */
		screen = Screen.getScreen(screenNumber - 1);
		
	}
	
	@When("^I relocate browser window to screen number (\\d+)$")
	public void i_relocate_the_screen_to_window(final int screenNumber) {
		
		driver.manage().window().setPosition(new Point(Screen.getScreen(screenNumber-1).x, Screen.getScreen(screenNumber-1).y));
		driver.manage().window().maximize();
		
		
	}
	

	@Then("^there (?:are|is) (\\d+) screen(?:s)? detected$")
	public void there_are_screens_detected(int availableScreensNumber)
			throws Throwable {

		assertThat(Screen.getNumberScreens()).isEqualTo(availableScreensNumber);

	}

	@Given("^the pattern \"(.*?)\" does not exist on screen (\\d+)$")
	public void the_pattern_does_not_exist_on_screen(String arg1, int arg2)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I wait for pattern \"(.*?)\" on screen (\\d+)$")
	public void i_wait_for_pattern_on_screen(String arg1, int arg2)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^the pattern \"(.*?)\" exists on screen (\\d+)$")
	public void the_pattern_exists_on_screen(String arg1, int arg2)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I wait for pattern \"([^\"]*)\"$")
	public void i_wait_for_pattern(final String patternName) throws FindFailed {

		log.info("Waiting for pattern: " + patternName + ". . .");

		// setStoredPattern(new Pattern(patternName));
		setStoredMatch(screen.wait(patternName, waitTimeout));

	}

	@When("^I expand the Region \"(.*?)\"$")
	public void i_expand_the_region(final String direction) {

		assertThat(region).isNotNull();

		switch (RegionExpandDirection.fromString(direction)) {

		case UPWARDS:
			log.info("up");
			setRelativeRegion(region.above());
			relativeRegion.highlight(1);

			break;

		case DOWNWARDS:
			log.info("down");
			setRelativeRegion(region.below());

			break;

		case LEFT:
			log.info("left");
			setRelativeRegion(region.left());

			break;

		case RIGHT:
			log.info("right");
			setRelativeRegion(region.right());
			break;

		default:
			throw new NoSuchElementException("sdsd");
		}

	}

	@Then("^the resulting Region is \"(.*?)\" the original Region (including|not including) it$")
	public void resulting_Region_is_relative_to_the_original_Region(
			final String relation, final String maybe) {

		boolean including = "including".equals(maybe);

		Rectangle originalRegionRect = region.getRect();
		Rectangle relativeRegionRect = relativeRegion.getRect();

		if (!including) {

			if ("above".equals(relation)) {

				assertThat(relativeRegionRect.getMinY()).isEqualTo(0);
				assertThat(relativeRegionRect.getMaxY()).isEqualTo(
						originalRegionRect.getMinY());
				assertThat(originalRegionRect.getMinX()).isEqualTo(
						relativeRegionRect.getMinX());
				assertThat(originalRegionRect.getMaxX()).isEqualTo(
						relativeRegionRect.getMaxX());

			} else if ("below".equals(relation)) {

				assertThat(relativeRegionRect.getMaxY()).isEqualTo(
						screen.getH());
				assertThat(relativeRegionRect.getMinY()).isEqualTo(
						originalRegionRect.getMaxY());
				assertThat(originalRegionRect.getMinX()).isEqualTo(
						relativeRegionRect.getMinX());
				assertThat(originalRegionRect.getMaxX()).isEqualTo(
						relativeRegionRect.getMaxX());

			} else if ("right".equals(relation)) {

				assertThat(relativeRegionRect.getMaxX()).isEqualTo(
						screen.getW());
				assertThat(originalRegionRect.getMaxX()).isEqualTo(
						relativeRegionRect.getMinX());
				assertThat(relativeRegionRect.getMinY()).isEqualTo(
						originalRegionRect.getMinY());
				assertThat(relativeRegionRect.getMaxY()).isEqualTo(
						originalRegionRect.getMaxY());

			} else if ("left".equals(relation)) {

				assertThat(relativeRegionRect.getMinX()).isEqualTo(0);
				assertThat(originalRegionRect.getMinX()).isEqualTo(
						relativeRegionRect.getMaxX());
				assertThat(relativeRegionRect.getMinY()).isEqualTo(
						originalRegionRect.getMinY());
				assertThat(relativeRegionRect.getMaxY()).isEqualTo(
						originalRegionRect.getMaxY());

			} else {

				throw new UnsupportedOperationException(
						"Relative direction not supported");

			}

		} else if (including) {

			if ("above".equals(relation)) {

				assertThat(relativeRegionRect.getMinY()).isEqualTo(0);
				assertThat(relativeRegionRect.getMaxY()).isEqualTo(
						originalRegionRect.getMaxY());
				assertThat(originalRegionRect.getMinX()).isEqualTo(
						relativeRegionRect.getMinX());
				assertThat(originalRegionRect.getMaxX()).isEqualTo(
						relativeRegionRect.getMaxX());

			} else if ("below".equals(relation)) {

				assertThat(relativeRegionRect.getMaxY()).isEqualTo(
						screen.getH());
				assertThat(relativeRegionRect.getMinY()).isEqualTo(
						originalRegionRect.getMinY());
				assertThat(originalRegionRect.getMinX()).isEqualTo(
						relativeRegionRect.getMinX());
				assertThat(originalRegionRect.getMaxX()).isEqualTo(
						relativeRegionRect.getMaxX());

			} else if ("right".equals(relation)) {

				assertThat(relativeRegionRect.getMaxX()).isEqualTo(
						screen.getW());
				assertThat(originalRegionRect.getMinX()).isEqualTo(
						relativeRegionRect.getMinX());
				assertThat(relativeRegionRect.getMinY()).isEqualTo(
						originalRegionRect.getMinY());
				assertThat(relativeRegionRect.getMaxY()).isEqualTo(
						originalRegionRect.getMaxY());

			} else if ("left".equals(relation)) {

				assertThat(relativeRegionRect.getMinX()).isEqualTo(0);
				assertThat(originalRegionRect.getMaxX()).isEqualTo(
						relativeRegionRect.getMaxX());
				assertThat(relativeRegionRect.getMinY()).isEqualTo(
						originalRegionRect.getMinY());
				assertThat(relativeRegionRect.getMaxY()).isEqualTo(
						originalRegionRect.getMaxY());

			} else {

				throw new UnsupportedOperationException(
						"Relative direction not supported");

			}

		} else {
			throw new UnsupportedOperationException();
		}

	}

	@When("^I observe the screen for pattern \"(.*?)\" to \"(.*?)\"$")
	public void i_observe_the_screen_for_pattern_to(final String pattern,
			final String eventType) throws Throwable {
		log.info("Setting up region to observe for event: {}", eventType);

		log.info("Setting initial outcome to False");
		setObservationOutcome(false);

		// new Screen instance is required for each observation since stopping
		// an observer
		// deactivates ALL running observations and starting an observer
		// activates ALL registered
		// events for the observed region
		region = new Screen();

		log.info("Checking event type");
		switch (ObserverEvent.fromString(eventType)) {

		case ON_APPEAR:
			log.info("Detected event type --> {}", eventType);

			log.info("Registering callback");
			region.onAppear(pattern, new ObserverCallBack() {

				@Override
				public void appeared(ObserveEvent e) {
					eventFired(e);
				}
			});

			break;

		case ON_VANISH:
			log.info("Detected event type --> {}", eventType);

			log.info("Registering callback");
			region.onVanish(pattern, new ObserverCallBack() {

				@Override
				public void vanished(ObserveEvent e) {
					eventFired(e);
				}
			});

			break;

		case ON_CHANGE:
			log.info("Detected event type --> {}", eventType);

			log.info("Registering callback");
			region.onChange(onChangeThreshold, new ObserverCallBack() {

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

	@Given("^I define the pattern \"(.*?)\" as the Region$")
	public void i_define_pattern_as_region(final String pattern)
			throws FindFailed {

		setRegion(screen.find(pattern));

	}

	@Given("^the pattern \"(.*?)\" (exists|does not exist) on the screen$")
	public void the_pattern_maybe_visible_on_the_screen(final String pattern,
			final String maybe) throws Throwable {

		log.info("Checking pattern existence");

		boolean isShown = "exists".equals(maybe);

		if (isShown) {
			log.info("Expecting pattern to exist on the screen");

			// resetStoredMatch();
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
	 * WARNING: This step will reset the stored Match to avoid false positives
	 * in subsequent verification steps.
	 */
	@Then("^the pattern matches with score (\\d+\\.\\d+)$")
	public void the_pattern_matches_with_score(final double score)
			throws Throwable {

		assertThat(storedMatch.getScore()).isGreaterThan(score);
		resetStoredMatch();

	}

	@Then("^the \"(.*?)\" pattern dimensions match the created Region$")
	public void pattern_dimensions_match_the_created_Region(
			final String patternFile) throws IOException {

		String imagePath = System.getProperty("user.dir")
				+ "/src/test/resources/patterns/" + patternFile;
		BufferedImage image = ImageIO.read(new File(imagePath));

		assertThat(image.getHeight()).isEqualTo(storedMatch.getH());
		assertThat(image.getWidth()).isEqualTo(storedMatch.getW());

	}

	@When("^I reset the stored match$")
	public void i_reset_stored_match() {

		try {
			setStoredMatch(null);
		} catch (Exception e) {
			log.error("Stored Match was NOT reset!");
		}

	}

	@When("^I print a stored match$")
	public void i_print_a_stored_match() {
		System.out.println(storedMatch);
	}

	/**
	 * This method must be used after each verification so that it a subsequent
	 * tests will not be affected by past results.
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
