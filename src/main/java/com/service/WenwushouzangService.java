package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.WenwushouzangEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WenwushouzangVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.WenwushouzangView;


/**
 * 文物收藏
 *
 * @author 
 * @email 
 * @date 2023-03-22 12:04:21
 */
public interface WenwushouzangService extends IService<WenwushouzangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WenwushouzangVO> selectListVO(Wrapper<WenwushouzangEntity> wrapper);
   	
   	WenwushouzangVO selectVO(@Param("ew") Wrapper<WenwushouzangEntity> wrapper);
   	
   	List<WenwushouzangView> selectListView(Wrapper<WenwushouzangEntity> wrapper);
   	
   	WenwushouzangView selectView(@Param("ew") Wrapper<WenwushouzangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WenwushouzangEntity> wrapper);
   	

}

