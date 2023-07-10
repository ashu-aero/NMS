package net.codejava;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	
	
	@Autowired
	private DeviceRepository deviceRepo;
	
	
	  @GetMapping("/") 
	  public String loginScreen(HttpSession session) {
		  session.removeAttribute("username");
		  return "login"; 
	  }
	 
	 
	
	/*
	 * @GetMapping("/") public String loginScreen( ) {
	 * 
	 * Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication(); if (authentication ==
	 * null || authentication instanceof AnonymousAuthenticationToken) { return
	 * "login"; }
	 * 
	 * return "redirect:/home"; }
	 */
 
	
	@GetMapping("/home")
	public String homeScreen(Model model, HttpSession session) {
		
		if(session.getAttribute("username")!=null) {
			List<Device> deviceList =deviceRepo.findAll();  		
			model.addAttribute("deviceList", deviceList);
			return "home";
		}
		else {
			return "redirect:/";
		}
		
		
	}

	@GetMapping("/add_device")
	public String addIp(HttpSession session) {
		
		if(session.getAttribute("username")!=null) {
			return "add_device";
		}
		else {
			return "redirect:/";
		}
		
	}
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	

	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "register_success";
	}
	
	
	@PostMapping("/add_device")
	public String deviceRegister(Device device, HttpSession session) {
		
		if(session.getAttribute("username")!=null) {
			deviceRepo.save(device);		
			return "add_device";
		}
		else {
			return "redirect:/";
		}
				
		
	}
	
	@PostMapping("/process_login")
	public String processLogin(User unknownUser, HttpSession session) {
		 
		//System.out.println("coming username is : "+unknownUser.getUsername());
		//System.out.println("coming Password is : "+unknownUser.getPassword());
		
		String unknownUsername=unknownUser.getUsername();
		String unknownPlainPassword=unknownUser.getPassword();
		
		
		BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder(); 
		
		User actualUser= userRepo.findUserByUserName(unknownUsername);		
		 
		String username=actualUser.getUsername();
		String encodedPassword=actualUser.getPassword(); 
		
		boolean isMatched=bCryptPasswordEncoder.matches(unknownPlainPassword, encodedPassword);
		
		if(isMatched) {
			 
			session.setAttribute("username", username);
			return "redirect:home";
		}
		else {
			return "redirect:/";
		}
		
		
	}
	
	
 		 
}
