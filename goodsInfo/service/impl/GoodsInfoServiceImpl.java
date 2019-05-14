package com.qhit.goodsInfo.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qhit.goodsInfo.dao.IGoodsInfoDao;
import com.qhit.goodsInfo.pojo.GoodsInfo;
import com.qhit.goodsInfo.service.IGoodsInfoService;
import com.qhit.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
* Created by GeneratorCode on 2018/05/15
*/

@Service 
public class GoodsInfoServiceImpl  implements IGoodsInfoService {

    @Resource
    IGoodsInfoDao dao;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    Gson gson;

    @Override 
    public boolean insert(Object object) { 
        return dao.insert(object); 
    } 

    @Override 
    public boolean update(Object object) { 
        return dao.update(object); 
    } 

    @Override 
    public boolean updateSelective(Object object) { 
        return dao.updateSelective(object); 
    } 

    @Override 
    public boolean delete(Object id) { 
        GoodsInfo goodsInfo = findById(id);
        return dao.delete(goodsInfo); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public GoodsInfo findById(Object id) {
        List<GoodsInfo> list = dao.findById(id);
        return  list.get(0); 
    } 

    @Override 
    public List<GoodsInfo> search(GoodsInfo goodsInfo) {
        return dao.search(goodsInfo); 
    }

    @Override
    public boolean cartAdd(GoodsInfo goodsInfo, HttpSession session) {
        //新建一个购物车容器
        Map<Integer, GoodsInfo> map=null;
        //从session中取userid
        Integer userid = (Integer) session.getAttribute("baseUser");
        //判断这个用户是否已经拥有购物车
        boolean flag = redisUtils.hasKey(userid.toString());
        if(!flag){
            //如果没有购物车，则新建一个购物车
            map=new Hashtable<>();
            //key商品id  vlue商品信息
            map.put(goodsInfo.getGid(),goodsInfo);
            //把map转成json
            String s = gson.toJson(map);
            //存到redis里key是用户id  value商品信息转成的json数据
            redisUtils.set(String.valueOf(userid),s);
        }else {
            //如果用户在Redis里有购物车
            //通过用户id查找json信息
            Object cartJson = redisUtils.get(String.valueOf(userid));
            //把查找的json信息转成我们需要的map格式
            Object cartInfo=gson.fromJson(cartJson.toString(),new TypeToken<Map<Integer,GoodsInfo>>(){}.getType());
            map=(Map<Integer,GoodsInfo>)cartInfo;
            for(Map.Entry<Integer,GoodsInfo>goodsInfoEntry:map.entrySet()){
                //判断id是否一致
                if(goodsInfoEntry.getKey().equals(goodsInfo.getGid())){
                    //如果id一致更改购物车的数量
                    goodsInfoEntry.getValue().setCount(goodsInfo.getCount());
                    return true;
                }
            }
            //如果id不一样就添加到对应的购物车里
            map.put(goodsInfo.getGid(),goodsInfo);
            String toJson = gson.toJson(map);
            //修改Redis信息
            redisUtils.set(userid.toString(),toJson);
        }

        return true;
    }

    @Override
    public boolean Delcart(Integer shopid, HttpSession session) {
        //查询登录人的userid
        Integer userid = (Integer) session.getAttribute("baseUser");
        //获取登录人的购物车信息
        Object usercartinfo = redisUtils.get(userid.toString());
        //把购物车的信息转成json
        Object cartinfo = gson.fromJson(usercartinfo.toString(), new TypeToken<Map<Integer, GoodsInfo>>(){}.getType());
        //json 有可能为空  判断下
        if(cartinfo!=null){
            //定义一个map用来当做存放数据的容器
            Map<Integer,GoodsInfo>map;
            //把json数据存放到map里
            map=(Map<Integer,GoodsInfo>)cartinfo;
            //如果这个商品id不为空
            if(map.get(shopid)!=null){
                //删除
                map.remove(shopid);
                //删除后添加到redis中
                String s = gson.toJson(map);
                redisUtils.set(userid.toString(),s);
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

}
