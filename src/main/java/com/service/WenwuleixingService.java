package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.WenwuleixingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WenwuleixingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.WenwuleixingView;


/**
 * 文物类型
 *
 * @author 
 * @email 
 * @date 2023-03-22 12:04:21
 */
public interface WenwuleixingService extends IService<WenwuleixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WenwuleixingVO> selectListVO(Wrapper<WenwuleixingEntity> wrapper);
   	
   	WenwuleixingVO selectVO(@Param("ew") Wrapper<WenwuleixingEntity> wrapper);
   	
   	List<WenwuleixingView> selectListView(Wrapper<WenwuleixingEntity> wrapper);
   	
   	WenwuleixingView selectView(@Param("ew") Wrapper<WenwuleixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WenwuleixingEntity> wrapper);
   	

}

