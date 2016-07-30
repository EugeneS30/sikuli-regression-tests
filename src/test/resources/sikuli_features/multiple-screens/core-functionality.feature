@Core @MultipleScreen
Feature: Multiple Screens Core Functionality

  Scenario: All screens have been detected
    Given there are 2 screens available
    Then there are 2 screens detected
    
  #Scenario: All Screens size have been detected
  #  Given there are 2 screens available
  #  Then screens sizes detected properly