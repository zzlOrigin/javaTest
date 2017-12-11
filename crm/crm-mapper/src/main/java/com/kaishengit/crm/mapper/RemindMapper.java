package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Remind;
import com.kaishengit.crm.Example.RemindExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RemindMapper {
    long countByExample(RemindExample example);

    int deleteByExample(RemindExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Remind record);

    int insertSelective(Remind record);

    List<Remind> selectByExample(RemindExample example);

    Remind selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Remind record, @Param("example") RemindExample example);

    int updateByExample(@Param("record") Remind record, @Param("example") RemindExample example);

    int updateByPrimaryKeySelective(Remind record);

    int updateByPrimaryKey(Remind record);

    List<Remind> findAllByStaffId(Integer id);
}