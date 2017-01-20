package ru.innopolis.uni.course3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.course3.mapper.UserMapper;
import ru.innopolis.uni.course3.model.Role;
import ru.innopolis.uni.course3.model.User;
import ru.innopolis.uni.course3.repository.SpringDataUserRepository;
import ru.innopolis.uni.course3.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SpringDataUserRepository repository;
    //private UserRepository repository;

    public UserDetailsServiceImpl() {
    }

    public UserDetailsServiceImpl(UserRepository repository) {
//        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = repository.getByEmail(email);
        User user = UserMapper.INSTANCE.map(repository.findByEmail(email));
        if (user == null) {
            throw new UsernameNotFoundException("No user found with email: "+ email);
        }
        return new org.springframework.security.core.userdetails.User
                (user.getEmail(), user.getPassword().toLowerCase(), user.isEnabled(), true,
                        true, true, getAuthorities(user.getRoles()));
    }

    private List<GrantedAuthority> getAuthorities(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(String.valueOf(role)));
        }
        return authorities;
    }
}
