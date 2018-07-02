package org.qwan.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "S_NAMES")
@Data
public class Fullname {
	
	@Id	
    private int names_userid;	
    private String firstname;
    private String lastname;
}