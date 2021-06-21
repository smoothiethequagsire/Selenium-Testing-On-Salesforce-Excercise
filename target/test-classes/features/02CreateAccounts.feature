Feature: Create accounts implementation
    
    Background: 
    Given driver is initialized1
    
 	Scenario: Create accounts
    Given user goes to account page
    And user clicks on new button
    When user enters data and saves
   	Then account gets created
   	
	Scenario: Attempt to create null account
		Given user goes to account page
		And user clicks on new button
		When user clicks on save button
		Then error message is displayed