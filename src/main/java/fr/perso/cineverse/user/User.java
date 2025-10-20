package fr.perso.cineverse.user;

import fr.perso.cineverse.constants.Role;
import fr.perso.cineverse.user.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @Column(nullable = false, unique = true)
    private String      email;

    @Column(nullable = false, unique = true)
    private String      pseudo;

    @Column(nullable = false)
    private String      password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role        role = Role.USER;

    //TODO: enum Role

    public User(UserDto dto) {
        this.email = dto.getEmail();
        this.pseudo = dto.getPseudo();
        this.password = dto.getPassword();
        this.role = dto.getRole();
    }

}
