@Datatable
Feature: Facebook Login Page - DataTable
	
@datatable_step 
Scenario Outline: Login with Username <Username> 
#	Given Open Facebook Application 
	Then Input to Email textbox and Password textbox 
		|Username|Password|Address|		
		|<Username>|<Password>|<Address>|
	And Click Submit button 
	#	Then verify displayed infor
	#	|Username|Address|
	#	|<Username>|<Address>|
#	And Close application 
	
	Examples: 
		|Username|Password|Address|City|Phone|
		|automationtesting@gmail.com|123456|13 Le Loi|HCM|0989998888|
		|automationfc@gmail.com|123456|15 Le Loi|HCM|0989997777|
		
@datatable_scenario @data_driven_testing 
Scenario Outline: Datatable in scenario 
#	Given Open Facebook Application 
	Then Input to Email textbox with "<Username>" 
	And Input to Password textbox with "<Password>" 
	And Click Submit button 
#	And Close application 
			
	Examples: 
		|Username|Password|
		|automationfc1@gmail.com|123456|
		|automationfc2@gmail.com|123456|
		|automationfc3@gmail.com|123456|
		|automationfc4@gmail.com|123456|
	