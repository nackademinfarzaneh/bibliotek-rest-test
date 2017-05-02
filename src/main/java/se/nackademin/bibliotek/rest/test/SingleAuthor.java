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
public class SingleAuthor {
     private Author author;

    
    public SingleAuthor(Author author) {
        this.author = author;
    }
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
    
}
