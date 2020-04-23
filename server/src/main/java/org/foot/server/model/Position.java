package org.foot.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Position {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Float alt;
    @Column
    private Float lng;
}
