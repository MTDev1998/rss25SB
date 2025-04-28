package fr.univrouen.rss25SB.Repository;


import fr.univrouen.rss25SB.Entity.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}