Feature: This is a test

    #@Firstest
  Scenario: Login to Application

    Given I navigate to Sauce Labs
    Then I verify "Login" page is loaded
    And I enter value in "username" field on "Login" page
    And I enter value in "password" field on "Login" page
    And I click on "Login" on "Login" page
    Then  I verify "Home" page is loaded



  #  Then I verify " " is displayed
  #  Then I validate " " is not displayed
  #  And I select  " abc" from "From station "
  #  And I mouse hover on " "

    # Scenario Outline: Login to Application- multiple
    # Given I navigate to Sauce Labs
    # Then I verify "Login" page is loaded
    # And I enter "<userName>" and "<password>" field
    # Examples:
    # |  userName |  password  |
    # |standard_user | secret_sauce |
    # |problem_user | secret_sauce  |



  Scenario: Count number of products on home page
    Given I navigate to Sauce Labs
    Then I verify "Login" page is loaded
    And I enter value in "username" field on "Login" page
    And I enter value in "password" field on "Login" page
    And I click on "Login" on "Login" page
    Then  I verify "Home" page is loaded
    And I verify Number of products on "Home" page


  Scenario: Validate name and price of all products against excel
    Given I navigate to Sauce Labs
    Then I verify "Login" page is loaded
    And I enter value in "username" field on "Login" page
    And I enter value in "password" field on "Login" page
    And I click on "Login" on "Login" page
    Then  I verify "Home" page is loaded
    And I validate Product name and Price on "Home" page

  @Firstest
  Scenario: Add Product to cart
    Given I navigate to Sauce Labs
    Then I verify "Login" page is loaded
    And I enter value in "username" field on "Login" page
    And I enter value in "password" field on "Login" page
    And I click on "Login" on "Login" page
    Then  I verify "Home" page is loaded
    And I click on "Add to Cart" for a Product on "Home" page
    And I click on cart link on "Home" page
    Then I verify "Cart" page is loaded
    And I verify Product is added to cart
    When I click on "Checkout" on "Cart" page
    Then I verify "CheckoutStep1" page is loaded
    And I enter value in "First name" field on "CheckoutStep1" page
    And I enter value in "Last name" field on "CheckoutStep1" page
    And I enter value in "Zip" field on "CheckoutStep1" page
    And I click on "Continue" on "CheckoutStep1" page
    Then I verify "CheckoutStep2" page is loaded
    And I verify item total matches with product price
    When I click on "Finish" on "checkoutStep2" page
    Then I verify "CheckoutComplete" page is loaded
    And I verify order is completed

  Scenario: Add multiple Products to cart
    Given I navigate to Sauce Labs
    Then I verify "Login" page is loaded
    And I enter "<userName>" in "username" field on "Login" page
    And I enter "<password>" in "password" field on "Login" page
    And I click on "Login" on "Login" page
    Then  I verify "Home" page is loaded
    When I click on "Add to Cart" for "Product1" on "Home" page
    Then I verify "Product1" is added to cart
    When I click on "Continue Shopping" on "Cart" page
    Then I verify "Home" page is loaded
    When I click on "Add to Cart" for "Product2" on "Home" page
    Then I verify "Product2" is added to cart

  Scenario: Complete Flow of Order
    Given I navigate to Sauce Labs
    Then I verify "Login" page is loaded
    And I enter value in "username" field on "Login" page
    And I enter value in "password" field on "Login" page
    And I click on "Login" on "Login" page
    Then  I verify "Home" page is loaded
    When I click on "Add to Cart" for "Product1" on "Home" page
    And I click on "Add to Cart" for "Product2" on "Home" page
    Then I verify "Product1" is added to cart
    And I verify "Product2" is added to cart
    When I click on "Remove" for "Product1" on "Cart" page
    Then I verify "Product1" is removed on "Cart" page
    When I click on "Checkout" on "Cart" page
    Then I verify "checkoutStep1" page is loaded
    When I enter "<firstName>" in "First Name" field on "checkoutStep1" page
    And I enter "<lastName>" in "Last Name" field on "checkoutStep1" page
    And I enter "<zipcode>" in "Zip Code" field on "checkoutStep1" page
    And I click on "Continue" on "checkoutStep1" page
    Then I verify "checkoutStep2" page is loaded
    When I click on "Finish" on "checkoutStep2" page
    Then I verify "CheckoutComplete" page is loaded
    And I verify order is completed

  Scenario: Cancel Checkout
    Given I navigate to Sauce Labs
    Then I verify "Login" page is loaded
    And I enter "<userName>" in "username" field on "Login" page
    And I enter "<password>" in "password" field on "Login" page
    And I click on "Login" on "Login" page
    Then  I verify "Home" page is loaded
    When I click on "Add to Cart" for "Product1" on "Home" page
    And I click on "Add to Cart" for "Product2" on "Home" page
    Then I verify "Product1" is added to cart
    And I verify "Product2" is added to cart
    When I click on "Checkout" on "Cart" page
    Then I verify "checkoutStep1" page is loaded
    When I enter "<firstName>" in "First Name" field on "checkoutStep1" page
    And I enter "<lastName>" in "Last Name" field on "checkoutStep1" page
    And I enter "<zipcode>" in "Zip Code" field on "checkoutStep1" page
    And I click on "Continue" on "checkoutStep1" page
    Then I verify "checkoutStep2" page is loaded
    When I click on "Cancel" on "checkoutStep2" page
    Then I verify "Home" page is loaded


  Scenario: Sort Products
    Given I navigate to Sauce Labs
    Then I verify "Login" page is loaded
    And I enter value in "username" field on "Login" page
    And I enter value in "password" field on "Login" page
    And I click on "Login" on "Login" page
    Then  I verify "Home" page is loaded
    When I click on "Sort" on "Home" page
    And I select "Name (Z to A)" from "Sort" on "Home" page
    Then I verify products are sorted based on sort criteria
