package com.theneva.beer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Beer
{
    @Id
    @GeneratedValue
    public Long id;

    @Column(unique = true)
    public String name;
    public String category;
    public String description;

    public Beer()
    {
    }

    public Beer(final String name, final String category, final String description)
    {
        this.name = name;
        this.category = category;
        this.description = description;
    }
}
