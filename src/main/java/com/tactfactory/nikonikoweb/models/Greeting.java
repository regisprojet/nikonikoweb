package com.tactfactory.nikonikoweb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="greeting")
public class Greeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(value="id")
	private Long id = null;
	
	@JsonProperty(value = "content_name")
    private String content;

    public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    
    public Greeting(String content) {
        this.content = content;
    }

    public Greeting() {
    	
    }

}
