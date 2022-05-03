#Author: tommy.berman@qualitestgroup.com
#Keywords Summary : DAN HOTEL, SignUp, SendOTP, CheckOTP, Sign In ,SignOUT, LinkReservation, Unlink Reservation.

@Test11111
Feature: Flow Test for Ten API of Dan Hotel

#  @tag1
  Scenario: All Tests for QA Developr
           
           #Given I SignUp new user with random email
           
           #When I SignUp new user for email "dantest4@mailinator.com"
           
           Then I send OTP for email "dantest4@mailinator.com"

           And I wait for "4000" milliseconds
           
           And I go to mailinator and get OTP for email "https://www.mailinator.com/v4/public/inboxes.jsp?to=dantest4"
           
           Then I check OTP for Token with email "dantest4@mailinator.com"
           
           Given Update User Profile and Attach User Picture
           
            And open excel file from config "ExcelFile"
            
           	And I wait for "3000" milliseconds
           	
            And I get value from excel for "14004"
            #And I get value from excel for "Autostar1711"
           
           	And I wait for "3000" milliseconds
           	
            When I prepare reservation data by "number"
            
            Then I choose API action "post" and send response
            
            Given Upload signature
            
            Given CreditCard authorization
            
            Given Pre check in
           
            #Then I unlink reservation with reservation ID
           
            Then I Sign out

