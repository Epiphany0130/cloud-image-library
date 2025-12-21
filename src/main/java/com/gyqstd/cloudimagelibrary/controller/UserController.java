package com.gyqstd.cloudimagelibrary.controller;

import com.gyqstd.cloudimagelibrary.common.BaseResponse;
import com.gyqstd.cloudimagelibrary.common.ResultUtils;
import com.gyqstd.cloudimagelibrary.exception.ErrorCode;
import com.gyqstd.cloudimagelibrary.exception.ThrowUtils;
import com.gyqstd.cloudimagelibrary.model.dto.user.UserRegisterRequest;
import com.gyqstd.cloudimagelibrary.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }
}
