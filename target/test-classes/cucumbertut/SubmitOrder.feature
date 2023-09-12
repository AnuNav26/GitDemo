
@tag
Feature: Purchase a product from ecommerce site
  I want to use this template for my feature file

Background:
Given I landed on ecommerce page
  @Regression
  Scenario Outline: User submitting the order
    Given logged in with username <username> and password <password>
    When  I add product <productName> to cart 
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed in confirmation page

    Examples: 
      | username  								| password | productName  	|
      | SanviNavin2612@gmail.com  | Sanu2612 | ZARA COAT 3 		|
      | sanvinavin1308@gmail.com  | Sanu1308 | IPHONE 13 PRO	|
