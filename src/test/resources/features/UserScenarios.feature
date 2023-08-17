@UserCleanUp
Feature: User scenarios

  Scenario: User created with correct details
    Given User with username "johndoe" does not exist
    When I create a new user with details:
      | id         | RANDOM_ID        |
      | username   | johndoe          |
      | firstName  | John             |
      | lastName   | Doe              |
      | email      | johndoe@mail.com |
      | password   | Qwerty123        |
      | phone      | 123-456-789      |
      | userStatus | 0                |
    Then User created
    When I read details of user with username "johndoe"
    Then User has correct details


  Scenario: User updated with correct details
    Given User created with details:
      | id         | RANDOM_ID        |
      | username   | johndoe          |
      | firstName  | John             |
      | lastName   | Doe              |
      | email      | johndoe@mail.com |
      | password   | Qwerty123        |
      | phone      | 123-456-789      |
      | userStatus | 0                |
    When Update all user details
      | id         | RANDOM_ID           |
      | username   | johndoeNew          |
      | firstName  | JohnNew             |
      | lastName   | DoeNew              |
      | email      | johndoeNew@mail.com |
      | password   | Qwerty123New        |
      | phone      | 123-456-789-321     |
      | userStatus | 1                   |
    Then User updated
    When I read details of user with username "johndoeNew"
    Then User has correct details


  Scenario: User deleted
    Given User created with details:
      | id         | RANDOM_ID        |
      | username   | johndoe          |
      | firstName  | John             |
      | lastName   | Doe              |
      | email      | johndoe@mail.com |
      | password   | Qwerty123        |
      | phone      | 123-456-789      |
      | userStatus | 0                |
    When I delete user with username "johndoe"
    Then User deleted


  Scenario: Get not existing user details
    Given User with username "johndoe" does not exist
    When I read details of user with username "johndoe"
    Then User not found
    And Error details returned "User not found"


  Scenario: Delete not existing user
    Given User with username "johndoe" does not exist
    When I delete user with username "johndoe"
    Then User not found