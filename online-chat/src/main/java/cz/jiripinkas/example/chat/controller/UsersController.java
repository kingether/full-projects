package cz.jiripinkas.example.chat.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cz.jiripinkas.example.chat.entity.User;
import cz.jiripinkas.example.chat.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@RequestMapping
	public String list(Model model) {
		model.addAttribute("list", userService.findAll());
		return "users";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam int id) {
		userService.delete(id);
		return "redirect:/users.html";
	}

	@ModelAttribute
	public User construct() {
		return new User();
	}

	@RequestMapping("/add")
	public String showAdd() {
		return "user";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute @Valid User user, BindingResult result) {
		if(result.hasErrors()) {
			return showAdd();
		}
		userService.add(user);
		return "redirect:/";
	}

	// @RequestMapping(value = "/add", method = RequestMethod.POST)
	// public String add(@RequestParam String name, @RequestParam String
	// password) {
	// User user = new User();
	// user.setName(name);
	// user.setPassword(password);
	// userService.add(user);
	// return "redirect:/";
	// }
	
	@RequestMapping("/edit")
	public String showEdit(@RequestParam int id, Model model) {
		model.addAttribute("user", userService.findOne(id));
		return "user";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String edit(@ModelAttribute @Valid User user, BindingResult result, @RequestParam int id, Model model) {
		if(result.hasErrors()) {
			return "user";
		}
		user.setId(id);
		userService.edit(user);
		return "redirect:/";
	}

}
