package com.gyqstd.cloudimagelibrary.service;

import com.gyqstd.cloudimagelibrary.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author guyuqi
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-12-21 10:40:37
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);


    /**
     * 加密
     * @param userPassword 用户密码
     * @return 加密后的密码
     */
    public String getEncryptPassword(String userPassword);
}
