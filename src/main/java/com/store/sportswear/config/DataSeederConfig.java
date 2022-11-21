package com.store.sportswear.config;

import com.store.sportswear.entity.Role;
import com.store.sportswear.entity.UserSystem;
import com.store.sportswear.repository.RoleRepository;
import com.store.sportswear.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataSeederConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public DataSeederConfig(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(roleRepository.findByNameRole("ROLE_ADMIN")==null){
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
        if(roleRepository.findByNameRole("ROLE_MEMBER")==null){
            roleRepository.save(new Role("ROLE_MEMBER"));
        }
        if(roleRepository.findByNameRole("ROLE_SHIPPER")==null){
            roleRepository.save(new Role("ROLE_SHIPPER"));
        }
        if(userRepository.findByEmail("admin@gmail.com")==null){
            UserSystem admin=new UserSystem();
            admin.setUser_email("admin@gmail.com");
            admin.setUser_password(passwordEncoder.encode("123456"));
            admin.setUser_name("Doan Quoc Bao");
            admin.setUser_phone("0864562155");
            HashSet<Role> roles=new HashSet<>();
            roles.add(roleRepository.findByNameRole("ROLE_ADMIN"));
            roles.add(roleRepository.findByNameRole("ROLE_MEMBER"));
            admin.setRole(roles);
            userRepository.save(admin);
        }
        if (userRepository.findByEmail("member@gmail.com") == null) {
            UserSystem member = new UserSystem();
            member.setUser_email("member@gmail.com");
            member.setUser_password(passwordEncoder.encode("123456"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByNameRole("ROLE_MEMBER"));
            member.setRole(roles);
            userRepository.save(member);
        }
        if (userRepository.findByEmail("shipper@gmail.com") == null) {
            UserSystem shipper = new UserSystem();
            shipper.setUser_email("shipper@gmail.com");
            shipper.setUser_password(passwordEncoder.encode("123456"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByNameRole("ROLE_SHIPPER"));
            shipper.setRole(roles);
            userRepository.save(shipper);
        }
    }
}
