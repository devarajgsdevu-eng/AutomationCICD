@tag
Feature: Purchae the order from Ecommerce website

Background:
Given I landed on Ecommerce page

@Regression
Scenario Outline: Positive Test of Submitting the order
Given logged in with username <name> and password<password>
When I add product <productName> to cart
And  Checkout <productName> and submit the order
Then  "Thankyou for the order." message is displayed on confirmation page.

Examples:
|   					name									|					password		|     productName |
|devaraj.s@happiestminds.com	| 			Welcome@123	|   ZARA COAT 3		|