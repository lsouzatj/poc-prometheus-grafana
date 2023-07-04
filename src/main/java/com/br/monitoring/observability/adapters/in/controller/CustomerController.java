package com.br.monitoring.observability.adapters.in.controller;

import com.br.monitoring.observability.adapters.in.controller.requestDTO.CustomerRequestDTO;
import com.br.monitoring.observability.adapters.in.controller.responseDTO.CustomerResponseDTO;
import com.br.monitoring.observability.application.core.model.Customer;
import com.br.monitoring.observability.application.ports.in.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final InsertCustomerInputPort insertCustomerInputPort;
    private final FindAllCustomerInputPort findAllCustomerInputPort;
    private final FindByIdCustomerInputPort findByIdCustomerInputPort;
    private final DeleteByIdCustomerInputPort deleteByIdCustomerInputPort;
    private final UpdateCustomerInputPort updateCustomerInputPort;

    @PostMapping("/save/{zipCode}")
    public ResponseEntity<CustomerResponseDTO> save(@RequestBody CustomerRequestDTO customerRequestDTO,
                                                    @PathVariable("zipCode") String zipCode){
        log.info("Save customer");
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequestDTO, customer);
        var customerSaved = insertCustomerInputPort.save(customer, zipCode);
        return Optional.ofNullable(customerSaved).map((c) ->{
            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
            BeanUtils.copyProperties(c, customerResponseDTO);
            return ResponseEntity.status(HttpStatus.OK).body(customerResponseDTO);
        }).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CustomerResponseDTO>> findAll(){
        log.info("Find all customers");
        List<Customer> listCustomer = findAllCustomerInputPort.findAll();
        if (!listCustomer.isEmpty()){
            List<CustomerResponseDTO> customerResponseList = listCustomer.stream()
                    .map(customer->{
                        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
                        BeanUtils.copyProperties(customer, customerResponseDTO);
                        return customerResponseDTO;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(customerResponseList);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable("id") Long id) {
        log.info("Find customer by id");
        return findByIdCustomerInputPort.findById(id).map(customer -> {
            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
            BeanUtils.copyProperties(customer, customerResponseDTO);
            return ResponseEntity.status(HttpStatus.OK).body(customerResponseDTO);
                }
        ).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomerResponseDTO> deleteById(@PathVariable("id") Long id){
        log.info("Delete customer by id");
        return deleteByIdCustomerInputPort.delete(id).map(customer -> {
            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
            BeanUtils.copyProperties(customer, customerResponseDTO);
            return ResponseEntity.status(HttpStatus.OK).body(customerResponseDTO);
        }).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@RequestBody CustomerRequestDTO customerRequest, @PathVariable("id") Long id){
        log.info("Update customer");
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequest, customer);
        return updateCustomerInputPort.update(customer, id).map(newCustomer ->{
            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
            BeanUtils.copyProperties(newCustomer, customerResponseDTO);
            return ResponseEntity.status(HttpStatus.OK).body(customerResponseDTO);
        }).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
}
