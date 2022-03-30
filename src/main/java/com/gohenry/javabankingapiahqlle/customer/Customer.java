package com.gohenry.javabankingapiahqlle.customer;

import com.gohenry.javabankingapiahqlle.common.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = Constants.SENSIBLE_MAX_LENGTH)
    @NotBlank(message = "Name can not be blank.")
    @Size(min = Constants.SENSIBLE_MIN_LENGTH, max = Constants.SENSIBLE_MAX_LENGTH,
            message = "Name does not meet size requirements; must be between ["
                    + Constants.SENSIBLE_MIN_LENGTH + ", " + Constants.SENSIBLE_MAX_LENGTH + "].")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
