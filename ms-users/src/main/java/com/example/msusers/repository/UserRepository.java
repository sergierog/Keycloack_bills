package com.example.msusers.repository;

import com.example.msusers.domain.User;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.ws.rs.NotFoundException;

@Repository
public class UserRepository {

    private Keycloak keycloak;

    @Value("${ms-users.keycloak.realm}")
    private String realm;

    public UserRepository(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    private User toUser(UserRepresentation userRepresentation) {
        return User.builder()
                .id(userRepresentation.getId())
                .userName(userRepresentation.getUsername())
                .email(userRepresentation.getEmail())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .build();
    }


    public User findByUserName (String userName){
        List<UserRepresentation> userRepresentation = keycloak
                .realm(realm)
                .users()
                .search(userName);
        return userRepresentation.stream()
                .map(this::toUser)
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public User findByEmail(String email) {
        List<UserRepresentation> userRepresentation = keycloak
                .realm(realm)
                .users()
                .search(email);
        return userRepresentation.stream()
                .map(this::toUser)
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
