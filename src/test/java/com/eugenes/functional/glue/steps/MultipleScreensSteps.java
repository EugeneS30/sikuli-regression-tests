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
public class MultipleScreensSteps extends AbstractSteps {

    @Autowired
    private Screen screen;

    @Inject
    private SikuliSupport sikuli;

    @Inject
    private FluentWait<WebDriver> wait;

    @Inject
    private WebDriver driver;

    private GraphicsDevice[] getGraphicsDevices() {

        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        return g.getScreenDevices();

    }

    @Given("^there (?:are|is) (\\d+) screen(?:s)? available$")
    public void there_are_screens_available(int availableScreensNumber) throws Throwable {

        assertThat(availableScreensNumber).isEqualTo(getGraphicsDevices().length);

    }
    
    @Then("^screens sizes detected properly$")
    public void primazry_monitor_set() {
        
        Screen.getPrimaryId();
        
    }

    @Then("^screens sizes detected properly$")
    public void screens_sizes_detected_properly() {
        
        

        // for (int i = 0; i < devices.length; i++) {
        // System.out.println("Width:"
        // + devices[i].getDisplayMode().getWidth());
        // System.out.println("Height:"
        // + devices[i].getDisplayMode().getHeight());
        // }

    }

}
