package com.inrocha.hruser.controller;

import com.inrocha.hruser.model.User;
import com.inrocha.hruser.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		Optional<User> optionalUser = repository.findById(id);
        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	@GetMapping(value = "/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email) {
		User obj = repository.findByEmail(email);
		if (obj == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(obj);
	}
}