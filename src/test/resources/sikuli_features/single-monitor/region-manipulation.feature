@Region
Feature: Region Manipulation

  Scenario: Region matches pattern file dimensions
    Given I have navigated to "http://sikulix.weebly.com/"
    When the pattern "QuickStartPlain.png" exists on the screen
    Then the "QuickStartPlain.png" pattern dimensions match the created Region 
  
  Scenario Outline: Expand Region <direction>
    Given I have navigated to "http://sikulix.weebly.com/"
    When the pattern "QuickStartPlain.png" exists on the screen
    And I define the pattern "QuickStartPlain.png" as the Region
    And I expand the Region "<direction>"
    Then the resulting Region is "<result>" the original Region not including it
    
    Examples:
    | direction | result |
    | up        | above  |
    | down      | below  |
    | right     | right  |
    | left      | left   |