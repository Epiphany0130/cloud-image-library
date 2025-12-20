package com.gyqstd.cloudimagelibrary.controller;

import com.gyqstd.cloudimagelibrary.common.BaseResponse;
import com.gyqstd.cloudimagelibrary.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GuYuqi
 * @version 1.0
 */
@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("/health")
    public BaseResponse<String> health() {
        return ResultUtils.success("ok");
    }
}
