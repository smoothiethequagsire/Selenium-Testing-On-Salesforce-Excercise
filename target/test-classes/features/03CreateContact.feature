Feature: Create contact implementation
 
    
 	Scenario: Create new contact
 		Given user is on account page
    And name is extracted from an account 
    And user opens Contact tab in new tab
    When user enters lastname using previously extracted name
   	Then contact is created
   	And user goes back to first tab