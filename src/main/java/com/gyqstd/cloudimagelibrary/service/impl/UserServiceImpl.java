package com.gyqstd.cloudimagelibrary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyqstd.cloudimagelibrary.model.entity.User;
import com.gyqstd.cloudimagelibrary.service.UserService;
import com.gyqstd.cloudimagelibrary.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author guyuqi
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-12-21 10:40:37
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




