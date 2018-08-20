package com.stitp.recommendation.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import  com.stitp.recommendation.result.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from songdetails where Type=#{Type} and Number=#{Number}")
    List<Order> findsongbynumber(Order order);
    @Select("select * from songdetails where Type=#{Type}")
    List<Order> findsong(int Type);
    @Select("select * from songdetails where ID=#{id}")
    Order findsongbyid(int id );
    @Select("select * from song_recommend where Type=#{Type}")
    List<Order> recommendByType(int Type);
}
