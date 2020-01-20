package com.arms.domain.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name= "project")
public class Project {

    @GeneratedValue
    @Id
    private int id;

    @NotEmpty
    private String name;


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> taskList;

    private Date createdDate;

    private Date updatedDate;
}

