package org.foot.server.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.*;

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
    private String player1;

    @Column
    private String player2;

    @Column
    private String player3;

    @Column
    private String time;

    @Column
    private String date;

    @Override
    public int compareTo(Match match) {
        LocalTime localTime1 =   LocalTime.parse(this.time);
        LocalTime localTime2 =   LocalTime.parse(match.getTime());
        LocalDate localDate1 = LocalDate.parse(this.date);
        LocalDate localDate2 = LocalDate.parse(match.getDate());
        LocalDateTime localDateTime1 = LocalDateTime.of(localDate1,localTime1);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate2,localTime2);

        return localDateTime1.compareTo(localDateTime2);
    }
}
