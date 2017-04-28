package se.nackademin.bibliotek.rest.test;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.post;
import static com.jayway.restassured.RestAssured.put;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.Random;
import java.util.UUID;


public class BookOperations {

    private static final String BASE_URL = "http://localhost:8080/librarytest/rest/";
    private String jsonString = "";

    public Response getBook(int id) {

        String resourceName = "books/" + id;
        Response response = given().accept(ContentType.JSON).log().all().get(BASE_URL + resourceName);

        return response;
    }

    public Response createRandomBook() {
        String resourceName = "books";
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String isbn = UUID.randomUUID().toString();

        String postBodyTemplate = "{\n"
                + "\"book\":\n"
                + "  {\n"
                + "    \"description\":\"%s\",\n"
                + "    \"isbn\":\"%s\",\n"
                + "    \"nbOfPage\":%s,\n"
                + "    \"title\":\"%s\"\n"
                + "  }\n"
                + "}";
        String postBody = String.format(postBodyTemplate, description, isbn, "" + new Random().nextInt(500), title);
        jsonString = postBody;

        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(BASE_URL + resourceName);
        return postResponse;
    }

    public String getLatestJsonString() {
        return jsonString;
    }

    public Response getAllBooks() {

        String resourceName = "books";
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL + resourceName).prettyPeek();
        return getResponse;
    }

    public Response deleteBook(int id) {

        String deleteResourceName = "books/" + id;
        Response deleteResponse = delete(BASE_URL + deleteResourceName);
        return deleteResponse;
    }
    
    public Response postBook(int id){
        
        String postResourceName ="books/ + id";
        Response postResponse = post(BASE_URL + postResourceName);
        return postResponse;        
    }
        public Response putBook(int id){
        
        String putResourceName ="books/";
        Response getResponse =getBook(id);
        
        Response putResponse = put(BASE_URL + putResourceName);
        return putResponse;        
    }
    
}