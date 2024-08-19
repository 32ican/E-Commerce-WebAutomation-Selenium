


  Feature: Adding new product to cart

    @Smoke
    Scenario: After logging in user could add product to cart

      Given user logged in to his account with username "mof16@test.com" and password "12341234"
      When user select a new product: simple computer
      And add it to cart
      Then confirmation message: "The product has been added to your" should appear
      And user log out
