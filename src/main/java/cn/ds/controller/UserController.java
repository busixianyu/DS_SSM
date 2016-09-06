package cn.ds.controller;

import cn.ds.context.SystemContext;
import cn.ds.model.Datas;
import cn.ds.model.DsUser;
import cn.ds.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String login(String name,Model model) {
		logger.info("加载user列表数据！");
		DsUser user = new DsUser();
		if(StringUtils.isNotBlank(name)){
			user.setName(name.trim());
		}
		int start = SystemContext.getOffset();
		int limit = SystemContext.getPageSize();
		PageHelper.startPage(start, limit);
		List<DsUser> userListWithPage = userService.getUserListWithPage(user);
		PageInfo<DsUser> pageInfo = new PageInfo<DsUser>(userListWithPage);
		Datas datas = new Datas(userListWithPage,pageInfo);
		model.addAttribute("datas",datas);
		model.addAttribute("name",name);
		return "login";
	}

	@RequestMapping(value = "/{id}/edit",method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("user/edit");
		mv.addObject("title","编辑用户");
		DsUser user = null;
		if(id!=null){
			user = userService.getUserById(id);
		}
		mv.addObject("user",user);
		return mv;
	}

	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("user/edit");
		mv.addObject("title","添加用户");
		return mv;
	}

	@RequestMapping(value = "/{id}/delete",method = RequestMethod.GET)
	public String delete(@PathVariable Integer id) {
		if(id!=null){
			userService.delete(id);
		}
		return "forward:/user/list";
	}
	@RequestMapping(value = "/findByName",method = RequestMethod.GET)
	@ResponseBody
	public String findByName(String name,Integer id){
		if(StringUtils.isBlank(name)){
			return "false";
		}
		return  userService.findByName(name,id)+"";

	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("user") DsUser user,BindingResult result,Map<String,Object> map) {

		if(result.hasErrors()){
			if(user!=null&&user.getId()!=null){
				map.put("title","编辑用户");
			}else {
				map.put("title", "添加用户");
			}
			return "user/edit";
		}
		if(user!=null&&user.getId()!=null){
			userService.update(user);
		}else{
			userService.add(user);
		}
		return "redirect:list";
	}
}