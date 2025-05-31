package projetoDB.AgregadorDeInvestimentos.service;

import org.springframework.stereotype.Service;
import projetoDB.AgregadorDeInvestimentos.controller.CreateUserDto;
import projetoDB.AgregadorDeInvestimentos.entity.User;
import projetoDB.AgregadorDeInvestimentos.repository.UserRepository;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UUID createUser(CreateUserDto createUserDto) {

        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null
                );

        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }
}
