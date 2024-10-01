@Parameter 
Feature: Facebook Login Page - Parameter

#Background: 
#	Given Open Facebook Application 

@no_param 
Scenario: Login with no parameter 1
	Then Input to Email textbox 
	And Input to Password textbox 
	And Click Submit button
	
@param_mark 
Scenario: Login with parameter 2
	Then Input to Email textbox with automationtesting1@gmail.com 
	And Input to Password textbox with 123456 
	And Click Submit button
	
	
@param_no_mark 
Scenario: Login with parameter 3 
	Then Input to Email textbox with "automationtesting@gmail.com" 
	And Input to Password textbox with "123456" 
	And Click Submit button 
	
@multiple_param 
Scenario: Login with parameter 4
	Then Input to Email textbox with "automationtesting@gmail.com" And Password textbox with "123456" 
	And Click Submit button 
	
