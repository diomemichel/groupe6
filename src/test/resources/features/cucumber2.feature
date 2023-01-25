


Feature: Transaction API
  As a fintech developer
  I want to test the Transaction API
  So that I can ensure it is working correctly

Scenario: Deposit funds
  Given a wallet with ID 1 and name "My Wallet" and balance 100
  When I POST a transaction with wallet ID 1 and amount 50 and type "deposit" to the "/transaction/transactions/1" endpoint
  Then the response status should be 201
  And the wallet with ID 1 should have a balance of 150