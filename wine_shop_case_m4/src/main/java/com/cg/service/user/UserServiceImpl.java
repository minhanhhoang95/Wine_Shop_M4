package com.cg.service.user;

import com.cg.model.LocationRegion;
import com.cg.model.User;
import com.cg.model.UserPrinciple;
import com.cg.model.dto.UserDTO;
import com.cg.model.dto.UserListDTO;
import com.cg.repository.LocationRegionRepository;
import com.cg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserDTO> findUserDTOByUsername(String username) {
        return userRepository.findUserDTOByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserListDTO saveUserDTO(User user) {
        return userRepository.saveUserDTO(user);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
//        return (UserDetails) userOptional.get();
    }




    @Override
    public List<UserListDTO> findAllUserListDTO() {
        return userRepository.findAllUserListDTO();
    }

    @Override
    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }


    @Override
    public Optional<UserListDTO> findUserListDTOByUsername(String username) {
        return userRepository.findUserListDTOByUsername(username);
    }

    @Override
    public Optional<UserListDTO> findUserListDTOByUsernamePassword(String username) {
        return userRepository.findUserListDTOByUsernamePassword(username);
    }

    @Override
    public Optional<UserListDTO> findUserListDTOByPhone(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<UserListDTO> findUserListDTOById(Long id) {
        return userRepository.findUserListDTOById(id);
    }

    @Override
    public Boolean existsByUsernameAndIdIsNot(String email, Long id) {
        return userRepository.existsByUsernameAndIdIsNot(email,id);
    }

    @Override
    public User saveNoPassword(User user) {
        LocationRegion locationRegion = locationRegionRepository.save(user.getLocationRegion());
        user.setLocationRegion(locationRegion);
        return userRepository.save(user);
    }
}
