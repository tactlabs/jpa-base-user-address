package org.qwan.jpa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "S_ADDRESSES")
@Data
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;	
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=User.class)
    @JoinColumn(name = "address_userid")
    @JsonBackReference //- It will go for infinite loop if you comment this out
    private User user;	
	
    private String streetname;
    private String city;
    private String country;
    
    public Address(){    	
    }
    
    public Address(String streetname, String city, String country, User user){
    	this.streetname = streetname;
    	this.city = city;
    	this.country = country;
    	this.user = user;
    }
}