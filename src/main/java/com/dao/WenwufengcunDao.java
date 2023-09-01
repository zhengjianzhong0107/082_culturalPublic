package com.dao;

import com.entity.WenwufengcunEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WenwufengcunVO;
import com.entity.view.WenwufengcunView;


/**
 * 文物封存
 * 
 * @author 
 * @email 
 * @date 2023-03-22 12:04:21
 */
public interface WenwufengcunDao extends BaseMapper<WenwufengcunEntity> {
	
	List<WenwufengcunVO> selectListVO(@Param("ew") Wrapper<WenwufengcunEntity> wrapper);
	
	WenwufengcunVO selectVO(@Param("ew") Wrapper<WenwufengcunEntity> wrapper);
	
	List<WenwufengcunView> selectListView(@Param("ew") Wrapper<WenwufengcunEntity> wrapper);

	List<WenwufengcunView> selectListView(Pagination page,@Param("ew") Wrapper<WenwufengcunEntity> wrapper);
	
	WenwufengcunView selectView(@Param("ew") Wrapper<WenwufengcunEntity> wrapper);
	

}
