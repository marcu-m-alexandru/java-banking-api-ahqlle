package com.gohenry.javabankingapiahqlle.bankaccount.history;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {
        @Index(columnList = "outBankAccountId"),
        @Index(columnList = "inBankAccountId")
})
@Getter
@Setter
@ToString
public class BankAccountHistory { // todo rename

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // todo probably better to use "from" and "to"
    @Column(nullable = false)
    private long outBankAccountId;

    @Column(nullable = false)
    private long inBankAccountId;

    @Column(nullable = false)
    private long amount = 0;

    @CreatedDate
    private long createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BankAccountHistory that = (BankAccountHistory) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
