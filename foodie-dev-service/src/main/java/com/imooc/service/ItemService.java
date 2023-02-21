package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.ShopCatVO;
import com.imooc.utils.PagedGridResult;

import java.util.List;

public interface ItemService {

    /**
     * 根据商品id查询
     * @param id
     * @return
     */
    public Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param id
     * @return
     */
    public List<ItemsImg> queryitemImgList(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemParam(String itemId);


    /**
     * 根据商品id查询商品的评价等级数量
     * @param ItemId
     */
    public CommentLevelCountsVO queryComments(String ItemId);

    /**
     * 根据商品id查询商品的评价(分页)
     * @param ItemId
     * @param level
     * @return
     */
    public PagedGridResult queryPageComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * 搜索商品列表
     * @param keyWords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searchItems(String keyWords, String sort, Integer page, Integer pageSize);
    public PagedGridResult searchItemsByThirdCat(String catId, String sort, Integer page, Integer pageSize);

    /**
     * 根据ids查询购物车中的商品数据
     * @param specIds
     * @return
     */
    public List<ShopCatVO> queryItemsBySpecIds(String specIds);
}
