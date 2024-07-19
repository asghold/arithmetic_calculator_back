package com.arithmeticcalculator.calculator.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.arithmeticcalculator.calculator.entities.RecordsOperation;
import com.arithmeticcalculator.calculator.entities.User;
import com.arithmeticcalculator.calculator.model.OperationDTO;
import com.arithmeticcalculator.calculator.model.RecordDTO;
import com.arithmeticcalculator.calculator.model.UserDTO;
import com.arithmeticcalculator.calculator.repositories.RecordsOperationRepository;
import com.arithmeticcalculator.calculator.repositories.UserRepository;

@Service
public class RecordServiceImpl implements RecordService{

    private static final Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecordsOperationRepository recordsOperationRepository;

    @Override
    public List<RecordDTO> getAll(UserDTO userDTO) throws Exception {
        try{
            User user = userRepository.findById(userDTO.getId()).orElse(null);
            if(user == null){
                throw new NotFoundException();
            }

            List<RecordsOperation> list = recordsOperationRepository.findByUser(user);

            return list
                .stream()
                .map(r -> {
                    return RecordDTO.builder()
                .amount(r.getAmount())
                .date(r.getDate())
                .id(r.getId())
                .operation(new OperationDTO(r.getOperation().getId(), r.getOperation().getCost(), r.getOperation().getType(), null))
                .user(new UserDTO(r.getUser().getId(),"",true,r.getUser().getUsername(),null, null))
                .operationResponse(r.getOperationResponse())
                .userBalance(r.getUserBalance())
                .build();
            }).collect(Collectors.toList());

        }catch(NotFoundException nfe) {
            logger.error(nfe.getMessage());
            throw new Exception("No records");
        }
        
        
    }

    @Override
    public void deleteRecord(String id) {
        try {
            recordsOperationRepository.deleteById(id);
        } catch (NoSuchElementException e){
            throw new NoSuchElementException();
        }
    }

}
