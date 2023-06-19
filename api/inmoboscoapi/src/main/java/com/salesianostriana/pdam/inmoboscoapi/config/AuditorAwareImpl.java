package com.salesianostriana.pdam.inmoboscoapi.config;

import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            return Optional.empty();
        }

        User user = (User) authentication.getPrincipal();
        return Optional.ofNullable(user.getId().toString());
    }
}
