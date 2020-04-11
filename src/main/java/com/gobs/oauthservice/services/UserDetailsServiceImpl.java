package com.gobs.oauthservice.services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import com.gobs.oauthservice.domain.entities.Authority;
import com.gobs.oauthservice.domain.entities.User;
import com.gobs.oauthservice.repositories.users.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(),
                        user.getPassword(), getGrantedAuthorities(user)))
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " Not found"));
    }

    @Autowired
    public void setUserRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Authority authority : usersRepository.fetchAuthoritiesById(user.getId())) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }

        return grantedAuthorities;
    }
}
