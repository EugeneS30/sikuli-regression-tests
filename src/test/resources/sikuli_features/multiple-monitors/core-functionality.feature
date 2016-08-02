@Core @MultipleMonitor
Feature: Multiple Monitors Core Functionality

  Scenario: All monitors have been detected
    Given there are 2 monitors available
    Then there are 2 monitors detected
    
  Scenario: All monitors sizes have been detected correctly
    Given there are 2 monitors available
    Then monitors sizes detected correctly
    
  Scenario: Primary monitor has been set correctly
    Given there are 2 monitors available
    And the primary monitor detected correctly
    
  Scenario: Mouse movement to each monitor center
    Given there are at least 2 monitors available
    Then the mouse can be moved to each monitor center 