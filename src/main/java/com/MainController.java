package com;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("/user")
	public String addUser(@RequestBody User user) {
		userRepository.save(user);
		return "User saved!!!";
	}
	
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		userRepository.deleteById(id);
		return "User deleted!!!";
	}
	
	@PostMapping("/user/{id}")
	public String updateUser(@PathVariable("id") int id, @RequestBody User newUser) {
	
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			userRepository.save(newUser);
			return "User udpated!!!";
		}
		return "User doesn't exist!!!";
	}
	
	
	
}
