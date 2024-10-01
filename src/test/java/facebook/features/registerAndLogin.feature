Feature: Register and Login

@Register_login
Scenario: Register to system and login
Given Open Register page
When Input Email
And Click Submit
Then Get UserID and Password infor
When Return to Login page
And Submit valid infor to Login form
Then Home page displayed