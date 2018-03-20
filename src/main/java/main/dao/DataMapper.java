package main.dao;

import main.pojo.DataVo;
import main.pojo.FzDataVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper{
    List<DataVo> selectDataByCutom();

    List<FzDataVo> selectfzDataByCutom();
}
