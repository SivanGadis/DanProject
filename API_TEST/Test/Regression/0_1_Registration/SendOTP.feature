@SendOTP



Feature: Test

  
@RegressionSendOTP
   Scenario: SendOTP
           
					Then I send OTP for email ""
					
				   And I wait for "7000" milliseconds
           