@Search @MultipleMonitors
Feature: Search MultipleMonitors

  Scenario: Pattern is found on secondary screen using wait()
    Given there are 2 screens available
    And I am using screen number 2
    And I relocate browser window to screen number 2
    And the pattern "QuickStartPlain.png" does not exist on the screen
    When I am navigating to "http://www.sikulix.weebly.com/"
    And I wait for pattern "QuickStartPlain.png"
    Then the pattern "QuickStartPlain.png" exists on the screen