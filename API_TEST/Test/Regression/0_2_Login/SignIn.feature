
Feature: Test

  
@RegressionLogin
   Scenario: Login

           Then I send OTP for email "tommybbb@mailinator.com"

           And I wait for "4000" milliseconds
           
           And I go to mailinator and get OTP for email "https://www.mailinator.com/v4/public/inboxes.jsp?to=tommybbb"
           
           Then I check OTP for Token with email "tommybbb@mailinator.com"
 
           