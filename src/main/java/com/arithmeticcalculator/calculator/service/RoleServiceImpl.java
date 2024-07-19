package com.arithmeticcalculator.calculator.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arithmeticcalculator.calculator.entities.Role;
import com.arithmeticcalculator.calculator.model.RoleDTO;
import com.arithmeticcalculator.calculator.model.RoleName;
import com.arithmeticcalculator.calculator.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<RoleDTO> findByName(RoleName name) {

        ModelMapper modelMapper = new ModelMapper();
        Optional<Role> role = roleRepository.findByName(name);
        RoleDTO roleDTO = modelMapper.map(role.get(), RoleDTO.class);
        
        return Optional.of(roleDTO);
        
    }

    

}
