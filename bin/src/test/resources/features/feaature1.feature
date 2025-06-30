Feature: feature1
  Scenario Outline: User login with different credentials
    Given User is on the login page
    When User enters "<username>" and "<password>"
    And Clicks the login button
    Then User should be on "<expected_page>"

    Examples:
      | username    | password | expected_page |
      | testuser1   | pass123  | Dashboard     |
      | user2       | pass456  | ProfilePage   |
      | invalid     | wrong    | LoginError    |