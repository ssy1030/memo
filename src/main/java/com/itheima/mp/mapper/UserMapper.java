package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.mp.domain.po.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper extends BaseMapper<User> {


    // 必须是ew
    public int queryCustomSql(@Param("ew")LambdaQueryWrapper<User> wrapper,@Param("amount") int  amount);
}
