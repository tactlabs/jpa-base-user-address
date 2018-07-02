package org.qwan.jpa.repository;

import java.util.List;

import org.qwan.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findAllByOrderByUseridAsc();
	
	List<User> findAllByOrderByUseridDesc();
	
	User findByUsername(String username);//considering username as unique, it should return only one
	
	List<User> findByEmail(String email);
	
	@Query("Select a from User a where a.username like concat(:username, '%')")
	List<User> findByUsernameStartsWith(@Param("username")String username);
	
	@Query("Select a from User a where a.username like concat('%', :username)")
	List<User> findByUsernameEndsWith(@Param("username")String username);
	
	@Query("Select a from User a where a.username like concat('%', :username, '%')")
	List<User> findByUsernameContains(@Param("username")String username);
	
	@Query("Select a from User a where a.email like concat(:email, '%')")
	List<User> findByEmailStartsWith(@Param("email")String email);
	
	@Query("Select a from User a where a.email like concat('%', :email)")
	List<User> findByEmailEndsWith(@Param("email")String email);
	
	@Query("Select a from User a where a.email like concat('%', :email, '%')")
	List<User> findByEmailContains(@Param("email")String email);
	
	@Query("Select a from User a where a.username like concat(:username, '%')")
	List<User> findByCreatedAfter(@Param("username")String username);
	
	@Query("Select a from User a where a.status = 1")
	List<User> findActiveUsers();
	
	@Query("Select a from User a where a.status = 0")
	List<User> findInactiveUsers();
}