package cn.ds.service.impl;

import cn.ds.dao.UserDao;
import cn.ds.model.DsUser;
import cn.ds.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;


	public List<DsUser> getUserListWithPage(DsUser user) {
		List<DsUser> users = null;
		if(user!=null){
			users = userDao.getUserListWithPage(user.getName());
		}else{
			users = userDao.getUserListWithPage(null);
		}
		return users;
	}

	@Override
	public DsUser getUserById(int id) {
		if(id<0){
			return null;
		}
		return userDao.getUserById(id);
	}

	@Override
	public void add(DsUser user) {
		if(user==null){
			throw new RuntimeException("添加失败，数据为空！");
		}
		userDao.addUser(user);
	}

	@Override
	public void update(DsUser user) {
		if(user==null||user.getId()==null){
			throw new RuntimeException("更新失败，数据为空！");
		}
		userDao.updateUser(user);
	}

	@Override
	public void delete(int id) {
		if(id<1){
			return;
		}
		userDao.deleteUserById(id);
	}

	@Override
	public boolean findByName(String name,Integer id) {
		if(StringUtils.isBlank(name)){
			return false;
		}
		int count = userDao.findByName(name,id);
		if(count>0){
			return false;
		}
		return true;
	}


}
