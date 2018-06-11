package org.project.user.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.project.user.entities.UserEntity;
import org.project.user.entities.UserRepository;
import org.project.user.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {

	@Autowired
	public UserRepository userRepository;
	
	/**
	 * Method, saves or update an UserEntity.
	 * @param userBean
	 */
	public void saveOrUpdateUser(UserBean userBean) {
		UserEntity userEntity = new UserEntity();
		mapUserEntity(userBean, userEntity);
		userRepository.save(userEntity);
		System.out.println("User Object is Saved");
	}
	
	/**
	 * Method, deletes an User Entity.
	 * @param userBean
	 */
	public void deleteUser(UserBean userBean){
		UserEntity userEntity = new UserEntity();
		mapUserEntity(userBean, userEntity);
		userRepository.delete(userEntity);
		System.out.println("User deleted Succesfully");
	}
	
	/**
	 * Method, gets the User Entities, and converts them into UserBean Model.
	 * @return
	 */
	public List<UserBean> getUsers() {
		List<UserBean> users = new ArrayList<>();
		Iterable<UserEntity> userEntities = userRepository.findAll();
		for (UserEntity userEntity : userEntities) {
			UserBean userBean = new UserBean();
			userBean.setId(String.valueOf(userEntity.getId()));
			userBean.setName(userEntity.getName());
			userBean.setDescription(userEntity.getDescription());
			users.add(userBean);
		}
		return users;
	}
	
	/**
	 * Method, maps UserBean Model to User Entity, for saving, updating and deleting.
	 * @param userBean
	 * @param userEntity
	 */
	private void mapUserEntity(UserBean userBean, UserEntity userEntity) {
		userEntity.setId(userBean.getId());
		userEntity.setName(userBean.getName());
		userEntity.setDescription(userBean.getDescription());
		userEntity.setDateCreated(new Date(new java.util.Date().getTime()));
		userEntity.setDateUpdated(new Date(new java.util.Date().getTime()));
	}

	public List<UserBean> searchUser(String id) {
		List<UserBean> userBeans = new ArrayList<>();
		UserEntity userEntity =userRepository.findOne(id);
		if(userEntity == null) {
			return new ArrayList<>();
		}
		UserBean userBean = new UserBean();
		userBean.setId(userEntity.getId());
		userBean.setName(userEntity.getName());
		userBean.setDescription(userEntity.getDescription());
		userBeans.add(userBean);
		return userBeans;
	}
	
}
