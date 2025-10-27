package fr.perso.cineverse.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.perso.cineverse.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements UserDetails{
    
    private Long id;
    private String pseudo;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return this.pseudo;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority(user.getRole().name())
        );

        return new UserPrincipal(
            user.getId(),
            user.getPseudo(),
            user.getEmail(),
            user.getPassword(),
            authorities);
    }
}
