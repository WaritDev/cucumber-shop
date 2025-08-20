Feature: Stock checking
  To prevent overselling
  The system must check stock before selling

  Scenario: Stock is enough
    Given the store is ready to service customers
    And a product "Pen" with price 10.0 and stock of 5 exists
    When I buy "Pen" with quantity 3
    Then total should be 30.0

  Scenario: Stock is NOT enough
    Given the store is ready to service customers
    And a product "Pen" with price 10.0 and stock of 2 exists
    When I try to buy "Pen" with quantity 3
    Then buying should fail with "InsufficientStockException"