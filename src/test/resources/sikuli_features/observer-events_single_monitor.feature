@Search @Core
Feature: Search

  @wip
  Scenario: Region.observe() fires on onAppear() event
    Given the pattern "QuickStartPlain.png" does not exist on the screen
    When I observe the screen for pattern "QuickStartPlain.png" to "appear" 
    And I am navigating to "http://sikulix.weebly.com/"
    Then the pattern "QuickStartPlain.png" exists on the screen
    And the pattern matches with score 0.9
    And the event have been fired
        
  Scenario: Region.observe() fires on onVanish() event
    Given I have navigated to "http://sikulix.weebly.com/"
    Then the pattern "QuickStartPlain.png" exists on the screen
    When I observe the screen for pattern "QuickStartPlain.png" to "vanish"
    And navigate to a blank page
    Then the pattern "QuickStartPlain.png" does not exist on the screen
    And the event have been fired
    
  Scenario: Region.observe() fires on onChanged() event
    Given the pattern "QuickStartPlain.png" is not visible on the screen
    And I observe the screen for pattern "QuickStartPlain.png" to "change"
    When I navigate to "http://sikulix.weebly.com/"
    Then the pattern "QuickStartPlain.png" is visible on the screen
    And the event have been fired