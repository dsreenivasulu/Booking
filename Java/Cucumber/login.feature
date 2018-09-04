@Login
Feature: Login to Admin app
	As a user
  I want to login successfully
  So that I can use Admin app to create configuration for Cloud Regression testing

@Regression @Dev @AdminApp
Scenario: LOGIN: login to Lila with valid credentials

	Given User is on admin login page
	When User enters Username "admin" on admin login page
    And User enters default admin Password
	Then User lands on admin home page
	Then user clicks on "Organizations"
	Then user creates new organization "Organizations"

@Regression @Dev
Scenario: LOGIN: login to Lila with wrong password

	Given User is on admin login page
	When User enters Username "admin" on admin login page
    And User enters Password "wrongpass####" on admin login page
	Then User should see incorrect credentials error message on admin login page

@Regression @Dev
Scenario: LOGIN: login to Lila with empty password

	Given User is on admin login page
	When User enters Username "admin" on admin login page
    And User enters Password "" on admin login page
	Then User should see incorrect credentials error message on admin login page
