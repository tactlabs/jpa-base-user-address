package org.qwan.jpa.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "S_USER")
//@Data
@JsonIgnoreProperties(value={"password"})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="USERID", nullable=false)
    private int userid;
	
	@Column(name="USERNAME", unique=true, nullable=false)
    private String username;

	@Column(name="PASSWORD", nullable=false)
    private String password;
	
	@Column(name="EMAIL", nullable=true)
    private String email;
     
	@OneToOne
    @JoinColumn(name="userid")
    private Fullname names;    
    
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)	
    @JsonManagedReference //- It will go for infinite loop if you comment this out
    private Set<Address> addresses;
	
	private Date created_date;
	
	private int status;
	
    public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Fullname getNames() {
		return names;
	}

	public void setNames(Fullname fullname) {
		this.names = fullname;
	}	
	
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	public int getStatus(){
		return status;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
	
	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
    
    @Override
    public String toString() {
        String result = String.format(
                "Category[userid=%d, username='%s']%n",
                userid, username);
        
        
        if(names != null){
        	result += String.format(
                    "Names[firstname=%s, lastname='%s']%n",
                    names.getFirstname(), names.getLastname());
        } 
        
        if (addresses != null) {
            for(Address address : addresses) {
                result += String.format(
                        "Address[id=%d, name='%s']%n",
                        address.getId(), address.getCountry());
            }
        }
        

        return result;
    }
    
    public User(){
    }

    public User(String username) {
        this.username = username;
    }
    
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}