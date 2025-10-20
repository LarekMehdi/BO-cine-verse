package fr.perso.cineverse.user;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /** COUNT **/

    public boolean existsByEmail(String email);

    public boolean existsByPseudo(String pseudo);
    
}
