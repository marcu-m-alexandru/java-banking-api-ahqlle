package com.gohenry.javabankingapiahqlle.bankaccount;

import com.gohenry.javabankingapiahqlle.customer.Customer;
import com.gohenry.javabankingapiahqlle.customer.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BankAccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    private void setup() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("test");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
    }

    @Test
    public void testFindById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/bank-accounts/1"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(startsWith("Could not find account")));
    }

    @Test
    public void testNewBankAccount() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/customers/1/bank-accounts")
                        .param("amount", "1000"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"customerId\":1,\"amount\":1000}"));
    }
}
