package com.example.assignment3;

import com.example.assignment3.security.AuthService;
import com.example.assignment3.security.dto.SignupRequest;
import com.example.assignment3.user.RoleRepository;
import com.example.assignment3.user.UserRepository;
import com.example.assignment3.user.model.ERole;
import com.example.assignment3.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(bootstrap) {
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("gabi@email.com")
                    .username("gabi")
                    .password("Gabriela3!")
                    .roles(Set.of("ADMINISTRATOR"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("gabi1@email.com")
                    .username("gabi1")
                    .password("Gabriela3!")
                    .roles(Set.of("SECRETARY"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("gabiDoc@email.com")
                    .username("gabiDoc")
                    .password("Gabriela3!")
                    .roles(Set.of("DOCTOR"))
                    .build());


        }
    }

}
