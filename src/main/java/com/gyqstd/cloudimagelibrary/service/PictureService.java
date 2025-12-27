package com.gyqstd.cloudimagelibrary.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyqstd.cloudimagelibrary.model.dto.picture.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gyqstd.cloudimagelibrary.model.dto.picture.PictureQueryRequest;
import com.gyqstd.cloudimagelibrary.model.dto.picture.PictureUploadRequest;
import com.gyqstd.cloudimagelibrary.model.entity.User;
import com.gyqstd.cloudimagelibrary.model.vo.picture.PictureVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

/**
* @author GuYuqi
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-12-25 18:21:47
*/
public interface PictureService extends IService<Picture> {
    /**
     * 上传图片
     *
     * @param multipartFile
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(MultipartFile multipartFile,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

    /**
     * 构建图片查询条件（将查询请求转为 QueryWrapper 对象）
     * @param pictureQueryRequest
     * @return
     */
    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

    /**
     * 获取单个图片封装
     * @param picture
     * @param request
     * @return
     */
    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    /**
     * 分页获取图片封装
     */
    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    /**
     * 图片数据校验
     * @param picture
     */
    void validPicture(Picture picture);
}
