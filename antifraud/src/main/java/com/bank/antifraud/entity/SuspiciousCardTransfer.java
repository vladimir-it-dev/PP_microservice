package com.bank.antifraud.entity;


import com.bank.antifraud.listener.TransferListener;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Getter
@Setter
@ToString
@SuperBuilder
@Entity
@Table(name = "suspicious_card_transfer")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@EntityListeners(TransferListener.class)
public class SuspiciousCardTransfer extends SuspiciousTransfer {

    @Positive Long cardTransferId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SuspiciousCardTransfer that = (SuspiciousCardTransfer) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
