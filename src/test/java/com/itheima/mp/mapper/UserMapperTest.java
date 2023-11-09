package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
        user.setId(5L);
        user.setUsername("Lucy");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);
    }


    @Test
    void testQueryByIds() {
        List<User> users = userMapper.selectBatchIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setBalance(20000);
        userMapper.updateById(user);
    }

    @Test
    void testDeleteUser() {
        userMapper.deleteById(5L);
    }

    @Test
    void toQueryUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("id","username","balance")
                .like("username","o")
                .ge("balance",1000);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
    // update set balance = 2000 where username = ‘Jack’
    @Test
    void toUpdateList() {
        User user = new User();
        user.setBalance(2000);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username","Jack");

        int count = userMapper.update(user,queryWrapper);
        System.out.println(count);
    }

    // update balance = balance-1 where id in ()
    @Test
    void updateWrapper() {
        List<Long> ids = List.of(1L,2L,5L);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
        updateWrapper.in("id",ids)
                .setSql("balance = balance-200");
        int count = userMapper.update(null,updateWrapper);
        System.out.println(count);
    }
    //推荐lambda ，不使用硬编码
    @Test
    void testlambdaWrapper() {
        List<Long> ids = List.of(1L,2L,5L);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>();
        wrapper.select(User:: getId,User :: getBalance)
                .like(User :: getUsername,"o")
                .ge(User::getBalance,2000);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
    @Test
    void testCustomSqlUpdate() {
        List<Long> ids = List.of(1L,2L,5L);
        int amount = 200;

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>();
        wrapper.in(User:: getId,ids);
        int count = userMapper.queryCustomSql(wrapper,amount);
    }


}