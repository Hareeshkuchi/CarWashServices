# Fitness Menu Navigation.feature
  @fitness
Feature: Fitness Menu Items Retrieval

  As a user browsing Justdial
  I want to explore fitness related options
  So that I can find specific gym services

  Scenario Outline: Retrieve and display sub-menu items under Fitness > Gym
    Given I am on the Justdial website again
    When I navigate to the Fitness category
    And I then select the Gym sub-category
    Then I should be able to retrieve "<numOfZymsIndex>" displayed sub-menu items
    Examples:
    |numOfZymsIndex|
    |6             |
