package com.arithmeticcalculator.calculator.service;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arithmeticcalculator.calculator.entities.User;
import com.arithmeticcalculator.calculator.model.UserDTO;
import com.arithmeticcalculator.calculator.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserDTO> findByUsername(String username) {

        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(userRepository.findByUsername(username), UserDTO.class);
        return Optional.of(userDTO);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        
        ModelMapper modelMapper = new ModelMapper();
        userDTO.setId(UUID.randomUUID().toString());
        User user = userRepository.save(modelMapper.map(userDTO, User.class));

        
        return new UserDTO(user.getId(), user.getPassword(), user.getStatus(), user.getUsername(), null, null);
    }

    

    

}
