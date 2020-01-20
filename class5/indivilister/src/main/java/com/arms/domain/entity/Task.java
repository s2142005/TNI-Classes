package com.arms.domain.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name= "task")
public class Task {

    @GeneratedValue
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @NotEmpty
    private String name;

    @NotNull
    private boolean status;

    private Date createdDate;

    private Date updatedDate;
}

