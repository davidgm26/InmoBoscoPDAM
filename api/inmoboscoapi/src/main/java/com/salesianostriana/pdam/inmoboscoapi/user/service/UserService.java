package com.salesianostriana.pdam.inmoboscoapi.user.service;

import com.salesianostriana.pdam.inmoboscoapi.Owner.model.Owner;
import com.salesianostriana.pdam.inmoboscoapi.Owner.repository.OwnerRepository;
import com.salesianostriana.pdam.inmoboscoapi.Owner.service.OwnerService;
import com.salesianostriana.pdam.inmoboscoapi.exception.*;
import com.salesianostriana.pdam.inmoboscoapi.property.dto.PropertyResponse;
import com.salesianostriana.pdam.inmoboscoapi.property.service.PropertyService;
import com.salesianostriana.pdam.inmoboscoapi.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.pdam.inmoboscoapi.search.util.SearchCriteria;
import com.salesianostriana.pdam.inmoboscoapi.user.UserRole;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.*;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import com.salesianostriana.pdam.inmoboscoapi.user.repository.UserRepository;
import com.salesianostriana.pdam.inmoboscoapi.others.Storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final StorageService storageService;

    private final OwnerService ownerService;

    public User createUser(CreateUserRequest createUserRequest, EnumSet<UserRole> roles) {

        User user = User.builder()
                .firstname(createUserRequest.getFirstname())
                .lastname(createUserRequest.getLastname())
                .username(createUserRequest.getUsername())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .phoneNumber(createUserRequest.getPhoneNumber())
                .dni(createUserRequest.getDni())
                .avatar("default.jpeg")
                .email(createUserRequest.getEmail())
                .birthdate(LocalDate.parse(createUserRequest.getBirthdate()))
                .rol(roles)
                .build();

        return save(user);
    }


    public User save(User u) {
        return userRepository.save(u);
    }

    public User createUserWithWorkerRole(CreateUserRequest createUserRequest) {
        return createUser(createUserRequest, EnumSet.of(UserRole.WORKER));
    }

    public void addOwnerRole(UUID id) {
        User user = findUserById(id);
        user.addUserRole(UserRole.OWNER);
        save(user);

    }

    public User createUserWithUserRole(CreateUserRequest createUserRequest) {
        return createUser(createUserRequest, EnumSet.of(UserRole.USER));
    }

    public List<CreateUserResponse> findAllUsers() {
        List<User> data = userRepository.findAll();
        return data.stream().map(CreateUserResponse::createUserResponseFromUser).collect(Collectors.toList());
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User findUserByUsername(String username) {
        return userRepository.findFirstByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public boolean userExistByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User editUserFindById(UUID id, EditUserRequest editUserRequest) {
        User user = findUserById(id);
        user.setFirstname(editUserRequest.getFirstname());
        user.setLastname(editUserRequest.getLastname());
        user.setUsername(editUserRequest.getUsername());
        user.setDni(editUserRequest.getDni());
        user.setPhoneNumber(editUserRequest.getPhoneNumber());
        user.setEmail(editUserRequest.getEmail());
        user.setBirthdate(LocalDate.parse(editUserRequest.getBirthdate()));

        return save(user);

    }

    public CreateUserResponse editUserFindByUsername(String username) {
        return CreateUserResponse.createUserResponseFromUser(findUserByUsername(username));
    }

    public User editPassword(EditUserPassword editUserPassword, User u) {
        if (!passwordEncoder.matches(editUserPassword.getNewPassword(), editUserPassword.getRepeatNewPassword()))
            throw new PasswordNotMatchException();
        return userRepository.findFirstByUsername(u.getUsername()).map(user -> {
            user.setPassword(passwordEncoder.encode(editUserPassword.getNewPassword()));
            user.setLastPasswordChangeAt(LocalDateTime.now());
            return save(user);
        }).orElseThrow(() -> new UserNotFoundException(u.getId()));
    }

    public void deleteUserByID(UUID id) {
        User u = findUserById(id);
        userRepository.delete(u);
    }


    public void setAvatarToUser(String name, User user) {
        user.setAvatar(name);
        userRepository.save(user);
    }

    public Page<AllUserDataDto> getAllUsers(List<SearchCriteria> params, Pageable pageable) {
        GenericSpecificationBuilder<User> userGenericSpecificationBuilder =
                new GenericSpecificationBuilder<>(params);
        Specification<User> spec = userGenericSpecificationBuilder.build();
        Page<AllUserDataDto> result = userRepository.findAll(spec, pageable).map(AllUserDataDto::fromUser);
        if (result.isEmpty())
            throw new EmptyUserListException();
        return result;

    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public User changeUserStatus(UUID id) {
        User user = findUserById(id);
        user.setEnabled(!user.isEnabled());
        return userRepository.save(user);
    }

    public User editUser(EditUserRequest newInfo,User u) {
      return  save(EditUserRequest.createUserFromEditUserRequest(newInfo,u));
    }

    public User createUserFromAdmin(CreateUserFromAdminDTO createUserFromAdminDTO){
       return save(CreateUserFromAdminDTO.createUserFromAdmin(createUserFromAdminDTO));


    }


}





