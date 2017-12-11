package com.kaishengit.mapper;

import com.kaishengit.entity.kaolaType;
import com.kaishengit.entity.kaolaTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface kaolaTypeMapper {
    long countByExample(kaolaTypeExample example);

    int deleteByExample(kaolaTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(kaolaType record);

    int insertSelective(kaolaType record);

    List<kaolaType> selectByExample(kaolaTypeExample example);

    kaolaType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") kaolaType record, @Param("example") kaolaTypeExample example);

    int updateByExample(@Param("record") kaolaType record, @Param("example") kaolaTypeExample example);

    int updateByPrimaryKeySelective(kaolaType record);

    int updateByPrimaryKey(kaolaType record);
}