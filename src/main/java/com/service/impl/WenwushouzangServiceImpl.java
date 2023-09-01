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


import com.dao.WenwushouzangDao;
import com.entity.WenwushouzangEntity;
import com.service.WenwushouzangService;
import com.entity.vo.WenwushouzangVO;
import com.entity.view.WenwushouzangView;

@Service("wenwushouzangService")
public class WenwushouzangServiceImpl extends ServiceImpl<WenwushouzangDao, WenwushouzangEntity> implements WenwushouzangService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WenwushouzangEntity> page = this.selectPage(
                new Query<WenwushouzangEntity>(params).getPage(),
                new EntityWrapper<WenwushouzangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<WenwushouzangEntity> wrapper) {
		  Page<WenwushouzangView> page =new Query<WenwushouzangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WenwushouzangVO> selectListVO(Wrapper<WenwushouzangEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WenwushouzangVO selectVO(Wrapper<WenwushouzangEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<WenwushouzangView> selectListView(Wrapper<WenwushouzangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public WenwushouzangView selectView(Wrapper<WenwushouzangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
