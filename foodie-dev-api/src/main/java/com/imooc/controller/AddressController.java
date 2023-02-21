package com.imooc.controller;

import com.imooc.pojo.UserAddress;
import com.imooc.service.UserAddressService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Api(value = "收获地址Controller", tags = {"收获地址接口相关的api"})
@RequestMapping("address")
@RestController
public class AddressController {
    @Autowired
    UserAddressService userAddressService;

    @ApiOperation(value = "根据用户id查询收获地址", notes = "根据用户id查询收获地址", httpMethod = "POST")
    @PostMapping("list")
    public IMOOCJSONResult list(
            @ApiParam(name = "userId", value = "用户id", readOnly = false)
            @RequestParam String userId
    ) {
        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("用户id不能为空");
        }
        List<UserAddress> list = userAddressService.queryAddressList(userId);
        return IMOOCJSONResult.ok(list);
    }

    @ApiOperation(value = "添加用户收获地址", notes = "添加用户收获地址", httpMethod = "POST")
    @PostMapping("add")
    public IMOOCJSONResult add(
            @ApiParam(name = "userId", value = "用户id", readOnly = false)
            @RequestParam String userId,
            @ApiParam(name = "receiver", value = "用户名", readOnly = false)
            @RequestParam String receiver,
            @ApiParam(name = "province", value = "省", readOnly = false)
            @RequestParam String province,
            @ApiParam(name = "city", value = "市", readOnly = false)
            @RequestParam String city,
            @ApiParam(name = "district", value = "区", readOnly = false)
            @RequestParam String district,
            @ApiParam(name = "mobile", value = "电话", readOnly = false)
            @RequestParam String mobile,
            @ApiParam(name = "detail", value = "详细地址", readOnly = false)
            @RequestParam String detail
    ) {
        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("用户id不能为空");
        }
        UserAddress userAddress = new UserAddress();
        Sid sid = new Sid();
        userAddress.setId(sid.nextShort());
        userAddress.setUserId(userId);
        userAddress.setReceiver(receiver);
        userAddress.setProvince(province);
        userAddress.setCity(city);
        userAddress.setDistrict(district);
        userAddress.setMobile(mobile);
        userAddress.setDetail(detail);
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        UserAddress address = userAddressService.createAddress(userAddress);
        return IMOOCJSONResult.ok(address);
    }

    @ApiOperation(value = "设置默认地址", notes = "设置默认地址", httpMethod = "POST")
    @PostMapping("setDefalut")
    public IMOOCJSONResult setDefalut(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "addressId", value = "收获地址id", required = true)
            @RequestParam String addressId
    ) {
        userAddressService.editorAddress(userId, addressId);
        return IMOOCJSONResult.ok();
    }
}
