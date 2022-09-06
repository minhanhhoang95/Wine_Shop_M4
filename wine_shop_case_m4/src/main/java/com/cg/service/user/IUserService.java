package com.cg.service.user;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.model.dto.UserListDTO;
import com.cg.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<UserDTO> findUserDTOByUsername(String username);

    Boolean existsByUsername(String email);



    UserListDTO saveUserDTO(User user);
    List<UserListDTO> findAllUserListDTO();

    Optional<UserListDTO> findUserListDTOByUsername(String username);

    Optional<UserListDTO> findUserListDTOByUsernamePassword(String username);

    Optional<UserListDTO> findUserListDTOByPhone(String username);

    Optional<UserListDTO> findUserListDTOById(Long id);

    Boolean existsById(Long id);


    Boolean existsByUsernameAndIdIsNot(String email, Long id);

    User saveNoPassword(User user);
}
