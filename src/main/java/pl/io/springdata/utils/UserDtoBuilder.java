package pl.io.springdata.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.io.springdata.dao.entity.UserDTO;

@Component
public class UserDtoBuilder {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDtoBuilder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO build(User user){
        return new UserDTO(
            user.getName(),
            passwordEncoder.encode(user.getPassword()),
            user.getRole()
        );
    }

}
