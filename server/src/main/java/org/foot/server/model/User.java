package org.foot.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Column(name = "phone")
    private String phone;
    @Column(name="User_Pwd")
    private @NotNull String password;
    @Column(name="Admin_Role")
    private @NotNull boolean isAdmin;
    @ManyToOne(cascade = CascadeType.ALL)
    private Offer offer;
    @OneToMany(mappedBy = "userManager")
    private List<Stadium> stadium;

}
