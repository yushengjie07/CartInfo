package com.qhit.goodsInfo.service;

import com.qhit.goodsInfo.pojo.GoodsInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
* Created by GeneratorCode on 2019/05/08
*/
public interface IGoodsInfoService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    GoodsInfo findById(Object id);

    List<GoodsInfo> search(GoodsInfo goodsInfo);

    boolean cartAdd(GoodsInfo goodsInfo, HttpSession session);

    boolean Delcart(Integer shopid, HttpSession session);
}