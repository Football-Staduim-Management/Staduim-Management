package org.foot.server.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.foot.server.model.Position;
import org.foot.server.model.User;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StadiumDto {

    private Long id;
    private String name;
    private Position position;
    private User userManager;
    private Long relativePos;
}
