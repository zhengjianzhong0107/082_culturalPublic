package com.dao;

import com.entity.WenwushouzangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WenwushouzangVO;
import com.entity.view.WenwushouzangView;


/**
 * 文物收藏
 * 
 * @author 
 * @email 
 * @date 2023-03-22 12:04:21
 */
public interface WenwushouzangDao extends BaseMapper<WenwushouzangEntity> {
	
	List<WenwushouzangVO> selectListVO(@Param("ew") Wrapper<WenwushouzangEntity> wrapper);
	
	WenwushouzangVO selectVO(@Param("ew") Wrapper<WenwushouzangEntity> wrapper);
	
	List<WenwushouzangView> selectListView(@Param("ew") Wrapper<WenwushouzangEntity> wrapper);

	List<WenwushouzangView> selectListView(Pagination page,@Param("ew") Wrapper<WenwushouzangEntity> wrapper);
	
	WenwushouzangView selectView(@Param("ew") Wrapper<WenwushouzangEntity> wrapper);
	

}
