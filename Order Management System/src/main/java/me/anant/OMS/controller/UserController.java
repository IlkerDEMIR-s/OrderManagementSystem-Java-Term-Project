package me.anant.OMS.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

import me.anant.OMS.model.Product;
import me.anant.OMS.model.Users;
import me.anant.OMS.service.UsersService;

import me.anant.OMS.config.WebSecurityConfig;
import me.anant.OMS.exceptions.ProductNotFoundException;
import me.anant.OMS.exceptions.UserNotFoundException;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@Controller
public class UserController {

  @Autowired
  UsersService userService;
  
  @Autowired
  WebSecurityConfig config;
  
  @GetMapping("/admin/user/add")
	public ModelAndView addView() {
		ModelAndView modelAndView = new ModelAndView("admin/user/add");
		modelAndView.addObject("command", new Users());
		return modelAndView;
	}
  
  @PostMapping("/admin/user/add")
	public String addUser(@ModelAttribute("command") @Valid Users user, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return "redirect:/admin/user/add";
		}
		if(userService.doesEmailExist(user.getEmail())) {
			redirectAttributes.addFlashAttribute("msg", "Email exists!");
			redirectAttributes.addFlashAttribute("class", "alert-danger");
			return "redirect:/admin/user/add";
		}
		else {
			user.setPassword(config.encodePassword(user.getPassword()));
			userService.save(user);
			redirectAttributes.addFlashAttribute("msg", "User added successfully");
			redirectAttributes.addFlashAttribute("class", "alert-success");
			return "redirect:/admin/user/list";
		}
	}
  
  @GetMapping("/admin/user/delete")
	public String list(@RequestParam("id") long id,
			final RedirectAttributes redirectAttributes) {
		userService.delete(id);
		redirectAttributes.addFlashAttribute("msg", "Product deleted successfully");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/user/list";
	}
  
  @GetMapping("/admin/user/update")
	public ModelAndView updateView(long id) throws UserNotFoundException {
		Optional<Users> optional = userService.findById(id);
		Users user = optional.get();
		user.setPassword("");
		ModelAndView modelAndView = new ModelAndView("admin/user/add");
		modelAndView.addObject("command", user);
		return modelAndView;
	}
  
  @PostMapping("/admin/user/update")
	public String updateView(@ModelAttribute("command") @Valid Users user, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return "admin/user/add";
		}
		if(userService.doesEmailExist(user.getEmail())) {
			redirectAttributes.addFlashAttribute("msg", "Email exists!");
			redirectAttributes.addFlashAttribute("class", "alert-danger");
			return "redirect:/admin/user/add";
		}
		else {
			user.setPassword(config.encodePassword(user.getPassword()));
			userService.save(user);
			redirectAttributes.addFlashAttribute("msg", "User updated successfully");
			redirectAttributes.addFlashAttribute("class", "alert-success");
			return "redirect:/admin/user/list";
		}
	}

  /**
   * This Get api is responsible to view User List
   * @return ModelAndView
   */
  @GetMapping("/admin/user/list")
  public ModelAndView list() {
    List<Users> uList = userService.get();
    System.out.println(uList);
    ModelAndView modelAndView = new ModelAndView("/admin/user/list");
    modelAndView.addObject("uList", uList);
    return modelAndView;
  }
  
  @GetMapping("/signup")
  public ModelAndView showSignupPage() {
    ModelAndView modelAndView = new ModelAndView("/signup/signup");
    modelAndView.addObject("command", new Users());
    return modelAndView;
  }
  
  @PostMapping("/signup")
  public String add(@ModelAttribute("command") @Valid Users user, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		if(result.hasErrors()) {
			return "redirect:/signup";
		}
		if(userService.doesEmailExist(user.getEmail())) {
			redirectAttributes.addFlashAttribute("msg", "Email exists!");
			redirectAttributes.addFlashAttribute("class", "alert-danger");
			return "redirect:/signup";
		}
		else {
			// encode password
			user.setPassword(config.encodePassword(user.getPassword()));
			user.setRole("ROLE_CUSTOMER");
			userService.save(user);
			redirectAttributes.addFlashAttribute("msg", "User added successfully");
			redirectAttributes.addFlashAttribute("class", "alert-success");
			return "redirect:/login";
		}
	}
}
