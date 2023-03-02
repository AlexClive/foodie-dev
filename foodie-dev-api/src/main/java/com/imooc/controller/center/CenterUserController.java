package com.imooc.controller.center;

import com.imooc.controller.BaseController;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;
import com.imooc.resource.FileUpload;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.DateUtil;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "用户信息接口", tags = {"用户信息相关接口"})
@RestController
@RequestMapping("userInfo")
public class CenterUserController extends BaseController {
    @Autowired
    public CenterUserService centerUserService;
    @Autowired
    FileUpload fileUpload;

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @PostMapping("update")
    public IMOOCJSONResult update(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @RequestBody @Valid CenterUserBO centerUserBO,
            BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response) {
        if (result.hasErrors()) {
            Map<String, String> errorMsg = getErrors(result);
            return IMOOCJSONResult.errorMap(errorMsg);
        }
        Users UserResult = setNullProperty(centerUserService.updateUserInfo(userId, centerUserBO));
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(UserResult), true);
        // TODO 后续要改，增加令牌token,整合redis
        return IMOOCJSONResult.ok(UserResult);
    }

    @ApiOperation(value = "用户头像修改", notes = "用户头像修改", httpMethod = "POST")
    @PostMapping("uploadFace")
    public IMOOCJSONResult uploadFace(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "file", value = "用户头像", required = true)
            MultipartFile file,
            HttpServletRequest request,
            HttpServletResponse response) {
        // 定义头像保存的地址
        // String fileSpace = IMAGE_USER_FACE_LOCATION;
        String fileSpace = fileUpload.getImageUserFaceLocation();
        // 在路径上为没有个用户增加一个userid，用于区分不同用户上传
        String uploadPathPrefix = File.separator + userId;
        if (file != null) {
            FileOutputStream fileOutputStream = null;

            try {
                // 获得文件上传的文件名称
                String fileName = file.getOriginalFilename();
                if (!StringUtils.isBlank(fileName)) {
                    // face-{userid}.png   imooc-face.png-> ["imooc-face","png"]
                    // 文件重命名
                    String[] fileNameArr = fileName.split("\\.");
                    // 获取文件的后缀名
                    String suffix = fileNameArr[fileNameArr.length - 1];
                    if (!suffix.equalsIgnoreCase("png") &&
                            !suffix.equalsIgnoreCase("jpg") &&
                            !suffix.equalsIgnoreCase("jpeg")) {
                      return IMOOCJSONResult.errorMsg("图片格式不正确");
                    }
                    // 文件名称重组
                    // 覆盖式上传，增量式 String newFileName = "face-" + userId + "随机字符" +"." + suffix;
                    String newFileName = "face-" + userId + "." + suffix;
                    //上传的头像最终的保存位置

                    String finalFacePath = fileSpace + uploadPathPrefix + File.separator + newFileName;
                    // 用于提供给web服务的访问地址
                    uploadPathPrefix += ("/" + newFileName);
                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null) {
                        // 创建文件夹
                        boolean wasSuccessful = outFile.getParentFile().mkdirs();
                        if (wasSuccessful) {
                            System.out.println("创建文件夹成功.");
                        }
                    }
                    // 文件输出保存到目录
                    fileOutputStream = new FileOutputStream(outFile);
                    InputStream inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return IMOOCJSONResult.errorMsg("文件不能为空");
        }
        String imageServiceUrl = fileUpload.getImageServiceUrl();
        String finalUserFaceUrl = imageServiceUrl + uploadPathPrefix + "?" + DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);
        // 更新用户头像到数据库
        Users userResult = centerUserService.updateUserFace(userId, finalUserFaceUrl);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);
        // TODO 后续要改，增加令牌token,整合redis
        return IMOOCJSONResult.ok();
    }

    public Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> errorList = result.getFieldErrors();
        for (FieldError error : errorList) {
            // 发生验证错误所对应的某一属性
            String errorField = error.getField();
            // 验证错误的信息
            String errorMsg = error.getDefaultMessage();
            map.put(errorField, errorMsg);
        }
        return map;
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }
}
