package com.learning.accounts.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "CUSTOMER")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    private String name;
    private String email;
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;
}
