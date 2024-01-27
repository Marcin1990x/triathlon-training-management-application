package pl.koneckimarcin.triathlontrainingmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.triathlontrainingmanagement.user.RoleEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class TriathlonAppPasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return new UsernamePasswordAuthenticationToken
                        (username, password, getGrantedAuthorities(user.get().getRoles()));
            } else {
                throw new BadCredentialsException("Invalid password.");
            }
        } else {
            throw new BadCredentialsException("No user registered with this details");
        }
    }
    private List<GrantedAuthority> getGrantedAuthorities(Set<RoleEntity> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(RoleEntity role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().toString()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
