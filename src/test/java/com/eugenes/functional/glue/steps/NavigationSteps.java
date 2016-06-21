package com.eugenes.functional.glue.steps;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

@Slf4j
public class NavigationSteps extends AbstractSteps {

    @Autowired
    private WebDriver driver;

    @When("^I navigate to \"(.*?)\"$")
    @Given("^I have navigated to \"(.*?)\"$")
    public void i_have_navigated_to(String url) throws Throwable {
        
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }

        

    }

    /**
     * This step is to be used for navigation where we do not want to wait until the page is fully
     * loaded.
     */
    @When("^I am navigating to \"(.*?)\"$")
    public void i_am_navigating_to(String url) throws Throwable {

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                try {

                    driver.get(url);

                } catch (UnreachableBrowserException e) {

                    log.info("The test probably finished while driver was still in the process of retrieving a URL in a separate thread");

                }

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
