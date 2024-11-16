package br.com.mvc.energymi.config;

import br.com.mvc.energymi.model.Usuario;
import br.com.mvc.energymi.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(UsuarioRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Usuario user1 = new Usuario();
            user1.setUsername("user1");
            user1.setPassword(passwordEncoder.encode("password"));
            userRepository.save(user1);

            Usuario user2 = new Usuario();
            user2.setUsername("user2");
            user2.setPassword(passwordEncoder.encode("password2"));
            userRepository.save(user2);
        };
    }
}
