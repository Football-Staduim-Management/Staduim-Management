package org.foot.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User_Entity")
public class User {
    @Id
    @GeneratedValue
    private @NotNull Long id;
    @Column(name="User_Name")
    private  @NotNull String name;
    @Column(name="User_Email")
    private @NotNull String email;
    @Column(name="User_Pwd")
    private @NotNull String password;
    @Column(name="Admin_Role")
    private @NotNull boolean isAdmin;
}
