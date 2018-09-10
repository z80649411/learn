package com.dr.jpacase.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@ToString
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long userId;
    private String name;
    private long age;


}
