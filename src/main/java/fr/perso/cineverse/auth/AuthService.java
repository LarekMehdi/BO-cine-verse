package fr.perso.cineverse.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.perso.cineverse.user.User;
import fr.perso.cineverse.user.UserRepository;
import fr.perso.cineverse.user.dto.UserDto;

@Service
public class AuthService {

    @Autowired
    private UserRepository      userRepository;

    @Autowired
    private PasswordEncoder     passwordEncoder;
    
    /** SIGNUP **/

    public User signup(UserDto dto) {

        User user = new User(dto);

        if (this.userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exist");
        }

        if (this.userRepository.existsByPseudo(user.getPseudo())) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Pseudo already exist");
        }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setDefaultRoleIfNeeded();
        this.userRepository.save(user);

        return user;
    }


}
