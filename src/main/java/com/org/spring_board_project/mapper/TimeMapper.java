package com.org.spring_board_project.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    // 어노테이션 혹은 XML로 SQL 작성
    @Select("select now()")
    String getTime();
}
