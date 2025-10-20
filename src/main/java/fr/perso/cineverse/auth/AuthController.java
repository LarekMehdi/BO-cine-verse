package fr.perso.cineverse.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import fr.perso.cineverse.user.User;
import fr.perso.cineverse.user.dto.UserDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService         authService;

     /** SIGNUP **/

    public User signup(@RequestBody @Valid UserDto dto) {
        return new User();
    }
    
}
