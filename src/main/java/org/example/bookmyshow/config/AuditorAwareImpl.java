package org.example.bookmyshow.config;


import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
//        Authentication authentication =
//                SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null ||
//                !authentication.isAuthenticated()) {
//            return Optional.of("SYSTEM");
//        }
//
//        return Optional.of(authentication.getName());
        return Optional.of("Admin");
    }    // we define who does the modificsation or Updated by
}
