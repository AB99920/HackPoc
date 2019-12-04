package com.pacman.bytes.demo.config;

import com.pacman.bytes.demo.repo.IUserRepository;
import com.pacman.bytes.demo.service.AuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("LDAP-MOCK")
public class ConfigLdapMock {

    @Bean
    public AuthorizationService getAuthorizationService(IUserRepository userRepository) {
        return new AuthorizationService(userRepository);
    }
}
