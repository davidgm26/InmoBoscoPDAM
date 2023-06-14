package com.salesianostriana.pdam.inmoboscoapi.Owner.service;

import com.salesianostriana.pdam.inmoboscoapi.Owner.model.Owner;
import com.salesianostriana.pdam.inmoboscoapi.Owner.repository.OwnerRepository;
import com.salesianostriana.pdam.inmoboscoapi.exception.UserHaveProperties;
import com.salesianostriana.pdam.inmoboscoapi.user.repository.UserRepository;
import com.salesianostriana.pdam.inmoboscoapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;


    public Owner findById(UUID id){
        return ownerRepository.findById(id).orElseThrow(UserHaveProperties::new);
    }

    public void deleteOwner(UUID id){
        Owner o = findById(id);
        deleteOwnerProperty(o);
        ownerRepository.delete(o);
    }

    public void deleteOwnerProperty(Owner o){
        if(!o.getOwns().isEmpty()){
            o.getOwns().forEach(property ->
                    property.setOwner(null));
        }else{
            throw new UserHaveProperties();
        }

    }

}
