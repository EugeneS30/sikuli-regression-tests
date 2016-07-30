package com.eugenes.functional;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Manual runner. Will only execute the scenarios that are tagged with @wip
 * 
 * @author eugene.shragovich
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:sikuli_features"}, format = {"json:target/cucumber-dev/cucumber.json", "pretty"}, tags = {"@wip"})

public class FunctionalDev
{

}
