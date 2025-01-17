package org.example.botoutlay.dbConfig.factory;

import org.example.botoutlay.dbConfig.entityAndService.entity.User;
import org.example.botoutlay.dbConfig.entityAndService.enums.Permissions;
import org.example.botoutlay.dbConfig.entityAndService.enums.Role;

import java.util.List;


public class UserFactorySingleton {


    private static UserFactorySingleton instance;

    private UserFactorySingleton() {
    }

    public static synchronized UserFactorySingleton getInstance() {
        if (instance == null) {
            instance = new UserFactorySingleton();
        }
        return instance;
    }

    public User createUser(String firstName, String lastName, String username, String password, Role role, List<Permissions> permissionsSet) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(password)
                .role(role)
                .permissions(permissionsSet)
                .enabled(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .build();
    }

}
