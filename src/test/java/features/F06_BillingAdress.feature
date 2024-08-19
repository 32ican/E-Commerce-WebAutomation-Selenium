

    @Regression
  Feature: verification that user could not proceed checking out without filling the mandatory data in the billing address

    Background:

      Given user logged in to his account with username "mof18@test.com" and password "12341234"
      When user go to shopping cart
      And agree the terms of service
      And click on check out



    Scenario: verify that error message will appear that country must be selected to proceed

      When user click on continue without selecting a country
      Then error message will appear that: "Country is required."


      Scenario: verify that error message will appear that city must be filled out

        When user select country "Egypt" and click on continue
        Then error message will appear that: "City is required"


        Scenario: verify that error message will appear that street addres is required

          When user select Country and fill the City as follows
              |Country| City|
              |Egypt  | Cairo|
          Then error message will appear that: "Street address is required"


          Scenario: verify that error message will appear that postal code is required

            When user fill the address as follows and click continue
              |Country| City| Street|
              |Egypt  | Cairo| Test st.|
            Then error message will appear that: "Zip / postal code is required"


            Scenario: verify that error message will appear that phone is required

              When user fill the zip code and click on continue
                |Country| City| Street|Zipcode|
                |Egypt  | Cairo| Test st.|12345|
              Then error message will appear that: "Phone is required"


