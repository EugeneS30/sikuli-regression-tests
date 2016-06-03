@Search
Feature: Search

  Background: Open browser and navigate to a page
    Given I have navigated to "http://www.sikulix.com/"

  Scenario: Wait for a pattern to appear on a screen using wait()
    When I wait for pattern "QuickStartPlain.png"
    Then the pattern "QuickStartPlain.png" exists
   
  Scenario: User can observe the screen to find a pattern using observe()
    When I observe the screen for pattern "QuickStartPlain.png"
    Then the pattern "QuickStartPlain.png" exists