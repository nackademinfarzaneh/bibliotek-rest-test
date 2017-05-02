/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.bibliotek.rest.test;

/**
 *
 * @author testautom-nack
 */
public class SingleBook {

    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    private Book book;
    private Author author;

    public SingleBook(Book book) {
        this.book = book;
    }

//    public SingleBook(SingleAuthor singleAuthor, Book book) {
//        
//         this.singelAuthor= singleAuthor;
//          this.book = book;       
//    }
        public SingleBook(Author author, Book book) {
        
         this.author = author;
          this.book = book;       
    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

//    /**
//     * @return the singelAuthor
//     */
//    public SingleAuthor getSingelAuthor() {
//        return singelAuthor;
//    }
    
  

}
