package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Chance;
import com.kaishengit.crm.Example.ChanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChanceMapper {
    long countByExample(ChanceExample example);

    int deleteByExample(ChanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Chance record);

    int insertSelective(Chance record);

    List<Chance> selectByExample(ChanceExample example);

    Chance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Chance record, @Param("example") ChanceExample example);

    int updateByExample(@Param("record") Chance record, @Param("example") ChanceExample example);

    int updateByPrimaryKeySelective(Chance record);

    int updateByPrimaryKey(Chance record);

    List<Chance> findAllChange(Integer id);
}