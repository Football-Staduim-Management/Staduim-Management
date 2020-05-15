package org.foot.server.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {

    private Long id;
    private Long stadium_id;
    private Long user_id;
    private String time;
    private String date;
}
