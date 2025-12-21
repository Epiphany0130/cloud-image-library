package com.gyqstd.cloudimagelibrary.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyqstd.cloudimagelibrary.exception.BusinessException;
import com.gyqstd.cloudimagelibrary.exception.ErrorCode;
import com.gyqstd.cloudimagelibrary.exception.ThrowUtils;
import com.gyqstd.cloudimagelibrary.model.entity.User;
import com.gyqstd.cloudimagelibrary.model.enums.UserRoleEnum;
import com.gyqstd.cloudimagelibrary.model.vo.user.LoginUserVO;
import com.gyqstd.cloudimagelibrary.service.UserService;
import com.gyqstd.cloudimagelibrary.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import static com.gyqstd.cloudimagelibrary.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author guyuqi
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-12-21 10:40:37
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    /**
     * 用户注册
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return
     */
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验
        ThrowUtils.throwIf(StrUtil.hasBlank(userAccount, userPassword, checkPassword), ErrorCode.PARAMS_ERROR, "参数为空");
        ThrowUtils.throwIf(userAccount.length() < 4, ErrorCode.PARAMS_ERROR, "用户账号过短");
        ThrowUtils.throwIf(userPassword.length() < 8 || checkPassword.length() < 8, ErrorCode.PARAMS_ERROR, "用户密码过短");
        ThrowUtils.throwIf(!userPassword.equals(checkPassword), ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        // 2. 检查是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = this.baseMapper.selectCount(queryWrapper);
        ThrowUtils.throwIf(count > 0, ErrorCode.PARAMS_ERROR, "账号重复");
        // 3. 加密
        String encryptPassword = getEncryptPassword(userPassword);
        // 4. 插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setUserName("无名");
        user.setUserRole(UserRoleEnum.USER.getValue());
        boolean saveResult = this.save(user);
        ThrowUtils.throwIf(!saveResult, ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
        return user.getId();
    }

    /**
     * 加密
     * @param userPassword 用户密码
     * @return
     */
    @Override
    public String getEncryptPassword(String userPassword) {
        // 盐值，混淆密码
        final String SALT = "ThisIsAVeryGoodProjectThisIsACloudImageLibraryOHHHHHHHHH";
        return DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    }


    /**
     * 用户登录
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return
     */
    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        ThrowUtils.throwIf(StrUtil.hasBlank(userAccount, userPassword), ErrorCode.PARAMS_ERROR, "参数为空");
        ThrowUtils.throwIf(userAccount.length() < 4, ErrorCode.PARAMS_ERROR, "账号错误");
        ThrowUtils.throwIf(userPassword.length() < 8, ErrorCode.PARAMS_ERROR, "密码错误");
        // 2. 加密
        String encryptPassword = getEncryptPassword(userPassword);
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 3. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return this.getLoginUserVO(user);
    }

    /**
     * 获得脱敏后的登录用户信息
     * @param user
     * @return
     */
    @Override
    public LoginUserVO getLoginUserVO(User user) {
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtil.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        ThrowUtils.throwIf(currentUser == null || currentUser.getId() == null, ErrorCode.NOT_LOGIN_ERROR);
        // 从数据库查询（追求性能的话可以注释，直接返回上述结果）
        long userId = currentUser.getId();
        currentUser = this.getById(userId);
        ThrowUtils.throwIf(currentUser == null, ErrorCode.NOT_LOGIN_ERROR);
        return currentUser;
    }


}




