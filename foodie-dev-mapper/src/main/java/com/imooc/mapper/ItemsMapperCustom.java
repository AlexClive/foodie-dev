package com.imooc.mapper;


import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.SearchItemsVO;
import com.imooc.pojo.vo.ShopCatVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {
    public List<ItemCommentVO> queryItemComments(@Param("paramMap") Map<String,Object> map);

    public List<SearchItemsVO> searchItems(@Param("paramMap") Map<String,Object> map);
    public List<SearchItemsVO> searchItemsByThirdCat(@Param("paramMap") Map<String,Object> map);
    public List<ShopCatVO> queryItemsBySpecIds(@Param("paramsList") List specIdsList);
    public int decreaseItemSpecStock(@Param("specId") String specId,@Param("pendingCounts") int pendingCounts);
}