package com.arms.app.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserAddForm {

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}

