@RaiMan
Feature: Multiple Monitors Core Functionality

  Scenario: All monitors have been detected
    Given there is at least one monitor available raiman
    Then all monitors have been detected raiman
    And the primary monitor has been detected correctly raiman
    And all monitor sizes have been detected correctly raiman
    And the mouse can be moved to each monitor center raiman
