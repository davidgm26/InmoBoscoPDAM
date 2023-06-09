package com.salesianostriana.pdam.inmoboscoapi.user.controller;

import com.salesianostriana.pdam.inmoboscoapi.search.util.SearchCriteria;
import com.salesianostriana.pdam.inmoboscoapi.search.util.SearchCriteriaExtractor;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.AllUserDataDto;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserRequest;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import com.salesianostriana.pdam.inmoboscoapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<Page<AllUserDataDto>> getAllUsersInfo(@RequestParam(value = "search", defaultValue = "") String search,
                                                                @PageableDefault(size = 5, page = 0) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);
        return ResponseEntity.ok(userService.getAllUsers(params, pageable));
    }

    @PutMapping("/ban/{id}")
    public ResponseEntity<AllUserDataDto> changeUserStatus(@PathVariable UUID id) {
        return ResponseEntity.ok(AllUserDataDto.fromUser(userService.changeUserStatus(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        userService.deleteUserByID(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<AllUserDataDto>editUserFromAdmin(@PathVariable UUID id,@RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.ok(AllUserDataDto.fromUser(userService.editUserFindById(id,createUserRequest)));
    }


}
