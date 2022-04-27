

Feature: Link Reservation

  
@RegressionLinkReservationByNumber
   Scenario: Link reservation to an existing user by number
           
            And open excel file from config "ExcelFile"
            
           	And I wait for "3000" milliseconds
           	
            And I get value from excel for "13987"
           
           	And I wait for "3000" milliseconds
           	
            When I prepare reservation data by "number"
            
            Then I choose API action "post" and send response