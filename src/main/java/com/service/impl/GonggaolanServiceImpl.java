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


import com.dao.GonggaolanDao;
import com.entity.GonggaolanEntity;
import com.service.GonggaolanService;
import com.entity.vo.GonggaolanVO;
import com.entity.view.GonggaolanView;

@Service("gonggaolanService")
public class GonggaolanServiceImpl extends ServiceImpl<GonggaolanDao, GonggaolanEntity> implements GonggaolanService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GonggaolanEntity> page = this.selectPage(
                new Query<GonggaolanEntity>(params).getPage(),
                new EntityWrapper<GonggaolanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<GonggaolanEntity> wrapper) {
		  Page<GonggaolanView> page =new Query<GonggaolanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<GonggaolanVO> selectListVO(Wrapper<GonggaolanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public GonggaolanVO selectVO(Wrapper<GonggaolanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<GonggaolanView> selectListView(Wrapper<GonggaolanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public GonggaolanView selectView(Wrapper<GonggaolanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
