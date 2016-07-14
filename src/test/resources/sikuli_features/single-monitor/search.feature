@Search
Feature: Search

  Scenario: Pattern is found using wait()
    Given I have navigated to a blank page
    When the pattern "QuickStartPlain.png" does not exist on the screen
    And I am navigating to "http://www.sikulix.weebly.com/"
    And I wait for pattern "QuickStartPlain.png"
    Then the pattern "QuickStartPlain.png" exists on the screen