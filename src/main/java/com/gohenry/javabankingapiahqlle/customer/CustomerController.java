package com.gohenry.javabankingapiahqlle.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// todo hateoas
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public Page<Customer> findAll(Pageable pageable) {
        return customerService.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer newCustomer(@Valid @RequestBody Customer customer) {
        return customerService.newCustomer(customer);
    }
}
