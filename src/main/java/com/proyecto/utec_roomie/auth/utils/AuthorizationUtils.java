package com.proyecto.utec_roomie.auth.utils;


import com.proyecto.utec_roomie.user.domain.User;
import com.proyecto.utec_roomie.user.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;



@Component
public class AuthorizationUtils {

    @Autowired
    private UserService userService;

    public boolean isAdminOrResourceOwner(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().toArray()[0].toString();
        User student= userService.findByEmail(username, role);

        return student.getId().equals(id);
    }

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        catch (ClassCastException e) {
            return null;
        }
    }

    public String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getAuthorities().toArray()[0].toString();
    }

}