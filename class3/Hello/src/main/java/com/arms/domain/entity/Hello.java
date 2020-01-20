package com.arms.domain.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Hello {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    private String name;
}
