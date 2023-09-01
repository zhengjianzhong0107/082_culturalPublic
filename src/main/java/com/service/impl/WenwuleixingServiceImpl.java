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


import com.dao.WenwuleixingDao;
import com.entity.WenwuleixingEntity;
import com.service.WenwuleixingService;
import com.entity.vo.WenwuleixingVO;
import com.entity.view.WenwuleixingView;

@Service("wenwuleixingService")
public class WenwuleixingServiceImpl extends ServiceImpl<WenwuleixingDao, WenwuleixingEntity> implements WenwuleixingService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WenwuleixingEntity> page = this.selectPage(
                new Query<WenwuleixingEntity>(params).getPage(),
                new EntityWrapper<WenwuleixingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<WenwuleixingEntity> wrapper) {
		  Page<WenwuleixingView> page =new Query<WenwuleixingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WenwuleixingVO> selectListVO(Wrapper<WenwuleixingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WenwuleixingVO selectVO(Wrapper<WenwuleixingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<WenwuleixingView> selectListView(Wrapper<WenwuleixingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public WenwuleixingView selectView(Wrapper<WenwuleixingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
