package com.example.db.mapper;

import com.example.db.domin.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/24 0:13
 */
@Mapper
public interface UserMapper {
    int inserUser(User user);
}
