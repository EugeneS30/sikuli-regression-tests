@Core @MultipleMonitor
Feature: Multiple Monitors Core Functionality

  Scenario: All monitors have been detected
    Given there is at least 1 monitor available
    Then all monitors have been detected
    
  Scenario: All monitors sizes have been detected correctly
    Given there is at least 1 monitor available
    Then all monitors sizes has been detected correctly
    
  Scenario: Primary monitor has been set correctly
    Given there is at least 1 monitor available
    Then the primary monitor has been detected correctly

  Scenario: Mouse movement to each monitor center
    Given there is at least 1 monitor available
    Then the mouse can be moved to each monitor center 