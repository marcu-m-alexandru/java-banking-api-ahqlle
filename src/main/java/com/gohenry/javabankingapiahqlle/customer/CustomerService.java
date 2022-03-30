package com.gohenry.javabankingapiahqlle.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer newCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
