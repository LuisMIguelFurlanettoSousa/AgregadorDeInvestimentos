package projetoDB.AgregadorDeInvestimentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoDB.AgregadorDeInvestimentos.entity.User;
import projetoDB.AgregadorDeInvestimentos.service.UserService;

import java.net.URI;

@RestController
@RequestMapping("/v1/users")
public class UserControler {

    private UserService userService;

    public UserControler(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        //
        return null;
    }
}
