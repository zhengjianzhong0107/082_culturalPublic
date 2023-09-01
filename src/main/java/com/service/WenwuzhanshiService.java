package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.WenwuzhanshiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WenwuzhanshiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.WenwuzhanshiView;


/**
 * 文物展示
 *
 * @author 
 * @email 
 * @date 2023-03-22 12:04:21
 */
public interface WenwuzhanshiService extends IService<WenwuzhanshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WenwuzhanshiVO> selectListVO(Wrapper<WenwuzhanshiEntity> wrapper);
   	
   	WenwuzhanshiVO selectVO(@Param("ew") Wrapper<WenwuzhanshiEntity> wrapper);
   	
   	List<WenwuzhanshiView> selectListView(Wrapper<WenwuzhanshiEntity> wrapper);
   	
   	WenwuzhanshiView selectView(@Param("ew") Wrapper<WenwuzhanshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WenwuzhanshiEntity> wrapper);
   	

}

