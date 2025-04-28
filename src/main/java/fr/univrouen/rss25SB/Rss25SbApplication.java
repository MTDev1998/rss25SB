package fr.univrouen.rss25SB;
import fr.univrouen.rss25SB.Entity.User;
import fr.univrouen.rss25SB.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Rss25SbApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Rss25SbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Ajouter un utilisateur de test
		User user = new User("Tarek", "tarek.moussaoui@univ-rouen.fr");
		userRepository.save(user);
		System.out.println("Utilisateur ajouté : " + user.getName());
	}
}