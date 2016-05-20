package com.eugenes.functional.glue.steps;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.eugenes.functional.config.SikuliConfiguration;
import com.eugenes.functional.config.WebDriverConfiguration;

@ContextConfiguration(classes = {WebDriverConfiguration.class, SikuliConfiguration.class})
public class AbstractSteps {
    
}
