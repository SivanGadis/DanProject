@CheckOTP



Feature: Test

  
@RegressionCheckOTP
   Scenario: CheckOTP

           And I go to mailinator and get OTP for email "https://www.mailinator.com/v4/public/inboxes.jsp?to=tommybb"
           
           Then I check OTP for Token with email ""
           