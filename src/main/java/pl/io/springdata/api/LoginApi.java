package pl.io.springdata.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.io.springdata.dao.entity.UserDTO;
import pl.io.springdata.manager.UserManager;
import pl.io.springdata.utils.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class LoginApi {

    UserManager userManager;

    @Autowired
    public LoginApi(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/getToken")
    public String login(@RequestBody User user, HttpServletResponse response){

        Iterable<UserDTO> users = userManager.findAll();

        String name = user.getName();
        String password = user.getPassword();

        for (UserDTO user_it : users){

            if (user_it.getName().equals(name) && BCrypt.checkpw(password, user_it.getPasswordHash())){
                long currentTimeMillis = System.currentTimeMillis();

                return Jwts.builder()
                        .setSubject(user.getName())
                        .claim("role", user_it.getRole())
                        .setIssuedAt(new Date(currentTimeMillis))
                        .setExpiration(new Date(currentTimeMillis + 60000))
                        .signWith(SignatureAlgorithm.HS512, "6v9y/B?E(H+MbQeThWmZq4t7w!z%C&F)")
                        .compact();
            }
        }

        response.setStatus(418);
        return null;

    }
}
