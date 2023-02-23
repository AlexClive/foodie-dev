package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ShopCatVO;
import com.imooc.utils.PagedGridResult;

import java.util.List;

public interface ItemService {

    /**
     * 根据商品id查询
     * @param itemId 商品id
     * @return 商品数据
     */
     Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param itemId 商品id
     * @return 商品图片
     */
     List<ItemsImg> queryitemImgList(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId id查询
     * @return 商品规格
     */
     List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     * @param itemId 商品id
     * @return 商品参数
     */
     ItemsParam queryItemParam(String itemId);


    /**
     * 根据商品id查询商品的评价等级数量
     * @param ItemId 商品id
     */
     CommentLevelCountsVO queryComments(String ItemId);

    /**
     * 根据商品id查询商品的评价(分页)
     * @param itemId 商品id
     * @param level 评价
     * @param page 第几页
     * @param pageSize 多少列
     * @return 商品评价list
     */
    PagedGridResult queryPageComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * 搜索商品列表
     * @param keyWords 商品关键词
     * @param sort 排序
     * @param page 第几页
     * @param pageSize 多少列
     * @return 商品数据
     */
     PagedGridResult searchItems(String keyWords, String sort, Integer page, Integer pageSize);
     PagedGridResult searchItemsByThirdCat(String catId, String sort, Integer page, Integer pageSize);

    /**
     * 根据ids查询购物车中的商品数据
     * @param specIds 规格id
     * @return 商品数据
     */
     List<ShopCatVO> queryItemsBySpecIds(String specIds);

    /**
     * 根据商品规格id获取规格对象的具体信息
     * @param specId 规格id
     * @return 商品数据
     */
    ItemsSpec queryItemSpecById(String specId);

    /**
     * 根据商品规格id获取商品的主图
     * @param itemId 规格id
     * @return 商品的主图
     */
    String queryItemMainImgById(String itemId);

    /**
     * 规格表中扣除库存
     * @param specId 规格id
     * @param buyCount 去除的数量
     */

    void decreaseItemSpecStock(String specId, Integer buyCount);
}
