package org.foot.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Stadium {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToOne
    private Position position;
    @ManyToOne
    @JoinColumn
    private User userManager;
}
