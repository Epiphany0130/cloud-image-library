package com.gyqstd.cloudimagelibrary.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyqstd.cloudimagelibrary.annotation.AuthCheck;
import com.gyqstd.cloudimagelibrary.common.BaseResponse;
import com.gyqstd.cloudimagelibrary.common.DeleteRequest;
import com.gyqstd.cloudimagelibrary.common.ResultUtils;
import com.gyqstd.cloudimagelibrary.constant.UserConstant;
import com.gyqstd.cloudimagelibrary.exception.ErrorCode;
import com.gyqstd.cloudimagelibrary.exception.ThrowUtils;
import com.gyqstd.cloudimagelibrary.model.dto.picture.PictureUploadRequest;
import com.gyqstd.cloudimagelibrary.model.dto.user.*;
import com.gyqstd.cloudimagelibrary.model.entity.User;
import com.gyqstd.cloudimagelibrary.model.vo.picture.PictureVO;
import com.gyqstd.cloudimagelibrary.model.vo.user.LoginUserVO;
import com.gyqstd.cloudimagelibrary.model.vo.user.UserVO;
import com.gyqstd.cloudimagelibrary.service.PictureService;
import com.gyqstd.cloudimagelibrary.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private UserService userService;

    @Resource
    private PictureService pictureService;

    /**
     * 上传图片（可重新上传）
     */
    @PostMapping("/upload")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<PictureVO> uploadPicture(
            @RequestPart("file") MultipartFile multipartFile,
            PictureUploadRequest pictureUploadRequest,
            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        PictureVO pictureVO = pictureService.uploadPicture(multipartFile, pictureUploadRequest, loginUser);
        return ResultUtils.success(pictureVO);
    }

}
