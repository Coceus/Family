package com.example.demo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.EqualsAndHashCode;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Person {
    @Id   
    @GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String name;
	@ManyToMany(cascade = CascadeType.ALL)
	Set<Person> parents;
	
	@ManyToMany(mappedBy = "parents", cascade = CascadeType.ALL)
	Set<Person> children;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Person> getParents() {
		return parents;
	}

	public void setParents(Set<Person> parents) {
		this.parents = parents;
	}

	public Set<Person> getChildren() {
		return children;
	}

	public void setChildren(Set<Person> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", parents=" + parents + ", children=" + children + "]";
	}
	
	
	
}
