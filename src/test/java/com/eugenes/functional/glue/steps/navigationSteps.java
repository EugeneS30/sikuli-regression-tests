package com.eugenes.functional.glue.steps;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class navigationSteps extends AbstractSteps {

	@Autowired
	private WebDriver driver;

	@When("^I navigate to \"(.*?)\"$")
	@Given("^I have navigated to \"(.*?)\"$")
	public void i_have_navigated_to(String url) throws Throwable {

		driver.get(url);

	}

	/**
	 * This step is to be used for navigation where we do not want to wait until
	 * the page is fully loaded.
	 */
	@When("^I am navigating to \"(.*?)\"$")
	public void i_am_navigating_to(String url) throws Throwable {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {

				driver.get(url);

			}
		});

		t.start();

	}

	@Given("^I have navigated to a blank page$")
	@When("^navigate to a blank page$")
	public void navigate_to_blank_page() throws Throwable {

		i_have_navigated_to("about:blank");

	}

}
