package com.dao;

import com.entity.WenwuzhanshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WenwuzhanshiVO;
import com.entity.view.WenwuzhanshiView;


/**
 * 文物展示
 * 
 * @author 
 * @email 
 * @date 2023-03-22 12:04:21
 */
public interface WenwuzhanshiDao extends BaseMapper<WenwuzhanshiEntity> {
	
	List<WenwuzhanshiVO> selectListVO(@Param("ew") Wrapper<WenwuzhanshiEntity> wrapper);
	
	WenwuzhanshiVO selectVO(@Param("ew") Wrapper<WenwuzhanshiEntity> wrapper);
	
	List<WenwuzhanshiView> selectListView(@Param("ew") Wrapper<WenwuzhanshiEntity> wrapper);

	List<WenwuzhanshiView> selectListView(Pagination page,@Param("ew") Wrapper<WenwuzhanshiEntity> wrapper);
	
	WenwuzhanshiView selectView(@Param("ew") Wrapper<WenwuzhanshiEntity> wrapper);
	

}
