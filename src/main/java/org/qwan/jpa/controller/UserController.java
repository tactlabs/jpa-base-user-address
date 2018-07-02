package org.qwan.jpa.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.qwan.jpa.model.Address;
import org.qwan.jpa.model.User;
import org.qwan.jpa.repository.AddressRepository;
import org.qwan.jpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/user")
@SuppressWarnings({"rawtypes", "serial", "unchecked"})
class UserController {
	
	private static Logger _log = LoggerFactory.getLogger(UserController.class);	
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private AddressRepository addressRepository;
	
	/**
	 * get all users (with addresses, names)
	 * 
	 * possible url:
	 * 	http://localhost:1878/user
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public <T> T getAll() {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findAll());
		
        return (T) map;            
	}
	
	/**
	 * get active users
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/active
	 * 
	 */
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	@Transactional
	public <T> T getActiveUsers() {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findActiveUsers());
		
        return (T) map;            
	}
	
	/**
	 * get inactive users
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/inactive
	 * 
	 */
	@RequestMapping(value = "/inactive", method = RequestMethod.GET)
	@Transactional
	public <T> T getInactiveUsers() {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findInactiveUsers());
		
        return (T) map;            
	}
	
	/**
	 * get all users (with addresses, names) order by userid asc
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/orderby/userid
	 * 
	 */
	@RequestMapping(value = "/orderby/userid", method = RequestMethod.GET)
	@Transactional
	public <T> T getAllOrderByUserIdAsc() {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findAllByOrderByUseridAsc());
		
        return (T) map;            
	}
	
	/**
	 * get all users (with addresses, names) order by userid asc
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/orderby/userid/desc
	 * 
	 */
	@RequestMapping(value = "/orderby/userid/desc", method = RequestMethod.GET)
	@Transactional
	public <T> T getAllOrderByUserIdDesc() {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findAllByOrderByUseridDesc());
		
        return (T) map;            
	}
	
	/**
	 * get users by username
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/get/user/by/username
	 * 
	 */
	@RequestMapping(value = "/get/user/by/username", method = RequestMethod.GET)
	@Transactional
	public <T> T getUsersByUsername(@RequestParam(name="username", defaultValue="raja") String username) {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findByUsername(username));
		
        return (T) map;            
	}
	
	/**
	 * get users by email
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/get/user/by/email
	 * 
	 */
	@RequestMapping(value = "/get/user/by/email", method = RequestMethod.GET)
	@Transactional
	public <T> T getUsersByEmail(@RequestParam(name="email", defaultValue="raja@gmail.com") String email) {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findByEmail(email));
		
        return (T) map;            
	}
	
	/**
	 * get users by username starts with
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/get/user/by/username/startswith
	 * 
	 */
	@RequestMapping(value = "/get/user/by/username/startswith", method = RequestMethod.GET)
	@Transactional
	public <T> T getUsersByUsernameStartswith(@RequestParam(name="username", defaultValue="ra") String usermameStringPart) {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findByUsernameStartsWith(usermameStringPart));
		
        return (T) map;            
	}
	
	/**
	 * get users by username ends with
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/get/user/by/username/endswith
	 * 
	 */
	@RequestMapping(value = "/get/user/by/username/endswith", method = RequestMethod.GET)
	@Transactional
	public <T> T getUsersByUsernameEndswith(@RequestParam(name="username", defaultValue="ja") String usermameEndingPart) {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findByUsernameEndsWith(usermameEndingPart));
		
        return (T) map;            
	}	
	
	
	/**
	 * get users by username ends with
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/get/user/by/username/contains
	 * 
	 */
	@RequestMapping(value = "/get/user/by/username/contains", method = RequestMethod.GET)
	@Transactional
	public <T> T getUsersByUsernameContains(@RequestParam(name="username", defaultValue="ja") String usermame) {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findByUsernameContains(usermame));
		
        return (T) map;            
	}
	
	/**
	 * get users by email starts with
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/get/user/by/email/startswith
	 * 
	 */
	@RequestMapping(value = "/get/user/by/email/startswith", method = RequestMethod.GET)
	@Transactional
	public <T> T getUsersByEmailStartswith(@RequestParam(name="email", defaultValue="ja") String emailStartingPart) {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findByEmailStartsWith(emailStartingPart));
		
        return (T) map;            
	}
	
	/**
	 * get users by email ends with
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/get/user/by/email/endswith
	 * 
	 */
	@RequestMapping(value = "/get/user/by/email/endswith", method = RequestMethod.GET)
	@Transactional
	public <T> T getUsersByEmailEndswith(@RequestParam(name="email", defaultValue="gmail.com") String emailEndingPart) {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findByEmailEndsWith(emailEndingPart));
		
        return (T) map;            
	}
	
	/**
	 * get users by email ends with
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/get/user/by/email/contains
	 * 
	 */
	@RequestMapping(value = "/get/user/by/email/contains", method = RequestMethod.GET)
	@Transactional
	public <T> T getUsersByEmailContains(@RequestParam(name="email", defaultValue="ja") String email) {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", userRepository.findByEmailContains(email));
		
        return (T) map;            
	}
	
	// get all addresses (without users)
	
	/**
	 * add user (without address, names)
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/add/user
	 * 
	 */
	@RequestMapping(value = "/add/user", method = {RequestMethod.POST})
	public <T> T addUser(
			@RequestParam(name="username", defaultValue="raja") String username,
			
			@RequestParam(name="password", defaultValue="yux5559") String password,
			@RequestParam(name="email", defaultValue="raja@gmail.com") String email
			) {
		
		final User user = getUserByUsername(username);
		
		if(user != null){
			Map<String, Object> map = new LinkedHashMap<>();
	        map.put("result", "ok");
	        map.put("message", "User already available");
	        
	        return (T) map;
		}
		
        _log.info("{addUser} user : "+user);
        
        // save user (without address, names)
        userRepository.save(user);
        
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", "ok");
        
        return (T) map;
	}
	
	
	/**
	 * add address
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/add/address
	 * 
	 */
	@RequestMapping(value = "/add/address", method = {RequestMethod.POST})
	public <T> T addAddress(
			@RequestParam(name="username", defaultValue="raja") String username,
			
			@RequestParam(name="streetname", defaultValue="11 Humber Boulevard") String streetname,
			@RequestParam(name="city", defaultValue="Toronto") String city,
			@RequestParam(name="country", defaultValue="Canada") String country
			) {
		
		final User user = getUserByUsername(username);
		
		//_log.info("{addAddresses} user : "+user);
		
		// adding address
        Set addressSet = new LinkedHashSet<Address>(){{
            add(new Address(streetname, city, country, user));
        }};
        user.setAddresses(addressSet);
        
        _log.info("{addAddresses} user : "+user);

        // save user (with address)
        userRepository.save(user);
        
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", "ok");
        
        return (T) map;
	}
	
	/**
	 * update city
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/address/update/city
	 * 
	 */
	@RequestMapping(value = "/address/update/city", method = {RequestMethod.POST})	
	public <T> T updateCityMultiple(
			@RequestParam(name="old", required = true) String oldName,
			@RequestParam(name="new", required = true) String newName			
			) {
		
		// update address multiple        
        addressRepository.updateCityMultiple(oldName, newName);
        
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", "ok");
        
        return (T) map;
	}
	
	
	/**
	 * update city
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/address/update/city/for/active/users
	 * 
	 */
	@RequestMapping(value = "/address/update/city/for/active/users", method = {RequestMethod.POST})	
	public <T> T updateCityMultipleOnActiveUsers(
			@RequestParam(name="old", required = true) String oldName,
			@RequestParam(name="new", required = true) String newName			
			) {
		
		// update address multiple  only for active users      
        addressRepository.updateCityMultipleOnActiveUsers(oldName, newName);
        
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", "ok");
        
        return (T) map;
	}
	
	/**
	 * update city
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/get/active/users/city
	 * 
	 */ 
	@RequestMapping(value = "/get/active/users/city", method = RequestMethod.GET)
	@Transactional
	public <T> T showActiveUsersCity() {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", addressRepository.showActiveUsersCity());
		
        return (T) map;            
	}
	
	
	/**
	 * get all addresses
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/address
	 * 
	 */
	@RequestMapping(value = "/address", method = RequestMethod.GET)
	@Transactional
	public <T> T getAllAddresses() {
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", addressRepository.findAll());
		
        return (T) map;            
	}
	
	/**
	 * get time
	 * 
	 * possible url:
	 * 	http://localhost:1878/user/time
	 * 
	 */
	@RequestMapping(value = "/time", method = RequestMethod.GET)
	@Transactional
	public <T> T getTime() {
		
		Date now = new Date();
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
		String timeNow = date.toString(fmt);
		
		Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        map.put("message", now);
        map.put("timenow", timeNow);
        map.put("timezone", TimeZone.getDefault());
		
        return (T) map;            
	}
	
	private User getUserByUsername(String username){
		User user = (User) userRepository.findByUsername(username);
		
		if(user == null)
			return new User(username);
		
		return user;
	}
}