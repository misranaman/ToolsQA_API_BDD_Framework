package stepDefinitions;

import apiEngine.IRestResponse;
import apiEngine.model.requests.AddBooksRequest;
import apiEngine.model.requests.Book;
import apiEngine.model.requests.ISBN;
import apiEngine.model.requests.RemoveBookRequest;
import apiEngine.model.responses.Books;
import apiEngine.model.responses.UserAccount;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

public class BookSteps extends BaseSteps {

    public BookSteps(TestContext testContext) {

        super(testContext);
    }

    private static final String USER_ID = "908be9bb-f09f-4b75-bbbd-268716914758";
    private Response response;
    private IRestResponse<UserAccount> userAccountResponse;

    private IRestResponse<Books> booksResponse;

    private Book book;

    @Given("^A list of books are available$")
    public void listOfBooksAreAvailable() {

        IRestResponse<Books> booksResponse = getEndPoints().getBooks();

        book = booksResponse.getBody().books.get(0);

        getScenarioContext().setContext(Context.BOOK, book);

        //System.out.println("listOfBooksAreAvailable");
    }

    @When("^I add a book to my reading list$")
    public void addBookInList() {

        ISBN isbn = new ISBN(book.isbn);

        AddBooksRequest addBooksRequest = new AddBooksRequest(USER_ID, isbn);

        booksResponse = getEndPoints().addBook(addBooksRequest);

        getScenarioContext().setContext(Context.BOOKS, booksResponse);

        userAccountResponse = getEndPoints().getUserAccount(USER_ID);

        getScenarioContext().setContext(Context.USER_ACCOUNT_RESPONSE, userAccountResponse);

        //System.out.println("addBookInList");
    }

//    @Then("^The book is added$")
//    public void bookIsAdded() {
//
//        userAccountResponse = getEndPoints().getUserAccount(USER_ID);
//
//        Assert.assertTrue(userAccountResponse.isSuccessful());
//
//        Assert.assertEquals(200, userAccountResponse.getStatusCode());
//
//        Assert.assertEquals(USER_ID, userAccountResponse.getBody().userId);
//
//        Assert.assertEquals(book.isbn, userAccountResponse.getBody().books.get(0).isbn);
//    }

    @When("^I remove a book from my reading list$")
    public void removeBookFromList() {

        Book book = (Book) getScenarioContext().getContext(Context.BOOK);

        RemoveBookRequest removeBookRequest = new RemoveBookRequest(USER_ID, book.isbn);

        response = getEndPoints().removeBook(removeBookRequest);

        getScenarioContext().setContext(Context.BOOK_REMOVE_RESPONSE, response);

        //System.out.println("removeBookFromList");
    }
//
//    @Then("^The book is removed$")
//    public void bookIsRemoved() {
//
//        Assert.assertEquals(204, response.getStatusCode());
//
//        userAccountResponse = getEndPoints().getUserAccount(USER_ID);
//
//        Assert.assertEquals(200, userAccountResponse.getStatusCode());
//
//        Assert.assertEquals(0, userAccountResponse.getBody().books.size());
//    }
}
