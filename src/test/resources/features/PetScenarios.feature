@PetCleanUp
Feature: Pet scenarios

  Scenario: Pet created with correct details
    Given Pet with id 111 does not exist
    When I create a new pet with details:
      | id           | 111       |
      | name         | John      |
      | status       | available |
      | categoryId   | 101       |
      | categoryName | dog       |
      | tagId        | 102       |
      | tagName      | beagle    |
      | photoUrl     | https://upload.wikimedia.org/wikipedia/commons/5/55/Beagle_600.jpg  |
    Then Pet created
    When I read pet details with id 111
    Then Pet has correct details


  Scenario: Ped updated with correct details
    Given Pet created with details:
      | id           | 111       |
      | name         | John      |
      | status       | available |
      | categoryId   | 101       |
      | categoryName | dog       |
      | tagId        | 102       |
      | tagName      | beagle    |
      | photoUrl     | https://upload.wikimedia.org/wikipedia/commons/5/55/Beagle_600.jpg  |
    When I update pet details with id 111
      | name   | JohnNew |
      | status | sold    |
    Then Pet updated
    When I read pet details with id 111
    Then Pet has correct details


  Scenario: Pet deleted
    Given Pet created with details:
      | id           | 111       |
      | name         | John      |
      | status       | available |
      | categoryId   | 101       |
      | categoryName | dog       |
      | tagId        | 102       |
      | tagName      | beagle    |
      | photoUrl     | https://upload.wikimedia.org/wikipedia/commons/5/55/Beagle_600.jpg  |
    When I delete pet with id 111
    Then Pet deleted
    When I read pet details with id 111
    Then Pet not found
    And Error details returned "Pet not found"
    When I delete pet with id 111
    Then Pet not found


  Scenario: Pet presents in list filtered by status
    Given Pet created with details:
      | id           | 111       |
      | name         | John      |
      | status       | sold      |
      | categoryId   | 101       |
      | categoryName | dog       |
      | tagId        | 102       |
      | tagName      | beagle    |
      | photoUrl     | https://upload.wikimedia.org/wikipedia/commons/5/55/Beagle_600.jpg  |
    When I get pets list filtered by status "sold"
    Then Pet with name "John" presents in list
