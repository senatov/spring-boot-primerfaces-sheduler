package de.senatov.reservationz.service;



import de.senatov.reservationz.auth.repository.UserRepository;
import de.senatov.reservationz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Iakov Senatov
 * @since 03.2020
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String bUserName) {

        Set<GrantedAuthority> grantedAuthorities;
        User user = this.userRepository.findByUsername(bUserName);
        if (user == null) {
            throw new UsernameNotFoundException(bUserName);
        }
        grantedAuthorities = user.getRoles().stream().map(role -> {return new SimpleGrantedAuthority(role.getName());}).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
