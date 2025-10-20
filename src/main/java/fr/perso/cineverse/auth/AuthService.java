package fr.perso.cineverse.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.perso.cineverse.auth.dto.AuthResponseDto;
import fr.perso.cineverse.auth.dto.SigninDto;
import fr.perso.cineverse.security.JwtTokenProvider;
import fr.perso.cineverse.security.UserPrincipal;
import fr.perso.cineverse.user.User;
import fr.perso.cineverse.user.UserRepository;
import fr.perso.cineverse.user.dto.UserDto;

@Service
public class AuthService {

    @Autowired
    private UserRepository          userRepository;

    @Autowired
    private PasswordEncoder         passwordEncoder;

    @Autowired
    private AuthenticationManager   authenticationManager;

    @Autowired
    private JwtTokenProvider        JwtTokenProvider;
    
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

    /** SIGNIN **/

    public AuthResponseDto signin(SigninDto dto) throws Exception {

        Authentication authentication = this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getPseudo(), dto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = this.JwtTokenProvider.generateToken(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        return new AuthResponseDto(
            token,
            principal.getId(),
            principal.getUsername(),
            principal.getAuthorities().stream().findFirst().get().getAuthority()
        );
    }


}
