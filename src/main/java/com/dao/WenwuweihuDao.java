package com.dao;

import com.entity.WenwuweihuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WenwuweihuVO;
import com.entity.view.WenwuweihuView;


/**
 * 文物维护
 * 
 * @author 
 * @email 
 * @date 2023-03-22 12:04:21
 */
public interface WenwuweihuDao extends BaseMapper<WenwuweihuEntity> {
	
	List<WenwuweihuVO> selectListVO(@Param("ew") Wrapper<WenwuweihuEntity> wrapper);
	
	WenwuweihuVO selectVO(@Param("ew") Wrapper<WenwuweihuEntity> wrapper);
	
	List<WenwuweihuView> selectListView(@Param("ew") Wrapper<WenwuweihuEntity> wrapper);

	List<WenwuweihuView> selectListView(Pagination page,@Param("ew") Wrapper<WenwuweihuEntity> wrapper);
	
	WenwuweihuView selectView(@Param("ew") Wrapper<WenwuweihuEntity> wrapper);
	

}
