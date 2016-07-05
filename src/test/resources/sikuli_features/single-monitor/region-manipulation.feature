@Region
Feature: Region Manipulation

  Scenario: Region matches pattern file dimensions
    Given I have navigated to "http://sikulix.weebly.com/"
    When the pattern "QuickStartPlain.png" exists on the screen
    Then the "QuickStartPlain.png" pattern dimensions match the created Region 
  
  @wip
  Scenario: Expand Region upwards
    Given I have navigated to "http://sikulix.weebly.com/"
    When the pattern "QuickStartPlain.png" exists on the screen
    And I expand the Region upward
    Then the resulting Region is above the original Region not including it   