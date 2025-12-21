package com.gyqstd.cloudimagelibrary.service;

import com.gyqstd.cloudimagelibrary.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gyqstd.cloudimagelibrary.model.vo.user.LoginUserVO;
import jakarta.servlet.http.HttpServletRequest;

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


    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获得脱敏后的登录用户信息
     * @param user
     * @return
     */
    LoginUserVO getLoginUserVO(User user);
}
