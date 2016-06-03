package com.eugenes.functional;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * 
 * @author eugene.shragovich
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:sikuli_features"}, format = {"json:target/cucumber-dev/cucumber.json", "pretty"})

public class TestRunnerIT
{

}
