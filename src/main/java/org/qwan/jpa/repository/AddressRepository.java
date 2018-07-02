package org.qwan.jpa.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.qwan.jpa.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	@Modifying
	@Query("Update Address a set a.city = :newname where a.city = :oldname")
	void updateCityMultiple(@Param("oldname")String oldName, @Param("newname")String newName);
		
	@Modifying
	@Query(
			"UPDATE Address a "
			+ "SET a.city = :newname "					
			+ "where a.city = :oldname and a.user in (select u from User u where u.status = 1)"
	)
	void updateCityMultipleOnActiveUsers(
			@Param("oldname")String oldName, 
			@Param("newname")String newName);
	
	@Query("select a1.city, a1.country from Address a1 join a1.user u where u.status = 1")
	List<Address> showActiveUsersCity();
	
	// delete all addresses for inactive users
	
	// delete all chennai address for specific user if the user is active
	
	//
	
	// keep only one addresses for inactive users; if they have more than one, delete rest but keep top 1
}