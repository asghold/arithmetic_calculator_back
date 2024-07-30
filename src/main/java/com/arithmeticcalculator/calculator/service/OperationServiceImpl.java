package com.arithmeticcalculator.calculator.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arithmeticcalculator.calculator.entities.Operation;
import com.arithmeticcalculator.calculator.entities.RecordsOperation;
import com.arithmeticcalculator.calculator.entities.User;
import com.arithmeticcalculator.calculator.model.DataGenericDTO;
import com.arithmeticcalculator.calculator.model.OperationDTO;
import com.arithmeticcalculator.calculator.model.RecordDTO;
import com.arithmeticcalculator.calculator.model.UserDTO;
import com.arithmeticcalculator.calculator.repositories.OperationRepository;
import com.arithmeticcalculator.calculator.repositories.RecordsOperationRepository;
import com.arithmeticcalculator.calculator.repositories.UserRepository;

@Service
public class OperationServiceImpl implements OperationService{

    @Value("${app.initial.balance}")
    private Double initalBalance; 

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private RecordsOperationRepository recordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenerateStringsService generateStringsService;

   

    @Override
    public List<OperationDTO> getOperations() {

        List<Operation> listOperations = operationRepository.findAll();

        
        return listOperations
                .stream()
                .map(o -> {
                    return OperationDTO
                            .builder()
                            .id(o.getId())
                            .type(o.getType())
                            .cost(o.getCost())
                            .build();
                    })
                .collect(Collectors.toList());
        
        
    }

    @Override
    public RecordDTO operation(UserDTO userDTO, Long id, DataGenericDTO data) throws Exception {
       RecordsOperation record = new RecordsOperation();
        
        ZoneId defaultZineId = ZoneId.systemDefault();
        LocalDateTime current = LocalDateTime.now();
        //Instant instant = Instant.from(current.atStartOfDay(defaultZineId).toInstant());
        Date date = Date.from(current.atZone(defaultZineId).toInstant());
        User user = userRepository.findByUsername(userDTO.getUsername()).orElseThrow(NoSuchElementException::new);

        Operation operation = operationRepository.findById(id).orElseThrow((NoSuchElementException::new));        
        List<RecordsOperation> lista = recordRepository.findByUser(user);
            
        record.setAmount(operation.getCost());
        record.setDate(date);
        record.setOperation(operation);
        if(operation.getType().equals("Random String")){
            record.setOperationResponse(String.join(",", generateStringsService.generateStrings(data.getNumberStrings(), data.getLength())));
        }else {
            record.setOperationResponse(realizeOperation(data, operation));
        }
        record.setUser(user);
            
        if(lista.isEmpty()){
            record.setUserBalance(initalBalance-operation.getCost());
        }else {
            RecordsOperation max = lista.stream()
                        .max(Comparator.comparing(RecordsOperation::getDate))
                        .orElseThrow(NoSuchElementException::new);
            if(max.getUserBalance()>=operation.getCost()){
            record.setUserBalance(max.getUserBalance()-operation.getCost());
            } else {
                throw new Exception("Insufficient balance");
            }
        }
        record.setId(UUID.randomUUID().toString());
        RecordsOperation result = recordRepository.save(record);

        OperationDTO opDT = new OperationDTO(result.getOperation().getId(),result.getOperation().getCost(),result.getOperation().getType(),null);
        return new RecordDTO(result.getId(),result.getAmount(),result.getDate(),result.getOperationResponse(),result.getUserBalance(),opDT,userDTO);
    }

    private String realizeOperation(DataGenericDTO data, Operation operation) throws ArithmeticException{
        switch (operation.getType()) {
            case "Addition":
                return String.valueOf(data.getNumbers().get(0) + data.getNumbers().get(1));
            case "Subtraction":
                return String.valueOf(data.getNumbers().get(0) - data.getNumbers().get(1));
            case "Multiplication": 
                return String.valueOf(data.getNumbers().get(0) * data.getNumbers().get(1));
            case "Division":
                if(data.getNumbers().get(1)!=0){
                    return String.valueOf(data.getNumbers().get(0) / data.getNumbers().get(1));
                } else {
                    throw new ArithmeticException();
                }
            default:
                return String.valueOf(Math.sqrt(data.getNumbers().get(0)));       
                
        }        
                
    }

    @Override
    public RecordDTO getLastRecord(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername()).orElseThrow(NoSuchElementException::new);
        List<RecordsOperation> lista = recordRepository.findByUser(user);
        if(lista == null || lista.size()==0){
            return new RecordDTO("",0.0,null,null,100.00,null,null);
        }

        RecordsOperation max = lista.stream()
                        .max(Comparator.comparing(RecordsOperation::getDate))
                        .orElseThrow(NoSuchElementException::new);
                        
        OperationDTO opDT = new OperationDTO(max.getOperation().getId(),max.getOperation().getCost(), max.getOperation().getType(),null);
        return new RecordDTO(max.getId(),max.getAmount(),max.getDate(),max.getOperationResponse(),max.getUserBalance(),opDT,userDTO);
        
    }

}
