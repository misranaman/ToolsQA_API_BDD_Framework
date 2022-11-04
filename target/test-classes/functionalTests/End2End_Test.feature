Feature: End to End Tests for ToolsQA's Book Store API
  Description: The purpose of these tests are to cover the End to End happy flow for the customer

  Book Store Swagger URL : https://bookstore.toolsqa.com/swagger/index.html

  Background: User generates token for Authorization
    Given I am an authorized user

    Scenario: Authorized user is able to add and Remove a book.
      Given A list of books are available
      When I add a book to my reading list
      Then The book is added
      When I remove a book from my reading list
      Then The book is removed
