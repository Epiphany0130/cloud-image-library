package com.gyqstd.cloudimagelibrary.service;

import com.gyqstd.cloudimagelibrary.model.dto.picture.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gyqstd.cloudimagelibrary.model.dto.picture.PictureUploadRequest;
import com.gyqstd.cloudimagelibrary.model.entity.User;
import com.gyqstd.cloudimagelibrary.model.vo.picture.PictureVO;
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

}
