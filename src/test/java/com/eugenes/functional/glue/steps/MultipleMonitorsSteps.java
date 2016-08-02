package com.eugenes.functional.glue.steps;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
import org.apache.regexp.recompile;
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
import com.eugenes.functional.util.MouseControlSupport;
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
public class MultipleMonitorsSteps extends AbstractSteps {

	@Autowired
	private Screen screen;

	@Inject
	private SikuliSupport sikuli;

	@Inject
	private FluentWait<WebDriver> wait;

	@Inject
	private WebDriver driver;
	
	@Inject
	private MouseControlSupport mouse;

	private GraphicsDevice[] getGraphicsDevices() {

		GraphicsEnvironment g = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		return g.getScreenDevices();

	}
	
	@Then("^the mouse can be moved to each monitor center$")
	public void the_mouse_can_be_moved_to_each_monitor_center() {
		
		for (int i=0; i<Screen.getNumberScreens(); i++) {
			Screen screen = new Screen(i);
			mouse.moveToCoordinates(screen.getCenter());
		}

	}

	@Given("^there (?:are|is) (\\d+) monitor(?:s)? available$")
	public void there_are_monitors_available(int availableMonitorsNumber)
			throws Throwable {

		assertThat(availableMonitorsNumber).isEqualTo(
				getGraphicsDevices().length);

	}

	@Given("^there (?:are|is) at least (\\d+) monitor(?:s)? available$")
	public void there_are_at_least_monitors_available(
			int availableMonitorsNumber) throws Throwable {

		assertThat(availableMonitorsNumber).isGreaterThanOrEqualTo(
				availableMonitorsNumber);

	}

	@Given("^there (?:are|is) (\\d+) monitor(?:s)? detected$")
	public void there_are_monitor_detected(int expectedToBeDetected)
			throws Throwable {

		assertThat(Screen.getNumberScreens()).isEqualTo(expectedToBeDetected);

	}

	@Then("^the primary monitor detected correctly$")
	public void primary_monitor_set() {

		assertThat(Screen.getPrimaryScreen().x).isEqualTo(0);
		assertThat(Screen.getPrimaryScreen().y).isEqualTo(0);

	}

	@Then("^monitors sizes detected correctly$")
	public void monitors_sizes_detected_properly() {

		GraphicsDevice[] devices = getGraphicsDevices();

		for (int i = 0; i < devices.length; i++) {

			assertThat(devices[i].getDisplayMode().getHeight()).isEqualTo(
					Screen.getScreen(i).getH());
			assertThat(devices[i].getDisplayMode().getWidth()).isEqualTo(
					Screen.getScreen(i).getW());

		}

	}
}
