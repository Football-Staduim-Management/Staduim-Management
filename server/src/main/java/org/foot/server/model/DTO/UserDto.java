package org.foot.server.model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private  Long id;

    private  String name;

    private  String email;

    private String phone;

    private  String password;

    private  boolean isAdmin;
}
