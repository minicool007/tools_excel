package main.dao;

import main.pojo.HospitalFamilyVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HosMapper {
    List<HospitalFamilyVo> selectHosByCutom();

    //List<HospitalFamilyVo> selectHos99ByCutom();
}
