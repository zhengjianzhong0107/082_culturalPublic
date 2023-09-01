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


import com.dao.WenwuweihuDao;
import com.entity.WenwuweihuEntity;
import com.service.WenwuweihuService;
import com.entity.vo.WenwuweihuVO;
import com.entity.view.WenwuweihuView;

@Service("wenwuweihuService")
public class WenwuweihuServiceImpl extends ServiceImpl<WenwuweihuDao, WenwuweihuEntity> implements WenwuweihuService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WenwuweihuEntity> page = this.selectPage(
                new Query<WenwuweihuEntity>(params).getPage(),
                new EntityWrapper<WenwuweihuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<WenwuweihuEntity> wrapper) {
		  Page<WenwuweihuView> page =new Query<WenwuweihuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WenwuweihuVO> selectListVO(Wrapper<WenwuweihuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WenwuweihuVO selectVO(Wrapper<WenwuweihuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<WenwuweihuView> selectListView(Wrapper<WenwuweihuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public WenwuweihuView selectView(Wrapper<WenwuweihuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
