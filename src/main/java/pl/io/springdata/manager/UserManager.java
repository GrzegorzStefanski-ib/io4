package pl.io.springdata.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.io.springdata.dao.entity.UserDTO;
import pl.io.springdata.dao.repo.UserRepo;

import java.util.Optional;

@Service
public class UserManager {
    private UserRepo userRepo;

    @Autowired
    public UserManager(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<UserDTO> findById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<UserDTO> findByName(String name) {
        Iterable<UserDTO> users = userRepo.findAll();
        Optional<UserDTO> output = Optional.empty();

        for (UserDTO user: users) {
            if(user.getName().equals(name)) {
                output = Optional.of(user);
                break;
            }
        }

        return output;
    }

    public Iterable<UserDTO> findAll() {
        return userRepo.findAll();
    }

    public UserDTO save(UserDTO user) {
        return userRepo.save(user);
    }

}
