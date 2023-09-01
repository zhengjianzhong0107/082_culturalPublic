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


import com.dao.WenwuzhanshiDao;
import com.entity.WenwuzhanshiEntity;
import com.service.WenwuzhanshiService;
import com.entity.vo.WenwuzhanshiVO;
import com.entity.view.WenwuzhanshiView;

@Service("wenwuzhanshiService")
public class WenwuzhanshiServiceImpl extends ServiceImpl<WenwuzhanshiDao, WenwuzhanshiEntity> implements WenwuzhanshiService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WenwuzhanshiEntity> page = this.selectPage(
                new Query<WenwuzhanshiEntity>(params).getPage(),
                new EntityWrapper<WenwuzhanshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<WenwuzhanshiEntity> wrapper) {
		  Page<WenwuzhanshiView> page =new Query<WenwuzhanshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WenwuzhanshiVO> selectListVO(Wrapper<WenwuzhanshiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WenwuzhanshiVO selectVO(Wrapper<WenwuzhanshiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<WenwuzhanshiView> selectListView(Wrapper<WenwuzhanshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public WenwuzhanshiView selectView(Wrapper<WenwuzhanshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
