package com.zetheta.kycamlorchestration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String customerId;

    private String fullName;

    private String email;

    private String phone;

    private String panNumber;

    private String aadhaarNumber;

    private String status;

    private LocalDateTime createdAt;
}