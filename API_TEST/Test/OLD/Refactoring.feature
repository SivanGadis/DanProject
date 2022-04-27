#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@Test11111
Feature: Flow Test for Ten API of Dan Hotel

#  @tag1
  Scenario: Registration of a new user 
           
           #Given I SignUp new user with random email
           
           #When I SignUp new user for email "tommybbb@mailinator.com"
           
           Then I send OTP for email "tommybb@mailinator.com"

           And I wait for "4000" milliseconds
           
           And I go to mailinator and get OTP for email "https://www.mailinator.com/v4/public/inboxes.jsp?to=tommybb"
           
           Then I check OTP for Token with email "tommybb@mailinator.com"
           
           #Given Update User Profile and Attach User Picture
           
           #Then I Sign out
           
            And open excel file from config "ExcelFile"
            
           	And I wait for "3000" milliseconds
           	
            And I get value from excel for "13970"
           
           	And I wait for "3000" milliseconds
           	
            When I prepare reservation data by "date"
            
            Then I choose API action "post" and send response
           
            Then I unlink reservation with reservation ID
           
#    And some other precondition
#    When I complete action
#    And some other action
#    And yet another action
#    Then I validate the outcomes
#    And check more outcomes

#  @tag2
#  Scenario Outline: Title of your scenario outline
#    Given I want to write a step with <name>
#    When I check for the <value> in step
#    Then I verify the <status> in step

#    Examples: 
#      | name  | value | status  |
#      | name1 |     5 | success |
#      | name2 |     7 | Fail    |
