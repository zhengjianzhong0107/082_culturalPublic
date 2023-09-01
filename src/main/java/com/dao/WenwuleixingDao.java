package com.dao;

import com.entity.WenwuleixingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WenwuleixingVO;
import com.entity.view.WenwuleixingView;


/**
 * 文物类型
 * 
 * @author 
 * @email 
 * @date 2023-03-22 12:04:21
 */
public interface WenwuleixingDao extends BaseMapper<WenwuleixingEntity> {
	
	List<WenwuleixingVO> selectListVO(@Param("ew") Wrapper<WenwuleixingEntity> wrapper);
	
	WenwuleixingVO selectVO(@Param("ew") Wrapper<WenwuleixingEntity> wrapper);
	
	List<WenwuleixingView> selectListView(@Param("ew") Wrapper<WenwuleixingEntity> wrapper);

	List<WenwuleixingView> selectListView(Pagination page,@Param("ew") Wrapper<WenwuleixingEntity> wrapper);
	
	WenwuleixingView selectView(@Param("ew") Wrapper<WenwuleixingEntity> wrapper);
	

}
