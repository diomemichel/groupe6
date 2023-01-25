


Feature: Wallet functionality

  Scenario Outline: As a customer I want to create a new wallet.
      Given I can create a new wallet
            And I sending wallet to be created with wallet id <id>, name <name> and balance <100>
            Then I should be able to see my newly created wallet

            Examples:
            | id      | name        | balance       |
            | 12345   | "test2"     | 100           |


  Scenario Outline: Create wallet
    Given a wallet with name "My Wallet" and balance 100
    When I POST the wallet to the endpoint
    Then the response status should be 201
    And the response body should contain the wallet



  Scenario Outline: Get wallet
    Given a wallet with ID 1 and name "My Wallet" and balance 100
    When I GET the wallet from the endpoint
    Then the response status2 should be 200
    And the response body2 should contain the wallet


  Scenario: Update wallet
    Given a wallet with ID 1 and name "My Wallet" and balance2 100
    When I PUT the wallet to the "/api/wallet/wallets/1" endpoint with name "Updated Wallet" and balance 200
    Then the response status should be 200
    And the response body should contain the updated wallet

  Scenario: Delete wallet
    Given a wallet with ID 1 and name "My Wallet" and balance3 100
    When I DELETE the wallet from the "/api/wallet/wallets/1" endpoint
    Then the response should be 204
    And the wallet should not be retrievable


