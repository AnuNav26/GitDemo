
@tag
Feature: Error Validation
  I want to use this template for my feature file



  @ErrorValidation
  Scenario Outline: Verify error validation providing incorrect username or password
   Given I landed on ecommerce page
   When logged in with username <username> and password <password>
   Then "Incorrect email or password." error message is displayed

    Examples: 
  Examples: 
      | username  								| password |
      | SanviNavin2612@gmail.com  | Sanu26122| 
      | sanvinavin0308@gmail.com  | Sanu1308 | 
