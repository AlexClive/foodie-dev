package com.imooc.controller;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.pojo.vo.ShopCatVO;
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
        itemInfoVo.setItem(items);
        itemInfoVo.setItemImgList(itemsImgsList);
        itemInfoVo.setItemsSpecList(itemsSpecList);
        itemInfoVo.setItemParams(itemsParam);
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

    @ApiOperation(value = "商品搜索", notes = "商品搜索", httpMethod = "GET")
    @GetMapping("/search")
    public IMOOCJSONResult search(
            @ApiParam(name = "keywords", value = "关键词", readOnly = true)
            @RequestParam String keywords,
            @ApiParam(name = "sort", value = "排序", readOnly = false)
            @RequestParam String sort,
            @ApiParam(name = "page", value = "第几页", readOnly = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "多少列", readOnly = false)
            @RequestParam Integer pageSize
    ) {
        if (StringUtils.isBlank(keywords)) {
            return IMOOCJSONResult.errorMsg("商品名称不能为空");
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        PagedGridResult grid = itemService.searchItems(keywords, sort, page, pageSize);


        return IMOOCJSONResult.ok(grid);
    }

    @ApiOperation(value = "根据分类id查询商品", notes = "根据分类id查询商品", httpMethod = "POST")
    @PostMapping("/catItems")
    public IMOOCJSONResult catItems(
            @ApiParam(name = "catId", value = "分类id", readOnly = true)
            @RequestParam String catId,
            @ApiParam(name = "sort", value = "排序", readOnly = false)
            @RequestParam String sort,
            @ApiParam(name = "page", value = "第几页", readOnly = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "多少列", readOnly = false)
            @RequestParam Integer pageSize
    ) {
        if (StringUtils.isBlank(catId)) {
            return IMOOCJSONResult.errorMsg("商品id不能为空");
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        PagedGridResult grid = itemService.searchItemsByThirdCat(catId, sort, page, pageSize);


        return IMOOCJSONResult.ok(grid);
    }

    /**
     * 用于用户长时间未登录网站，刷新购物车中的数据。
     *
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "根据规格id查询最新的商品数据", notes = "根据规格id查询最新的商品数据", httpMethod = "GET")
    @GetMapping("/refresh")
    public IMOOCJSONResult refresh(
            @ApiParam(name = "itemSpecIds", value = "分类id", readOnly = true)
            @RequestParam String itemSpecIds
    ) {
        if (StringUtils.isBlank(itemSpecIds)) {
            return IMOOCJSONResult.errorMsg("商品规格id不能为空");
        }
        List<ShopCatVO> list = itemService.queryItemsBySpecIds(itemSpecIds);

        return IMOOCJSONResult.ok(list);
    }


}
