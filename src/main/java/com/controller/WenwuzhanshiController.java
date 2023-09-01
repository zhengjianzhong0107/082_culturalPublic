package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.WenwuzhanshiEntity;
import com.entity.view.WenwuzhanshiView;

import com.service.WenwuzhanshiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;

/**
 * 文物展示
 * 后端接口
 * @author 
 * @email 
 * @date 2023-03-22 12:04:21
 */
@RestController
@RequestMapping("/wenwuzhanshi")
public class WenwuzhanshiController {
    @Autowired
    private WenwuzhanshiService wenwuzhanshiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,WenwuzhanshiEntity wenwuzhanshi, 
		HttpServletRequest request){

        EntityWrapper<WenwuzhanshiEntity> ew = new EntityWrapper<WenwuzhanshiEntity>();


		PageUtils page = wenwuzhanshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wenwuzhanshi), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,WenwuzhanshiEntity wenwuzhanshi, 
		HttpServletRequest request){
        EntityWrapper<WenwuzhanshiEntity> ew = new EntityWrapper<WenwuzhanshiEntity>();

		PageUtils page = wenwuzhanshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wenwuzhanshi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( WenwuzhanshiEntity wenwuzhanshi){
       	EntityWrapper<WenwuzhanshiEntity> ew = new EntityWrapper<WenwuzhanshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( wenwuzhanshi, "wenwuzhanshi")); 
        return R.ok().put("data", wenwuzhanshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(WenwuzhanshiEntity wenwuzhanshi){
        EntityWrapper< WenwuzhanshiEntity> ew = new EntityWrapper< WenwuzhanshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( wenwuzhanshi, "wenwuzhanshi")); 
		WenwuzhanshiView wenwuzhanshiView =  wenwuzhanshiService.selectView(ew);
		return R.ok("查询文物展示成功").put("data", wenwuzhanshiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WenwuzhanshiEntity wenwuzhanshi = wenwuzhanshiService.selectById(id);
        return R.ok().put("data", wenwuzhanshi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        WenwuzhanshiEntity wenwuzhanshi = wenwuzhanshiService.selectById(id);
        return R.ok().put("data", wenwuzhanshi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WenwuzhanshiEntity wenwuzhanshi, HttpServletRequest request){
    	wenwuzhanshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(wenwuzhanshi);

        wenwuzhanshiService.insert(wenwuzhanshi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody WenwuzhanshiEntity wenwuzhanshi, HttpServletRequest request){
    	wenwuzhanshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(wenwuzhanshi);

        wenwuzhanshiService.insert(wenwuzhanshi);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody WenwuzhanshiEntity wenwuzhanshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wenwuzhanshi);
        wenwuzhanshiService.updateById(wenwuzhanshi);//全部更新
        return R.ok();
    }
    
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        wenwuzhanshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<WenwuzhanshiEntity> wrapper = new EntityWrapper<WenwuzhanshiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = wenwuzhanshiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	









}
