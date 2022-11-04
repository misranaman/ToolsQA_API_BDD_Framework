package apiEngine;

import apiEngine.model.requests.AddBooksRequest;
import apiEngine.model.requests.AuthorizationRequest;
import apiEngine.model.requests.RemoveBookRequest;
import apiEngine.model.responses.Books;
import apiEngine.model.responses.Token;
import apiEngine.model.responses.UserAccount;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

public class Endpoints {

    //private static final String BASE_URL = "https://bookstore.toolsqa.com";

    private final RequestSpecification request;

    public Endpoints(String baseUrl) {

        System.out.println("----Endpoints constructor call----");

        RestAssured.baseURI = baseUrl;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");

    }

    public void authenticateUser(AuthorizationRequest authRequest) {

        Response response = request.body(authRequest).post(Route.generateToken());

        if (response.statusCode() != HttpStatus.SC_OK)
            throw new RuntimeException("Authentication Failed. Content of failed Response: " + response.toString() + " , Status Code : " + response.statusCode());

        Token tokenResponse = response.body().jsonPath().getObject("$", Token.class);

        //System.out.println(tokenResponse.token);

        request.header("Authorization", "Bearer " + tokenResponse.token);

    }

    public IRestResponse<Books> getBooks() {

        Response response = request.get(Route.books());
        return new RestResponse(Books.class, response);
    }

    public IRestResponse<Books> addBook(AddBooksRequest addBooksRequest) {

        Response response = request.body(addBooksRequest).post(Route.books());
        return new RestResponse(Books.class, response);
    }

    public Response removeBook(RemoveBookRequest removeBookRequest) {

        Response response = request.body(removeBookRequest).delete(Route.book());
        return response;
    }

    public IRestResponse<UserAccount> getUserAccount(String userId) {

        Response response = request.get(Route.userAccount(userId));
        return new RestResponse(UserAccount.class, response);
    }

}
