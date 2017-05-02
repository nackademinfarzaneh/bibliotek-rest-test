package se.nackademin.bibliotek.rest.test.authorsTest;

import com.jayway.restassured.response.Response;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import se.nackademin.bibliotek.rest.test.Author;
import se.nackademin.bibliotek.rest.test.Book;
import se.nackademin.bibliotek.rest.test.Book_AuthorOperations;
import se.nackademin.bibliotek.rest.test.SingleAuthor;
import se.nackademin.bibliotek.rest.test.SingleBook;

public class AuthorTest {

    @Test
    public void testPostCreateNewAuthor() {
        // en ny författare skapas
        //return status 201
       
        Author author = new Author();
        author.setId(288); 
        author.setName("Kalle Kanin");
        
        Book book = new Book();       
        book.setAuthor(author);
        book.setDescription("have a naice day");
        book.setId(500);
        book.setIsbn("147258");
        book.setNbOfPage(200);
        book.setTitle("New book");

        SingleAuthor singleAuthor = new SingleAuthor(author);

        SingleBook singleBook = new SingleBook(book);

        Response postResponse = new Book_AuthorOperations().postAuthor(singleAuthor);

        assertEquals("Return 201 = Created", 201, postResponse.getStatusCode());

//        Response getResponse = new Book_AuthorOperations().getAuthor(author.getId());
//        assertEquals("Return 200 = OK", 200, getResponse.getStatusCode());
//        
//        Author fetchedAuthor = getResponse.jsonPath().getObject("author", Author.class);
//        assertEquals(author.getId(), fetchedAuthor.getId());
//        
//        
//        assertEquals(author.getName(), fetchedAuthor.getName());
    }

    @Test
    public void testPutAuthor() {
        // en existerande författare uppdateras 
        //return status 200
    }

    @Test
    public void testGetAllAuthors() {  //ok lör
        //retunerar en lista över alla författare
        //return 200

        Response response = new Book_AuthorOperations().getAllAuthors();
        assertEquals("Returns 200 = OK", 200, response.getStatusCode());
    }

    @Test
    public void testUpdateAuthorStatus404() {

        //404 author was not found        
    }

    @Test
    public void testPostInvalidAuthor() {
        Author author = new Author();
        author.setName("Herp Derp");
        author.setId(5);
        SingleAuthor singleAuthor = new SingleAuthor(author);

        Response response = new Book_AuthorOperations().postAuthor(singleAuthor);
        assertEquals("Returns 400 = Exists", 400, response.getStatusCode());
    }

    @Test
    public void testGetAuthorByAuthorId() {
        //http://localhost:8080/librarytest/rest/authors/4
    }

    @Test
    public void testDeleteAuthorByAuthorId() {

    }


}
