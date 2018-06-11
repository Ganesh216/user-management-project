package org.project.user.api;

import java.util.List;

import org.project.user.dao.UserDAO;
import org.project.user.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author hacker
 *
 */
@RestController
@Component
public class UserAPI {

	@Autowired
	UserDAO userDAO;
	
	/**
	 * API method saves or update an user, by calling the DAO interface.
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/saveorupdateuser", method = RequestMethod.POST)
	public ResponseEntity<UserBean> saveOrUpdateUser(@RequestBody UserBean user){
		System.out.println("User ID "+user.getId());
		userDAO.saveOrUpdateUser(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	/**
	 * API method to Search user, by calling the DAO interface.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/searchUser",params= {"user_id"},  method =RequestMethod.GET ,produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> searchUser(@RequestParam(value="user_id") String id){
		System.out.println("User ID "+id);
		List<UserBean> userBeans =	userDAO.searchUser(id);
		if(userBeans.isEmpty()) {
			return null;
		}else {
			return new ResponseEntity<>(userBeans,HttpStatus.OK);
		}
	}
	/**
	 * API method delete a user, by calling the DAO delete function.
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
	public ResponseEntity<UserBean> deleteUser(@RequestBody UserBean user){
		System.out.println("User ID "+user.getId());
		userDAO.deleteUser(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	/**
	 * API method, return all the available Users.
	 * @return
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getUsers(){
		System.out.println("Display Users ");
		List<UserBean> userBeans = userDAO.getUsers();
		return new ResponseEntity<>(userBeans, HttpStatus.OK);
	}
}
