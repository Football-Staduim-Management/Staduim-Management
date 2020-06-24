package org.foot.server.service;

import org.foot.server.DAL.MatchsRepository;
import org.foot.server.DAL.PositionRepository;
import org.foot.server.DAL.StadiumRepository;
import org.foot.server.model.DTO.StadiumDto;
import org.foot.server.model.Match;
import org.foot.server.model.Position;
import org.foot.server.model.SearchInfo;
import org.foot.server.model.Stadium;
import org.foot.server.model.mapper.StadiumMapper;
import org.foot.server.model.mapper.StadiumMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
/**
 * This file is the service that provide the CRUD of the stadiums
 * in the data base.
 *
 * Also it provides the "search" function that search the availble stadiums in
 * a specified zone on the map.  For that, it use the "haversine Distance" function.
 *
 * */
@Service
public class StadiumManagementService {

    @Autowired
    StadiumRepository stadiumRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    MatchsRepository matchsRepository;

    StadiumMapper stadiumMapper = new StadiumMapperImpl();

    List<Long> relativePoss = new ArrayList<Long>();


    public StadiumDto creatStadium(StadiumDto stadiumDto) throws Exception{
        if(stadiumRepository.findByName(stadiumDto.getName())!=null){
            throw new Exception("Stadium name already exist");
        }
        return stadiumMapper.StadiumtoStadiumDto(stadiumRepository.save(stadiumMapper.StadiumDtotoStadium(stadiumDto)));
    }

    public void deleteStadium(StadiumDto stadiumDto){
        stadiumRepository.delete(stadiumMapper.StadiumDtotoStadium(stadiumDto));
    }

    public StadiumDto readStadium(String name){
        return stadiumMapper.StadiumtoStadiumDto(stadiumRepository.findByName(name));
    }

    public List<StadiumDto> readAll(){
        List<Stadium> stadiumList = (List) stadiumRepository.findAll();
        return stadiumList
                .stream()
                .map((Stadium element) -> {
                    return stadiumMapper.StadiumtoStadiumDto(element);
                })
                .collect(Collectors.toList());
    }

    public List<?> search(SearchInfo searchInfo){
        List<Position> positions = (List)this.positionRepository.findAll();
        List<Position> flitredPositions = positions.stream().filter(element->{
            double d = haversineDistance(element.getAlt(), element.getLng(), searchInfo.getZoneCenter().getAlt()
                    , searchInfo.getZoneCenter().getLng());

            if(d< searchInfo.getZoneRaduis()){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        List<Stadium> stadiums = this.stadiumRepository.findByPositionIn(flitredPositions );
        stadiums=stadiums.stream().filter(element->this.disponibility(searchInfo.getDate(), searchInfo.getTime(),element,5400)).collect(Collectors.toList());
        List<StadiumDto> stadiumsDto = stadiums.stream().map(stadium -> stadiumMapper.StadiumtoStadiumDto(stadium)).collect(Collectors.toList());
        for(int i=0;i<stadiums.size();i++){
            stadiumsDto.get(i).setRelativePos(this.relativePoss.get(i));
        }
        return stadiumsDto;
    }

    private double haversineDistance(float lat1,float lon1, float lat2, float lon2){

        int R = 6371;
        double dLat = deg2rad(lat2-lat1);
        double dLon = deg2rad(lon2-lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        this.relativePoss.add((long)(d*1000));

        return d*1000;
    }

    private double deg2rad(double deg){
        return  deg * (Math.PI/180);
    }

    private boolean disponibility(String date, String time, Stadium stadium, int duration){

        LocalDateTime reqDateTime = this.getLocalDateTime(date,time);

        List<Match> matches = matchsRepository.findByStadium_id(stadium.getId());
        if(matches.size() ==0 ){
            return true;
        }else{
            Collections.sort(matches);
            for(int i=0;i<matches.size();i++){
                if(this.getLocalDateTime(matches.get(i).getDate(),matches.get(i).getTime()).compareTo(reqDateTime)>0){
                    Duration d = Duration.between(this.getLocalDateTime(matches.get(i).getDate(),matches.get(i).getTime())
                            ,this.getLocalDateTime(matches.get(i+1).getDate(),matches.get(i+1).getTime()));
                    if(d.getSeconds()>=2*duration){
                        return true;
                    }else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private LocalDateTime getLocalDateTime(String date, String time){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate localDate = LocalDate.parse(date,df);
        String[] t = time.split(" ");

        LocalTime localTime = LocalTime.parse(t[0]);

        if(t.length>1 && t[1].equals("PM")){
            localTime = localTime.plusHours(12);
        }

        return LocalDateTime.of(localDate,localTime);
    }


}
