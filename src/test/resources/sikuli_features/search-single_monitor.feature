@Search
Feature: Search

  Scenario: User can wait for an element to appear on a screen using wait()
    When I wait for element "QuickStartPlain.png"
    Then the element "QuickStartPlain.png" exists
    
  Scenario: User can verify whether a pattern exists on a screen using exists()
    When I search for element "QuickStartPlain.png"
    Then the element "QuickStartPlain.png" exists
    
  #Scenario: User can observe the screen to find a pattern using observe()
  #  When I observe the screen for element QuickStartPlain.png
  #  Then the element is located