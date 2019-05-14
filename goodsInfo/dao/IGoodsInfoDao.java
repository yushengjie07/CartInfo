package com.qhit.goodsInfo.dao;

import com.qhit.goodsInfo.pojo.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
* Created by GeneratorCode on 2018/05/15
*/

@Mapper  
public interface IGoodsInfoDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<GoodsInfo> search(GoodsInfo goodsInfo);

    List findByGname(Object gname);

    List findByMarketprice(Object marketprice);

    List findByShopprice(Object shopprice);

    List findByImage(Object image);

    List findByGdesc(Object gdesc);

    List findByGdate(Object gdate);

    List findByCid(Object cid);

    List findByStocknumber(Object stocknumber);

    List findBySalenumber(Object salenumber);

    List findByShopid(Object shopid);

}
