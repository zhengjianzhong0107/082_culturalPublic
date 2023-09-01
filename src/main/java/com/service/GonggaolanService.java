package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.GonggaolanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.GonggaolanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.GonggaolanView;


/**
 * 公告栏
 *
 * @author 
 * @email 
 * @date 2023-03-22 12:04:21
 */
public interface GonggaolanService extends IService<GonggaolanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<GonggaolanVO> selectListVO(Wrapper<GonggaolanEntity> wrapper);
   	
   	GonggaolanVO selectVO(@Param("ew") Wrapper<GonggaolanEntity> wrapper);
   	
   	List<GonggaolanView> selectListView(Wrapper<GonggaolanEntity> wrapper);
   	
   	GonggaolanView selectView(@Param("ew") Wrapper<GonggaolanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<GonggaolanEntity> wrapper);
   	

}

