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
public class Author {

    private Integer id;
    private String name;

//    public Author(Integer id, String authorName) {
//
//        id = this.id;
//        authorName = this.authorName;
//
//    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
