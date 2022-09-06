package com.cg.controller.rest;


import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.LocationRegion;
import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.model.dto.UserListDTO;
import com.cg.service.locationRegion.LocationRegionService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private AppUtils appUtil;

    @Autowired
    private IUserService userService;
    @Autowired
    private LocationRegionService locationRegionService ;

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<UserListDTO> userDTOList = userService.findAllUserListDTO();

        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        Optional<User> userOptional = userService.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException("Invalid user ID");
        }

        return new ResponseEntity<>(userOptional.get().toUserListDTO(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody UserListDTO userListDTO, BindingResult bindingResult) {

        new UserListDTO().validate(userListDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        userListDTO.setId(String.valueOf(0L));
//        userListDTO.getLocationRegion().setId(0L);
        LocationRegion locationRegion = userListDTO.getLocationRegion().toLocationRegion();
        locationRegion.setId(0L);
        Boolean exitsEmail = userService.existsByUsername(userListDTO.getUsername());

        if (exitsEmail) {
            throw new EmailExistsException("Email already exists");
        }
        LocationRegion newLocationRegion = locationRegionService.save(locationRegion);
        userListDTO.setLocationRegion(newLocationRegion.toLocationRegionDTO());
        User newUser = userService.save(userListDTO.toUser());

        return new ResponseEntity<>(newUser.toUserListDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/update")
//    @PreAuthorize("ADMIN")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody UserListDTO userListDTO, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        Boolean existId = userService.existsById(Long.parseLong(userListDTO.getId()));

        if (!existId) {
            throw new ResourceNotFoundException("Customer ID invalid");
        }

        Boolean existEmail = userService.existsByUsernameAndIdIsNot(userListDTO.getUsername(), Long.parseLong(userListDTO.getId()));

        if (existEmail) {
            throw new DataInputException("Email is exist");
        }

        userListDTO.getLocationRegion().setId(0L);

        User updatedUser = userService.saveNoPassword(userListDTO.toUser());

        return new ResponseEntity<>(updatedUser.toUserListDTO(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doDelete(@PathVariable Long id) {
        System.out.println("delete method");
        Optional<UserListDTO> user = userService.findUserListDTOById(id);

        if (user.isPresent()) {
            try {
                user.get().setDeleted(true);
                userService.save(user.get().toUser());
                return new ResponseEntity<>(HttpStatus.ACCEPTED);

            } catch (DataInputException e) {
                throw new DataInputException("Invalid suspension information");
            }
        } else {
            throw new DataInputException("Invalid apartment information");
        }
    }
}
