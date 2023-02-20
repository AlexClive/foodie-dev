package com.imooc.controller;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "商品接口", tags = {"商品信息相关接口"})
@RestController
@RequestMapping("items")
public class ItemController extends BaseController {

    @Autowired
    public ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public IMOOCJSONResult info(
            @ApiParam(name = "itemId", value = "商品id", readOnly = true)
            @PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg("商品id不能为空");
        }

        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgsList = itemService.queryitemImgList(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        ItemInfoVO itemInfoVo = new ItemInfoVO();
        itemInfoVo.setItems(items);
        itemInfoVo.setItemsImgList(itemsImgsList);
        itemInfoVo.setItemsSpecList(itemsSpecList);
        itemInfoVo.setItemsParam(itemsParam);
        return IMOOCJSONResult.ok(itemInfoVo);
    }


    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public IMOOCJSONResult commentLevel(
            @ApiParam(name = "itemId", value = "商品id", readOnly = true)
            @RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg("商品id不能为空");
        }

        CommentLevelCountsVO countsVO = itemService.queryComments(itemId);


        return IMOOCJSONResult.ok(countsVO);
    }

    @ApiOperation(value = "查询商品评价", notes = "查询商品评价", httpMethod = "GET")
    @GetMapping("/comments")
    public IMOOCJSONResult comments(
            @ApiParam(name = "itemId", value = "商品id", readOnly = true)
            @RequestParam String itemId,
            @ApiParam(name = "level", value = "商品评价等级", readOnly = false)
            @RequestParam Integer level,
            @ApiParam(name = "page", value = "第几页", readOnly = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "多少列", readOnly = false)
            @RequestParam Integer pageSize
    ) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg("商品id不能为空");
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        PagedGridResult grid = itemService.queryPageComments(itemId, level, page, pageSize);


        return IMOOCJSONResult.ok(grid);
    }

    @ApiOperation(value = "商品搜索", notes = "商品搜索", httpMethod = "POST")
    @PostMapping("/search")
    public IMOOCJSONResult search(
            @ApiParam(name = "keyWords", value = "关键词", readOnly = true)
            @RequestParam String keyWords,
            @ApiParam(name = "sort", value = "排序", readOnly = false)
            @RequestParam String sort,
            @ApiParam(name = "page", value = "第几页", readOnly = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "多少列", readOnly = false)
            @RequestParam Integer pageSize
    ) {
        if (StringUtils.isBlank(keyWords)) {
            return IMOOCJSONResult.errorMsg("商品名称不能为空");
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        PagedGridResult grid = itemService.searchItems(keyWords, sort, page, pageSize);


        return IMOOCJSONResult.ok(grid);
    }

}
