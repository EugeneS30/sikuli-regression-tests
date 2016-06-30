package com.eugenes.functional;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.IScreen;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eugenes.functional.config.SikuliConfiguration;
import com.eugenes.functional.config.WebDriverConfiguration;

/**
 *
 * Test class to aid the productivity of developing new scenarios.
 *
 * These are ignored by default, since they are covered by the main test cases
 * anyway, but are here to support development/debugging of particular issues.
 *
 * These test cases will generally expect some manual configuration to have been
 * completed prior to the test and as such may not be repeatable. They are
 * checked in for reference purposes only.
 *
 * @author tim.myerscough
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = { "local" })
@ContextConfiguration(classes = { WebDriverConfiguration.class,
		SikuliConfiguration.class })
@SuppressWarnings("unused")
@Slf4j
public class DevelopScenarioIT {
	
	@Inject
	private Screen screen;
	
	@Inject
	private WebDriver driver;

	@BeforeClass
	public static void configureBrowser() {
		System.setProperty("driver.remote.capability.browser", "firefox");
		System.setProperty("wait.withTimeout.time", "15");
	}

	@Test
	public void test1() throws FindFailed{
//		driver.get("http://sikulix.weebly.com");
		Region region = screen;
		Match m = screen.find("GetWin10SysBar.png");
		m.highlight(1);
		
	}

}
