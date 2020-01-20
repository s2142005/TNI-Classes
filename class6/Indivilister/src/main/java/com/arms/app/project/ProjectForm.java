package com.arms.app.project;

import lombok.Data;

@Data
public class ProjectForm {
    private int id;
    private String name;

    public ProjectForm(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProjectForm() {}
}
