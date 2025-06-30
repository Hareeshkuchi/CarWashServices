# Free Listing Form Submission.feature
  @FreeList
Feature: Free Listing Form Validation

  As a business owner
  I want to register for a free listing
  So that I can promote my services, with proper data validation

  Scenario: Capture error message on invalid phone number submission
    Given I am on the Justdial website's "Free Listing Registration" page
    When I enter an "Invalid Phone Number" like "123"
    And I click the "Submit" button
    Then I should see an "Error Message" related to the invalid phone number
