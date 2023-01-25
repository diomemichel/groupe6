Feature: Post functionality

  Scenario Outline: As a customer I want to create a new post.
    Given I can create a new post
    And I sending post to be created with post id <id>, name <name> and balance <100>
    Then I should be able to see my newly created post

    Examples:
    | id      | name      | balance       |
    | 12345   | test2     | 100 |
    | new_ID  | test3     | 1000  |