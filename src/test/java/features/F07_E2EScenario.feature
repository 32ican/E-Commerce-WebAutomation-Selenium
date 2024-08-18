

  Feature: E2E Order checkout

    @Smoke
    Scenario:End to end scenario: User could order a simple computer and check it out

      Given user logged in to his account with username "mof16@test.com" and password "12341234"
      When user select a new product: simple computer
      And add it to cart
      And confirmation message: "The product has been added to your" should appear
      And user go to shopping cart
      And give the discount code "AutomationDiscount2"
      And user could see a confirmation message contains: "discount"
      And the discount is 20%
      And agree the terms of service
      And click on check out
      And add the address and continue
      |Country| City  | Street  | PostalCode| Phone |
      |Egypt  | Cairo | Test St.| 12345     |+12340000 |
      And continue to payment method and enter the payment information:
      | Card Type   |       Visa       |
      | Holder Name | Barbara Gordon   |
      | Card Number | 4485564059489345 |
      | Exp. Month  |      04          |
      | Exp. Year   |     2025         |
      | Card Code   |     123          |

      Then user could see confirmation message: "Your order has been successfully processed!"
      And user log out

