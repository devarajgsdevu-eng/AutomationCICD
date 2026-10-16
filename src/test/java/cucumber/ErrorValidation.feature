@tag
Feature: Error Validation

@ErrorValidation
Scenario Outline: Nagative Test for login validation
Given I landed on Ecommerce page
When  logged in with username <name> and password<password>
Then  "Incorrect email or password." message is displayed.

Examples:
|   					name									|					password		|
|devaraj.s@happiestminds.com	| 			Welcome@123	|