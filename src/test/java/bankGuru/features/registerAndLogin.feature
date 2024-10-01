@Customer
Feature: Register/Login and Create new customer

@Register_login
Scenario: Register to system and login
Given Get Login page url
When Open Register page
And Input Email
And Click Submit
Then Get UserID and Password infor
When Return to Login page
And Submit valid infor to Login form
Then Home page displayed

@New_customer
Scenario Outline: New customer
	Given Click "New Customer" link 
	When Input to "Customer Name" textbox with value "<CustomerName>"
	And Input to radio button with value "f"
	And Input to "Date of Birth" textbox with value "<DobIn>"
	And Input to "Address" textarea with value "<Address>"
	And Input to "City" textbox with value "<City>"
	And Input to "State" textbox with value "<State>"
	And Input to "PIN" textbox with value "<Pin>"
	And Input to "Mobile Number" textbox with value "<MobileNumber>"
	And Input to "E-mail" textbox with value "<Email>"
	And Input to "Password" textbox with value "<Password>"
	And Click "Submit" button
	Then "Customer Registered Successfully!!!" message is displayed
	And "Customer Name" is displayed correctly with value "<CustomerName>"
	And "Gender" is displayed correctly with value "<Gender>"
	And "Birthdate" is displayed correctly with value "<DobOut>"
	And "Address" is displayed correctly with value "<Address>"
	And "City" is displayed correctly with value "<City>"
	And "State" is displayed correctly with value "<State>"
	And "Pin" is displayed correctly with value "<Pin>"
	And "Mobile No." is displayed correctly with value "<MobileNumber>"
	And "Email" is displayed correctly with value "<Email>"
	
	Examples:
	| CustomerName | DobIn | DobOut | Address | City | State | Pin | MobileNumber | Password | Gender | Email |
	| Thao | 01/12/1994 | 1994-01-12 | 123 DMC | HCM | Tan Phu | 700000 | 0974774777 | 123456 | female | thaodo@gmail.com |