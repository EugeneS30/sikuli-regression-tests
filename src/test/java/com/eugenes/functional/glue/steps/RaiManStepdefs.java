package com.eugenes.functional.glue.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.sikuli.basics.Settings;
import org.sikuli.script.Screen;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * corresponding to sikuli_features/multiple-monitors/core-functionality-raiman.feature
 * which originally was sikuli_features/multiple-monitors/core-functionality.feature
 */
public class RaiManStepdefs {

  private static GraphicsDevice[] availableMonitors = null;
  private static int primaryMonitorID = -1;

  static {
    availableMonitors = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
    int id = 0;
    for (GraphicsDevice monitor : availableMonitors) {
      if (monitor.getDefaultConfiguration().getBounds().getX() == 0 && monitor.getDefaultConfiguration().getBounds().getY() == 0) {
        primaryMonitorID = id;
        break;
      }
      id++;
    }
  }

  @Given("^there is at least one monitor available raiman$")
  public void thereIsAtLeastMonitorAvailableRaiman() throws Throwable {
    assertTrue(availableMonitors.length > 0);
  }

  @Then("^all monitors have been detected raiman$")
  public void allMonitorsHaveBeenDetectedRaiman() throws Throwable {
    assertEquals(Screen.getNumberScreens(), availableMonitors.length);
  }

  @Then("^the primary monitor has been detected correctly raiman$")
  public void thePrimaryMonitorHasBeenDetectedCorrectlyRaiman() throws Throwable {
    assertTrue(Screen.getPrimaryScreen().x == 0 && Screen.getPrimaryScreen().y == 0);
    assertEquals(Screen.getPrimaryScreen().w, (int) availableMonitors[primaryMonitorID].getDefaultConfiguration().getBounds().getWidth());
    assertEquals(Screen.getPrimaryScreen().h, (int) availableMonitors[primaryMonitorID].getDefaultConfiguration().getBounds().getHeight());
  }

  @Then("^all monitor sizes have been detected correctly raiman$")
  public void allMonitorsSizesHasBeenDetectedCorrectlyRaiman() throws Throwable {
    for (int i = 0; i < Screen.getNumberScreens(); i++) {
      if (i < primaryMonitorID) {
        assertEquals(Screen.getScreen(i).x, (int) availableMonitors[i].getDefaultConfiguration().getBounds().getX());
        assertEquals(Screen.getScreen(i).y, (int) availableMonitors[i].getDefaultConfiguration().getBounds().getY());
        assertEquals(Screen.getScreen(i).w, (int) availableMonitors[i].getDefaultConfiguration().getBounds().getWidth());
        assertEquals(Screen.getScreen(i).h, (int) availableMonitors[i].getDefaultConfiguration().getBounds().getHeight());
      } else if (i == primaryMonitorID) {
        continue;
      } else {
        assertEquals(Screen.getScreen(i).x, (int) availableMonitors[i + 1].getDefaultConfiguration().getBounds().getX());
        assertEquals(Screen.getScreen(i).y, (int) availableMonitors[i + 1].getDefaultConfiguration().getBounds().getY());
        assertEquals(Screen.getScreen(i).w, (int) availableMonitors[i + 1].getDefaultConfiguration().getBounds().getWidth());
        assertEquals(Screen.getScreen(i).h, (int) availableMonitors[i + 1].getDefaultConfiguration().getBounds().getHeight());
      }

    }
  }

  @Then("^the mouse can be moved to each monitor center raiman$")
  public void theMouseCanBeMovedToEachMonitorCenterRaiman() throws Throwable {
    float delay = Settings.MoveMouseDelay;
    Settings.MoveMouseDelay = 0;
    for (int i = 0; i < Screen.getNumberScreens(); i++) {
      Screen screen = Screen.getScreen(i);
      screen.hover();
      assertEquals(screen.getCenter().x, MouseInfo.getPointerInfo().getLocation().x);
      assertEquals(screen.getCenter().y, MouseInfo.getPointerInfo().getLocation().y);
    }
    Settings.MoveMouseDelay = delay;
  }
}
