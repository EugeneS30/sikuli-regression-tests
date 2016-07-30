@Search @SingleScreen
Feature: Search

  Scenario: Pattern is found using wait()
    Given the pattern "QuickStartPlain.png" does not exist on the screen
    When I am navigating to "http://www.sikulix.weebly.com/"
    And I wait for pattern "QuickStartPlain.png"
    Then the pattern "QuickStartPlain.png" exists on the screen