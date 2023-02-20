package com.alibou.security.auth;

import com.alibou.security.user.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @GetMapping("/me")
  public UserDetails currentUserName(Authentication authentication) {
    return (UserDetails) authentication.getPrincipal();
  }

  @GetMapping("/all")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<User>> allUsers() {
    return ResponseEntity.ok(service.findAllUsers());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/createUser")
  public ResponseEntity<User> createUser(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.createUser(request));
  }

  @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/changePassword/{userId}")
  public ResponseEntity<User> changePassword(@PathVariable Integer userId, @RequestBody String newPassword) {
    return ResponseEntity.ok(service.changePassword(userId, newPassword));
  }
}
