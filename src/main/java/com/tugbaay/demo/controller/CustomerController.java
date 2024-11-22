package com.tugbaay.demo.controller;

import com.tugbaay.demo.models.Customer;
import com.tugbaay.demo.models.Customers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/v1/getByName")
    public Customer getCustomer(@RequestParam("name") String name) {
        return new Customer(UUID.randomUUID().toString(), "", name);
    }

    @GetMapping("/v2/getByName/{customerId}")
    public Customer v2getCustomer(@PathVariable String customerId,
                                  @RequestParam(value = "name", defaultValue = "customerName") String name) {
        return new Customer(UUID.randomUUID().toString(), customerId, name);
    }

    @GetMapping("/v3/getByName/{customerId}")
    public Customer v3getCustomer(@PathVariable String customerId,
                                  @RequestParam(value = "name", defaultValue = "customerName") String name,
                                  @RequestHeader HttpHeaders headers) {
        List<String> authorization = headers.get("Authorization");
        return new Customer(UUID.randomUUID().toString(), customerId, authorization.get(0));
    }

    @PostMapping("/create")
    public ResponseEntity<Customers> createCustomer(@RequestBody Customers customer) {
        customer.setId(UUID.randomUUID().toString());
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable String customerId,
                                                    @RequestBody Customers customer) {
        customer.setId(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PatchMapping("/update/{customerId}")
    public ResponseEntity<Customers> updateCustomerWithSpesificArea(@PathVariable String customerId,
                                                                    @RequestBody Customers customer) {
        customer.setId(customerId);
        customer.setName("Tugba");
        customer.setSurname("Ay Gorucu");
        customer.setEmail(customer.getEmail());
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerId) {
        return new ResponseEntity<>(customerId + " is deleted.", HttpStatus.OK);
    }
}
