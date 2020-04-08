package org.foot.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Offer {
    @Id
    @GeneratedValue
    private Long Id;
    @Column(name = "Offre_type")
    private String type;
    @Column(name = "Offre_statrDate")
    private LocalDateTime statrDate;
    @Column(name = "Offre_payementType")
    private PayementType payementType;
    @Column(name = "Offre_selectedPayement")
    private String selectedPayement;
    @OneToMany(mappedBy = "offer")
    private List<User> userList;
}
