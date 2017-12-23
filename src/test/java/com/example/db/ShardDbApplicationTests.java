package com.example.db;

import com.example.db.configuration.Protis1;
import com.example.db.configuration.Protis2;
import com.example.db.domin.User;
import com.example.db.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardDbApplicationTests {
	@Autowired
	UserService userService;

	@Test
	public void contextLoads() {
		User user = new User();
		user.setAge(12);
		user.setName("张三11");
		int inserUser = userService.inserUser(user);
		System.out.println(inserUser);
	}



}
