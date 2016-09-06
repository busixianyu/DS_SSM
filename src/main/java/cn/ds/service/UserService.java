package cn.ds.service;

import cn.ds.model.DsUser;

import java.util.List;

public interface UserService {

	List<DsUser> getUserListWithPage(DsUser user);
	DsUser getUserById(int id);
	void add(DsUser user);
	void update(DsUser user);
	void delete(int id);

	boolean findByName( String name,Integer id);
}
