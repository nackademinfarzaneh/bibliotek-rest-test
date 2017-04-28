/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.bibliotek.rest.test.booksTest;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.put;
import static com.jayway.restassured.RestAssured.put;
import static com.jayway.restassured.RestAssured.when;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import static javax.swing.UIManager.get;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;
import se.nackademin.bibliotek.rest.test.Book;
import se.nackademin.bibliotek.rest.test.BookOperations;
import se.nackademin.bibliotek.rest.test.SingleBook;

/**
 *
 * @author testautom-nack
 */
public class BookTest {

    private static final String BASE_URL = "http://localhost:8080/librarytest/rest/";

    String author = "Farzaneh";
    String description = "Den här är andra test för modul 3";
    String title = "Http och Rest-API:er";
    String isbn = "4321";
    Integer nbOfPage = 100;

    public BookTest() {
    }

    @Test
    public void testCreateNewBook() { //ok

        Book book = new Book(description, title, isbn, nbOfPage);

        SingleBook singleBook = new SingleBook(book);
        Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().post(BASE_URL + "books").prettyPeek();
        System.out.println("Status code: " + response.getStatusCode());

        assertEquals("Status code should be 201", 201, response.statusCode());

    }

    @Test
    public void testUpdateBookStatus200() { 
        //status code 200 , was uppdate
        //<title>Good Omens</title>
//        BookOperations bookOperation = new BookOperations();
//        Response getResponse = bookOperation.getAllBooks();
//        String fetchedTiltle = getResponse.jsonPath().getString("books.book[-1].title");
       

        Book book = new Book();
        BookOperations bookOperation = new BookOperations();
        book = given().accept(ContentType.JSON).get(BASE_URL + "books/2").prettyPeek().jsonPath().getObject("book", Book.class);
        
        book.setTitle("TestTest");
        SingleBook singleBook = new SingleBook(book);        

        Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().put(BASE_URL+"books/").prettyPeek();
        
        assertEquals("Status code should be 200", 200, response.statusCode());

    }

    @Test
    public void testGetAllBooks() {//ok

        Book book = new Book();
        book = given().accept(ContentType.JSON).get(BASE_URL + "books/").prettyPeek().jsonPath().getObject("book", Book.class);
        SingleBook singleBook = new SingleBook(book);

        Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().get(BASE_URL + "books/").prettyPeek();
        assertEquals("Status code should be 200", 200, response.statusCode());

    }

    @Test
    public void testGetBooksById() {//ok

        Book book = new Book();
        book = given().accept(ContentType.JSON).get(BASE_URL + "books/2").prettyPeek().jsonPath().getObject("book", Book.class);
        SingleBook singleBook = new SingleBook(book);

        Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().get(BASE_URL + "books/2").prettyPeek();
        assertEquals("Status code should be 200", 200, response.statusCode());

    }

    @Test
    public void testGetBookByAuthor() {//ok

        Book book = new Book();
        book = given().accept(ContentType.JSON).get(BASE_URL + "books/byauthor/4").prettyPeek().jsonPath().getObject("book", Book.class);
        SingleBook singleBook = new SingleBook(book);

        Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().get(BASE_URL + "books/byauthor/4").prettyPeek();
        assertEquals("Status code should be 200", 200, response.statusCode());

    }

    @Test
    public void testAddAuthorToBookByBookId() {
        
        Book book = new Book(description, title, isbn, nbOfPage);
        
        book.setAuthor("Test");
        
        SingleBook singleBook = new SingleBook(book);
        Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().post(BASE_URL + "books").prettyPeek();
        System.out.println("Status code: " + response.getStatusCode());

        assertEquals("Status code should be 201", 201, response.statusCode());
    }

    @Test
    public void testUppdateBooksListOfAuthorsByBookId() {

    }

    @Test
    public void testDeleteBookById() { //ok

        Book book = new Book();

        SingleBook singleBook = new SingleBook(book);
        Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().delete(BASE_URL + "books/12").prettyPeek();
        System.out.println("Status code: " + response.getStatusCode());
        assertEquals("Status code should be 204", 204, response.statusCode());

    }

    @Test
    public void testUpdateInvalidBookReturnsStatus400() {
        //status code 400 , book contained an author with no id field set or the book contained an         
        // author that didntexist in the database
    }

    @Test
    public void testUpdateInvalidBookReturnsStatus404() {
        //status code 404 book not found
    }

    /*      @Test
    public void testFetchInvalidBookReturns404() {

        Response response = new BookOperations().getBook(475236);
        System.out.println("Status code är: " + response.getStatusCode());

        assertEquals("Status code should be 404", 404, response.statusCode());

    }
     */
 /*
    
    get("/lotto").then().body("lotto.winners.winnerId", hasItems(23, 54));
     */
//var person =  { "name":"John", "age":31, "city":"New York" }; 
//   person.name;   //returns John   
    /*{
"employees":[  "John", "Anna", "Peter" ]
}    
     */
}
