

Feature: Link Reservation

  
@RegressionLinkReservationByDate
   Scenario: Link reservation to an existing user by date
           
            And open excel file from config "ExcelFile"
            
           	And I wait for "3000" milliseconds
           	
            And I get value from excel for "Autostar1890"
           
           	And I wait for "3000" milliseconds
           	
            When I prepare reservation data by "date"
            
            Then I choose API action "post" and send response