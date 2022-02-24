package com.Spring.ExamCheatingDetection.config;

import com.Spring.ExamCheatingDetection.Entity.Person;
import com.Spring.ExamCheatingDetection.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserPrincipal  implements UserDetails {
    private Person person;

    public UserPrincipal(Person person){
        this.person = person;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       // List<GrantedAuthority> authorities = new ArrayList<>();
        // Extract list of permissions (name)

//        this.user.getPermissionList().forEach(p -> {
//            GrantedAuthority authority = new SimpleGrantedAuthority(p);
//            authorities.add(authority);
//        });

        // Extract list of roles (ROLE_name)


//        this.person.getRoleList().forEach(r -> {
//            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
//            authorities.add(authority);
//        });
//
//        return authorities;


        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(person.getRole());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public int getId()
    {
        return this.person.getId();
    }
}
