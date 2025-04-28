package com.example.companyInventortMgmt.departments;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="departments")
@Getter
@Setter
public class DepartmentsEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name",nullable = false, unique = true, length = 50)
    private String name;
    @Column(name="code",nullable = false, unique = true, length = 5)
    private String code;
}
