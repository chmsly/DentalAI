import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            if (userService.findByUsername(user.getUsername()) != null) {
                throw new CustomException("Username is already taken");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error registering user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken(authentication);
            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new CustomException("Error logging in: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        try {
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok("User logged out successfully");
        } catch (Exception e) {
            throw new CustomException("Error logging out: " + e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
        try {
            User user = userService.findById(id);
            if (user == null) {
                throw new CustomException("User not found");
            }
            return ResponseEntity.ok(user);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error fetching user: " + e.getMessage());
        }
    }
}