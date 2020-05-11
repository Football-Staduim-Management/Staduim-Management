package org.foot.server.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Match implements Comparable<Match> {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String time;

    @Column
    private String date;

    @Override
    public int compareTo(Match match) {
        LocalTime localTime1 =   LocalTime.parse(this.time);
        LocalTime localTime2 =   LocalTime.parse(match.getTime());

        return localTime1.compareTo(localTime2);
    }
}
