package com.eugenes.functional;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Manual runner.
 * Will only execute the scenarios that are tagged with @tags 
 * 
 * @author eugene.shragovich
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:sikuli_features"}) //, tags = {"@wip"})


public class FunctionalDevIT {
    

}
