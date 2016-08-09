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
import com.eugenes.functional.glue.steps.PatternSearchSteps;

/**
 *
 * Test class to aid the productivity of developing new scenarios.
 *
 * 
 * @author eugene.shragovich
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = { "local" })
@ContextConfiguration(classes = { WebDriverConfiguration.class, SikuliConfiguration.class })
@SuppressWarnings("unused")
@Slf4j
public class DevelopScenario {
	
	@Inject
	private Screen screen;
	
	@Inject
	private WebDriver driver;
	
	@Inject
	private PatternSearchSteps patternSearchSteps;

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
	
	@Test
	public void regionManipulation() throws Throwable {
		driver.get("http://sikulix.weebly.com/");
		patternSearchSteps.the_pattern_maybe_visible_on_the_screen("QuickStartPlain.png", "exists");
		patternSearchSteps.i_expand_the_region("up");
	}

}
