package com.example.petcare.mapper;

import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BoardMapper {
    Board selectById(@Param("id") Integer id);

    NearByBoardDTO selectIdWithBidAndAid(@Param("id") Integer id);
}
