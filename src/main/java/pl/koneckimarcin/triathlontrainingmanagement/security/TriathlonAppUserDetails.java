package pl.koneckimarcin.triathlontrainingmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.user.role.RoleEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TriathlonAppUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String userName;
        String password;
        List<GrantedAuthority> authorityList;
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the user: " + username);
        } else {
            userName = user.get().getEmailAddress();
            password = user.get().getPassword();
            authorityList = getGrantedAuthorities(user.get().getRoles());
        }
        return new User(userName, password, authorityList);
    }
    private List<GrantedAuthority> getGrantedAuthorities(Set<RoleEntity> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(RoleEntity role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().toString()));
        }
        return grantedAuthorities;
    }
}
