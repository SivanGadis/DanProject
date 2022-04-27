@CheckOTP



Feature: Test

  

   Scenario: CheckOTP

           #And open excel file from config "ExcelFile"

           #And I wait for "3000" milliseconds

           #And Get value from Row num "1" and Cell num "1"

           #And I wait for "3000" milliseconds

           #And Get value in loop from Row num "1" and Cell num "1"

           #And I wait for "3000" milliseconds
           
           
           And Go to mailinator and get OTP "https://www.mailinator.com/v4/public/inboxes.jsp?to=tommey"
           
           Given Check OTP for Token "tommey@mailinator.com"
           