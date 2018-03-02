Feature: Search feature

  Background: 
    Given I navigate to GMP

  @SmokeTests
  Scenario: Verify Search Page
    When I click on Search icon
    Then I Should View Search Page

  Scenario: Verify if all the Search Entries are seen
    When I click on Search icon
    Then I Should see all the below links
      | Gift Id | Gift Amount (From) | Gift Amount (To) | Gift Date | Master Id | First Name | Last Name | Organization Name | Zip | Fund Code | Alt Transaction Id | Debit Account | Credit Account |

  Scenario: Search for Doners
    When I click on Search icon
    And I search using below Inputs:
      | Gift Amount (From) |   100 |
      | Gift Amount (To)   | 10000 |
    Then I should view Search Results as"9" and should have these below results in First row
      |  | Donor, gift5 | 02/04/2018 | 555.00 |  | 555 | Street5, GU, 55555 |  | 2155 |  |
