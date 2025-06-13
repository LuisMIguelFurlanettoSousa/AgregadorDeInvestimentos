package projetoDB.AgregadorDeInvestimentos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoDB.AgregadorDeInvestimentos.entity.User;
import projetoDB.AgregadorDeInvestimentos.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserControler {

    private final UserService userService;

    public UserControler(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        var users = userService.listUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUserById(userId, updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
