package org.foot.server.service;

import org.foot.server.DAL.PositionRepository;
import org.foot.server.DAL.StadiumRepository;
import org.foot.server.model.DTO.StadiumDto;
import org.foot.server.model.DTO.UserDto;
import org.foot.server.model.Position;
import org.foot.server.model.SearchInfo;
import org.foot.server.model.Stadium;
import org.foot.server.model.mapper.StadiumMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StadiumManagementService {
    @Autowired
    StadiumRepository stadiumRepository;
    @Autowired
    PositionRepository positionRepository;
    StadiumMapper stadiumMapper = Mappers.getMapper(StadiumMapper.class);

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
            double d = haversineDistance(element.getAlt(),element.getLng(), searchInfo.getZoneCenter().getAlt()
                    , searchInfo.getZoneCenter().getLng());

            if(d< searchInfo.getZoneRaduis()){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        List<Stadium> stadiums = this.stadiumRepository.findByPositionIn(flitredPositions );
        List<StadiumDto> stadiumsDto = stadiums.stream().map(stadium -> stadiumMapper.StadiumtoStadiumDto(stadium)).collect(Collectors.toList());
        for(int i=0;i<stadiums.size();i++){
            stadiumsDto.get(i).setRelativePos(this.relativePoss.get(i));
        }
        return stadiumsDto;
    }
    public double haversineDistance(float lat1,float lon1, float lat2, float lon2){

        int R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        double dLon = deg2rad(lon2-lon1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km
        this.relativePoss.add((long)(d*1000));

        return d*1000;
    }
    public double deg2rad(double deg){
        return  deg * (Math.PI/180);
    }
}
