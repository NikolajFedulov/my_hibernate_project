package ua.ithillel.my_hibernate_project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class SecurityConfiguration {

    private final String ENCODED_PASSWORD = "$2a$10$a1xkj1XSVE81fLJT8oQdIOiiz31PmRPEkFeLSHc6bFvSabGCYmXVG";

    @Bean
    public InMemoryUserDetailsManager users() {
//        String password = "hello";
//        String hashedPassword = new BCryptPasswordEncoder().encode(password);
//        System.out.println(hashedPassword);
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}" + ENCODED_PASSWORD)
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}
