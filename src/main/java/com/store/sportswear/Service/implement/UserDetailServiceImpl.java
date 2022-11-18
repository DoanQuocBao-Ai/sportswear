package com.store.sportswear.Service.implement;

import com.store.sportswear.Entity.Role;
import com.store.sportswear.Entity.UserSystem;
import com.store.sportswear.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetailServiceImpl() {
        super();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSystem userSystem = userRepository.findByEmail(username);
        if(userSystem ==null){
            throw new UsernameNotFoundException("User with email "+username+" was not be found");
        }
        Set<GrantedAuthority> grantedAuthorities=new HashSet<>();
        Set<Role> roleSet= userSystem.getRole();
        for(Role role: roleSet){
            grantedAuthorities.add(new SimpleGrantedAuthority((role.getName())));
        }
        return new User(username,userSystem.getUser_password(),grantedAuthorities);
    }
}
