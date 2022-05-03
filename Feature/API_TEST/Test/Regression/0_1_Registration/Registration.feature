



Feature: Registration of a new user

  
@RegressionRegistration
   Scenario: Registration of non-existing user
						
						Given I SignUp new user with random email
						
            #When I SignUp new user for email ""
           