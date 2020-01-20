package com.arms.app.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserEditForm {

    @NotNull
    private Integer userId;

    @NotEmpty
    private String name;
    
    private String email;

    @NotEmpty
    private String password;
}
