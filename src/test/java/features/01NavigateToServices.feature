Feature: Login action implementation

    
 	Scenario: Valid login and navigation action
    Given user is on Salesforce login page
    And user send login data and click login button
    And user is redirected to home page
    When user click on the AppLauncher icon
    And click on the Service option
   	Then user is redirected to service page
   	And navigate tabs clicking new and cancel when possible