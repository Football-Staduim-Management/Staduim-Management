package org.foot.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchInfo {
    private Float zoneRaduis;
    private String time;
    private String date;
    private Position zoneCenter;

}

