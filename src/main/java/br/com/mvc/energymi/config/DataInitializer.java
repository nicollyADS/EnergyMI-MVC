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
            user1.setUsername("admin");
            user1.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(user1);
        };
    }
}
