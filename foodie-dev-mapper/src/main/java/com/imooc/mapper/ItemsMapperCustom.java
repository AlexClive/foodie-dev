package com.imooc.mapper;


import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.SearchItemsVO;
import com.imooc.pojo.vo.ShopCatVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {
    List<ItemCommentVO> queryItemComments(@Param("paramMap") Map<String,Object> map);

    List<SearchItemsVO> searchItems(@Param("paramMap") Map<String,Object> map);
    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramMap") Map<String,Object> map);
    List<ShopCatVO> queryItemsBySpecIds(@Param("paramsList") List<String> specIdsList);
    int decreaseItemSpecStock(@Param("specId") String specId,@Param("pendingCounts") int pendingCounts);
}