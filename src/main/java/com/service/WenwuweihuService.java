package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.WenwuweihuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WenwuweihuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.WenwuweihuView;


/**
 * 文物维护
 *
 * @author 
 * @email 
 * @date 2023-03-22 12:04:21
 */
public interface WenwuweihuService extends IService<WenwuweihuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WenwuweihuVO> selectListVO(Wrapper<WenwuweihuEntity> wrapper);
   	
   	WenwuweihuVO selectVO(@Param("ew") Wrapper<WenwuweihuEntity> wrapper);
   	
   	List<WenwuweihuView> selectListView(Wrapper<WenwuweihuEntity> wrapper);
   	
   	WenwuweihuView selectView(@Param("ew") Wrapper<WenwuweihuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WenwuweihuEntity> wrapper);
   	

}

