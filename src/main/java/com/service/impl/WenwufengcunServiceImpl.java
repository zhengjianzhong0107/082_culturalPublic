package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.WenwufengcunDao;
import com.entity.WenwufengcunEntity;
import com.service.WenwufengcunService;
import com.entity.vo.WenwufengcunVO;
import com.entity.view.WenwufengcunView;

@Service("wenwufengcunService")
public class WenwufengcunServiceImpl extends ServiceImpl<WenwufengcunDao, WenwufengcunEntity> implements WenwufengcunService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WenwufengcunEntity> page = this.selectPage(
                new Query<WenwufengcunEntity>(params).getPage(),
                new EntityWrapper<WenwufengcunEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<WenwufengcunEntity> wrapper) {
		  Page<WenwufengcunView> page =new Query<WenwufengcunView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WenwufengcunVO> selectListVO(Wrapper<WenwufengcunEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WenwufengcunVO selectVO(Wrapper<WenwufengcunEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<WenwufengcunView> selectListView(Wrapper<WenwufengcunEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public WenwufengcunView selectView(Wrapper<WenwufengcunEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
