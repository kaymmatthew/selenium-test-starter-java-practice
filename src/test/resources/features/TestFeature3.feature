# TASK3: Create tests from the acceptance criteria

#  Navigate to the AWRS UI(https://www.tax.service.gov.uk/check-the-awrs-register)
#  Search for a URN XXAW 000 0044 5456
#  Not found page is returned
#  search for XXAA 000 0044 5444
#  Invalid page is returned

Feature: Validate AWRS register search
  @test3
  Scenario: Search for a URN that is not found
    Given I go to url "https://www.tax.service.gov.uk/check-the-awrs-register?utm_source=chatgpt.com"
    When I search for AWRS URN "XXAW 000 0044 5456"
    And I click the continue button
    Then I should see the "not found" page

  Scenario: Search for an invalid URN
    Given I go to url "https://www.tax.service.gov.uk/check-the-awrs-register?utm_source=chatgpt.com"
    When I search for AWRS URN "XXAA 000 0044 5444"
    And I click the continue button
    Then I should see the "invalid" page