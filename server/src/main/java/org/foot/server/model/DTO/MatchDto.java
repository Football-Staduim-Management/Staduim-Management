package org.foot.server.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.foot.server.model.User;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {

    private Long id;
    private String stadiumName;
    private String userEmail;
    private String player1Email;
    private String player2Email;
    private String player3Email;
    private String time;
    private String date;
}
