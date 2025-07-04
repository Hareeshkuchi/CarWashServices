# Car Wash Services Search.feature
  @Carwash
Feature: Car Wash Services Identification

  As a user looking for car wash services
  I want to find top-rated services near my location
  So that I can choose a reliable service

  Scenario Outline: Display top 5 car wash services meeting criteria
    Given I am on the Justdial website
    When I specify the Location as detected location
#    And I search for "Car Washing Services"
    And I search for "<serviceIndex>"
#    And I apply filters for Rating > "4" stars and Customer Votes > "20"
    And I apply filters for Rating > "<ratingIndex>" stars and Customer Votes > "<ratersIndex>"
#    Then I should see a list of "5" car washing services
    Then I should see a list of "<numberOfResultsToShow>" car washing services
    Examples:
      |serviceIndex|numberOfResultsToShow|ratingIndex|ratersIndex|
      |1           |4                    |2          |3          |

