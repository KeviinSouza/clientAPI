package com.crud.api.client.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.sun.istack.NotNull;

@Entity
@SequenceGenerator(name = "seq_client", sequenceName = "seq_client", allocationSize = 1, initialValue = 1)
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_client")
    private Long id;
	
	private String name;
    private String birthDate;
    
    public Long getId() {
 		return id;
 	}
 	public void setId(Long id) {
 		this.id = id;
 	}
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
    
}
