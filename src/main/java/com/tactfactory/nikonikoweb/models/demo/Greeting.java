package com.tactfactory.nikonikoweb.models.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name= "demo_Greeting")
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(value= "id")
    private Long id=null;

    @JsonProperty(value="myContent")
    private String content;

    public void setContent(String content) {
		this.content = content;
	}

	public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
