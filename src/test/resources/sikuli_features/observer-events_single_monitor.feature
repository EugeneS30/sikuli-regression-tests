@Search @Core
Feature: Search

  Scenario: Region.observe() fires on onAppear() event
    Given the pattern "QuickStartPlain.png" is not visible on the screen
    When I observe the screen for pattern "QuickStartPlain.png" to "appear" 
    And I navigate to "http://sikulix.weebly.com/"
    Then the pattern "QuickStartPlain.png" is visible on the screen
    And the event fires
        
  Scenario: Region.observe() fires on onVanish() event
    Given I have navigated to "http://sikulix.weebly.com/"
    And the pattern "QuickStartPlain.png" is visible on the screen
    When I observe the screen for pattern "QuickStartPlain.png" to "vanish"
    And navigate to a blank page
    Then the event fires
    
  Scenario: Region.observe() fires on onChanged() event
    Given the pattern "QuickStartPlain.png" is not visible on the screen
    And I observe the screen for pattern "QuickStartPlain.png" to "change"
    When I navigate to "http://sikulix.weebly.com/"
    Then the pattern "QuickStartPlain.png" is visible on the screen
    And the event fires