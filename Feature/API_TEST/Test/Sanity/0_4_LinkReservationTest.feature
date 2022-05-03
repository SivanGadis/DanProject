@test111



Feature: User account tests

  
@LinkReservation
   Scenario: Link reservation to an existing user
           
            And open excel file from config "ExcelFile"
            
           	And I wait for "3000" milliseconds
           	
            And I get value from excel for "13970"
           
           	And I wait for "3000" milliseconds
           	
            When I prepare reservation data by "date"
            
            Then I choose API action "post" and send response
           
            Then I unlink reservation with reservation ID