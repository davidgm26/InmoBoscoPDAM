package com.salesianostriana.pdam.inmoboscoapi.services;

import com.salesianostriana.pdam.inmoboscoapi.dto.CreateUserRequest;
import com.salesianostriana.pdam.inmoboscoapi.models.User;
import com.salesianostriana.pdam.inmoboscoapi.models.UserRole;
import com.salesianostriana.pdam.inmoboscoapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public User createUser(CreateUserRequest createUserRequest, Set<UserRole> rol){
    User user = User.builder()
            .firstname(createUserRequest.getFirstname())
            .lastname(createUserRequest.getLastname())
            .username(createUserRequest.getUsername())
            .password(createUserRequest.getPassword())
            .phone(createUserRequest.getPhoneNumber())
            .dni(createUserRequest.getDni())
            .avatar(createUserRequest.getAvatar())
            .email(createUserRequest.getEmail())
            .birthdate(createUserRequest.getBirthdate())
            .rol(rol)
            .build();
    return userRepository.save(user);
    }
    /*
    public User createUserWithOwnerRole(CreateUserRequest createUserRequest){
        return createUser(createUserRequest, Set.of(UserRole.OWNER));
    }
    */
  /*  public User createUserWithWorkerRole(CreateUserRequest createUserRequest){
        return createUser(createUserRequest, Set.of(UserRole.WORKER));
    }
*/
}
