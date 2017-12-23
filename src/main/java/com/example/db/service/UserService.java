package com.example.db.service;

import com.example.db.configuration.DatabaseContextHolder;
import com.example.db.domin.User;
import com.example.db.enuma.DatabaseType;
import com.example.db.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/24 0:13
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public int inserUser(User user){
        int i = Math.abs(user.getName().hashCode()) % 3;
        //指定对应的数据源
        if (i == 1){
            DatabaseContextHolder.setDatabaseType(DatabaseType.mybd1);
            System.out.println("===========mybd1================");
        } else if (i==2){
            DatabaseContextHolder.setDatabaseType(DatabaseType.mybd2);
            System.out.println("===========mybd2================");
        } else {
            DatabaseContextHolder.setDatabaseType(DatabaseType.mybd3);
            System.out.println("===========mybd3================");
        }
        int result = userMapper.inserUser(user);
        return result;
    }



    public static void main(String[] args) {
        int i = Math.abs("张三11".hashCode()) % 3;
        System.out.println(i);
    }
}
