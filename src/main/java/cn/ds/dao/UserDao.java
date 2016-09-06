package cn.ds.dao;

import cn.ds.model.DsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
	
	List<DsUser> getUserListWithPage(@Param("name") String name);
	DsUser getUserById(int id);
	void updateUser(DsUser user);
	void deleteUserById(int id);
	void addUser(DsUser user);

	int findByName(@Param("name") String name,@Param("id") Integer id);
}
