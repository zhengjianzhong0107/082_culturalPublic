package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.WenwufengcunEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WenwufengcunVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.WenwufengcunView;


/**
 * 文物封存
 *
 * @author 
 * @email 
 * @date 2023-03-22 12:04:21
 */
public interface WenwufengcunService extends IService<WenwufengcunEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WenwufengcunVO> selectListVO(Wrapper<WenwufengcunEntity> wrapper);
   	
   	WenwufengcunVO selectVO(@Param("ew") Wrapper<WenwufengcunEntity> wrapper);
   	
   	List<WenwufengcunView> selectListView(Wrapper<WenwufengcunEntity> wrapper);
   	
   	WenwufengcunView selectView(@Param("ew") Wrapper<WenwufengcunEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WenwufengcunEntity> wrapper);
   	

}

