Feature: Modify accounts

	Background: 
	Given driver is initialized3
	    
 	Scenario: Modify account with valid data
 		Given user fell to account page
    And user clicks on modify account
    When user enters new data and saves
   	Then account gets correctly modified
   	
   	Scenario: Modify account with invalid employee number data
 		Given user fell to account page
    And user clicks on modify account
    When user enters new invalid employee number
   	Then invalid employee message is displayed