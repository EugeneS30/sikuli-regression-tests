@Search @Core
Feature: Search

  @wip
  Scenario: Region.observe() fires on onAppear() event
    Given I have navigated to "http://sikulix.weebly.com/"
    And the pattern "QuickStartPlain.png" is visible on the screen 
    When I observe the screen for pattern "QuickStartPlain.png" to "appear"
    Then the event fires
        
  Scenario: Region.observe() fires on onChanged() event
    Given the pattern "QuickStartPlain.png" is not visible on the screen
    And I observe the screen for pattern "QuickStartPlain.png" to "change"
    When I navigate to "http://www.sikulix.com/"
    Then the pattern "QuickStartPlain.png" is visible on the screen
    And the onAppear() event fires
    
  Scenario: Region.observe() fires on onVanish() event
    Given I have navigated to "http://www.sikulix.com/"
    And the pattern "QuickStartPlain.png" is visible on the screen
    When I observe the screen for pattern "QuickStartPlain.png" to "vanish"
    And follow the link ""
    Then onVanish() event fires