package com.eugenes.functional.dev;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

@Slf4j
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DevTestArea {

	String imagePath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\patterns\\GetWin10SysBar.png";
	File image = new File(imagePath);
	Screen s = new Screen();

	@Before
	public void setUp() {
		ImagePath.setBundlePath(image.getParent());
	}
	
	@Test
	public void pointTest() {
		log.info("{},{}", Screen.getScreen(1).x, Screen.getScreen(1).y);
		Point p = new Point(Screen.getScreen(1).x, Screen.getScreen(1).y);
	}
	
	@Test
	public void browserMove() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setPosition(new Point(Screen.getScreen(1).x, Screen.getScreen(1).y));
		System.out.println("sdasda");
	}
	
	@Test
	public void multiScreenDetect(){
		
		Screen.getNumberScreens();
		log.info("Screens detected: {}", Screen.getNumberScreens());
		
		Screen screen1 = Screen.getScreen(0);
		Screen screen2 = Screen.getScreen(1);

		Screen.all();

	}

	
	@Test
	public void multiSceenTest1() {

		Region reg = Screen.all();
		reg.highlight(1);

	}

	@Test
	public void multiSceenTest() {

		List<Screen> screens = new ArrayList<Screen>();

		screens.add(new Screen(0));
		screens.add(new Screen(1));

		for (Screen screen : screens) {
			screen.highlight(1);
		}
		// screens.stream().forEach(highlight(1));
	}

	@Test
	public void testCoordinates() throws FindFailed {

		// Region reg = s.find("GetWin10SysBar.png");
		// log.info(reg.getRect().toString());

		log.info(Integer.toString(s.getH()));

	}

	@Test
	public void TestA() throws IOException {

		BufferedImage bimg = ImageIO.read(image);

		log.info("Actual image width: {}", bimg.getWidth());
		log.info("Actual image height: {}", bimg.getHeight());
	}

	@Test
	public void TestB() throws FindFailed {

		Region r = s.find("GetWin10SysBar.png");

		log.info("Detected image width: {}", r.getW());
		log.info("Detected image height: {}", r.getH());

		r.above().highlight(1);
		// r.below().highlight(1);
		// r.above(50).highlight(1);
		//
		// r.highlight(1);

	}

	@Ignore
	@Test
	public void highlightTest() throws FindFailed {

		ImagePath.setBundlePath("C:/temp/");
		Settings.MinSimilarity = 0.50;
		String startBtn = "win7StartBtn.png";
		String calcIcon = "calcIconSmall.png";
		String football = "footballImg.png";

		Screen s = new Screen(1);
		// Match m = s.find(calcIcon);
		// m.highlight(3);
		// Match m1 = s.wait(imageName);

		for (Iterator<Match> list = s.findAll(football); list.hasNext();) {
			Match m = list.next();
			m.highlight(5);
		}

		// for (Match m : s.findAll(football)) {
		// m.highlight(1);
		// }

	}
}
