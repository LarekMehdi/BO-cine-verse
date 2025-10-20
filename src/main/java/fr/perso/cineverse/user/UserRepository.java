package fr.perso.cineverse.user;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /** COUNT **/

    public boolean existsByEmail(String email);

    public boolean existsByPseudo(String pseudo);

    /** FIND **/

    @Query("SELECT u FROM User u WHERE u.pseudo = :pseudo")
    public Optional<User> findByPseudo(@Param("pseudo") String pseudo);
    
}
