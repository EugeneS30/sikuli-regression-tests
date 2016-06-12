@Search
Feature: Search

  @wip
  Scenario: Pattern is found using wait()
    Given the pattern "QuickStartPlain.png" does not exist on the screen
    And I wait for pattern "QuickStartPlain.png"
    When I navigate to "http://www.sikulix.com/"
    Then the pattern "QuickStartPlain.png" exists on the screen
    Then the pattern "QuickStartPlain.png" is found