/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.bibliotek.rest.test.booksTest;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import org.junit.Test;
import static org.junit.Assert.*;
import se.nackademin.bibliotek.rest.test.Author;
import se.nackademin.bibliotek.rest.test.Book;
import se.nackademin.bibliotek.rest.test.Book_AuthorOperations;
import se.nackademin.bibliotek.rest.test.SingleAuthor;
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
    Integer id = 1;
    Integer authorId;
    String authorName;
    //  private SingleAuthor SingleAuthor;

    public BookTest() {
    }

    @Test
    public void testGetAllBooks() {  //ok  lördag
        Response response = new Book_AuthorOperations().getAllBooks();
        assertEquals("Status code should be 200", 200, response.statusCode());
    }

    @Test
    public void testGetBookById() {//ok
//
//        Book book = new Book();
//        book = given().accept(ContentType.JSON).get(BASE_URL + "books/2").prettyPeek().jsonPath().getObject("book", Book.class);
//        SingleBook singleBook = new SingleBook(book);

        //Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().get(BASE_URL + "books/2").prettyPeek();
        Response response = new Book_AuthorOperations().getBookById(1);
        assertEquals("Status code should be 200", 200, response.statusCode());

    }

    @Test
    public void testGetInvalidBook() { //ok lör
        Response response = new Book_AuthorOperations().getBook(99999);
        assertEquals("Return 404 - Not Found", 404, response.getStatusCode());
    }

    @Test
    public void testCreateNewBook() { //ok

        Book book = new Book();
        Author author = new Author();

        author.setName("Hasean");
        author.setId(1);

        Book bAuthor = new Book(author);

        bAuthor.setDescription("New set och skapa en book!");
        bAuthor.setId(251);
        bAuthor.setIsbn("147852");
        bAuthor.setTitle("CreateNewBook");
        bAuthor.setNbOfPage(200);

        SingleBook singleBook = new SingleBook(bAuthor);

        Response postResponse = new Book_AuthorOperations().postAuthorToBook(999, singleBook);

        assertEquals("Status code should be 201", 201, postResponse.statusCode());
    }

    @Test
    public void testUpdateBook() { //ok lör, 

        // nästa gång vill man köra testen 
        //title är :"Ny uppdate"
        //ändra texten till något annat.  setTitle("Ny text")
        Response getResponse = new Book_AuthorOperations().getBook(104);
        assertEquals("Returns 200 = OK", 200, getResponse.getStatusCode());

        Book book = getResponse.jsonPath().getObject("book", Book.class);
        assertEquals(104, (int) book.getId());
        assertEquals("No one in particular", book.getTitle());

        book.setTitle("Ny uppdate");   //new title
        SingleBook singleBook = new SingleBook(book);

        Response putResponse = new Book_AuthorOperations().putBook(singleBook);
        assertEquals("Returns 200 = OK", 200, putResponse.getStatusCode());
    }

    @Test
    public void testGetBookByAuthor() {//ok    sön

        Response getResponse = new Book_AuthorOperations().getBooksByAuthor(1);
        assertEquals("Status code should be 200", 200, getResponse.statusCode());
    }

    @Test
    public void testAddAuthorToBook() {  //uppdate a book wi
        /*          
        post /books/{book_id}/authors
        Add an author to the specified book.
        HTTP status code 200
         */
        //ByBookId
        Response getResponse = new Book_AuthorOperations().getBook(2);
        assertEquals("Returns 200 = OK", 200, getResponse.getStatusCode());

        Author author = new Author();
        author.setName("Farzaneh");  //ny author
        author.setId(33);            //ny author id

        Book bookAuthor = new Book(author);
      
     //   SingleAuthor singleAuthor = new SingleAuthor(author);     
 
        bookAuthor = getResponse.jsonPath().getObject("book", Book.class);
  
        SingleBook singleBook = new SingleBook(bookAuthor);
        singleBook.setAuthor(author);
        
        //   SingleBook singleBook = new SingleBook(book);
        Response postResponse = new Book_AuthorOperations().postAuthorToBook(2, singleBook);
        assertEquals("Returns 200 = OK", 200, postResponse.getStatusCode());


    }

    @Test
    public void testUppdateBooksListOfAuthorsByBookId() {

        /*put /books/{book_id}/authors
Update a book's list of authors with a new list of authors.
        HTTP status code 200
         */
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
