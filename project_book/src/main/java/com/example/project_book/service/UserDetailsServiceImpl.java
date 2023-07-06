package com.example.project_book.service;

import com.example.project_book.repo.IUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUsersRepo usersRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.example.project_book.model.User users = this.usersRepo.findByEmailUser(email);
        if (users == null) {
            System.out.println("Email not found! " + email);
            throw new UsernameNotFoundException("Email: " + email + " was not found in the database");
        }
        List<com.example.project_book.model.User> userRole = this.usersRepo.findAllByRoleUser(users.getRoleUser());
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (userRole != null) {
            for (com.example.project_book.model.User u : userRole) {
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+u.getRoleUser().getNameRole());
                grantList.add(authority);
            }
        }
        UserDetails userDetails = (UserDetails) new User(users.getEmailUser(), //
                users.getPassUser(), grantList);
        return userDetails;
    }
}
