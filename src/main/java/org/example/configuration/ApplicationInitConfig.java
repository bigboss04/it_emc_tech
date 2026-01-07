package org.example.configuration;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.enums.RoleName;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner (UserRepository userRepository){
        return args -> {
            if( userRepository.findByUserName("ADMIN").isEmpty()){
                var roles = new HashSet<String>();
                log.info("ROLE {}" , roles);
                roles.add(RoleName.ADMIN.name());

                User userAdmin = User.builder()
                        .userName("ADMIN")
                        .password(passwordEncoder.encode("ADMIN"))
                        .roles(roles)
                        .build();

                userRepository.save(userAdmin);
                log.warn("admin user has been created with default password admin please change it");
            }
        };
    }
}
