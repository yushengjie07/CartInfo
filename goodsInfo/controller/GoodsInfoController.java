package com.qhit.goodsInfo.controller;

import com.qhit.goodsInfo.pojo.GoodsInfo;
import com.qhit.goodsInfo.service.IGoodsInfoService;
import com.qhit.utils.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/** 
* Created by GeneratorCode on 2018/05/15
*/ 

@RestController 
@RequestMapping("/goodsInfo") 
public class GoodsInfoController { 

    @Resource 
    IGoodsInfoService goodsInfoService;
    @Autowired
    Upload upload;

    @RequestMapping("/insert") 
    public void insert(GoodsInfo goodsInfo) { 
        goodsInfoService.insert(goodsInfo); 
    } 

    @RequestMapping("/delete") 
    public void delete(Integer gid) { 
        goodsInfoService.delete(gid); 
    } 

    @RequestMapping("/update") 
    public void update(GoodsInfo goodsInfo) { 
        goodsInfoService.update(goodsInfo); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(GoodsInfo goodsInfo) { 
        goodsInfoService.updateSelective(goodsInfo); 
    } 

    @RequestMapping("/load") 
    public GoodsInfo load(Integer gid) { 
        GoodsInfo goodsInfo = goodsInfoService.findById(gid); 
        return goodsInfo; 
    } 

    @RequestMapping("/list") 
    public List<GoodsInfo> list()  { 
        List<GoodsInfo> list = goodsInfoService.findAll(); 
        return list; 
    } 

    @RequestMapping("/search") 
    public List<GoodsInfo> search(GoodsInfo goodsInfo) { 
        List<GoodsInfo> list = goodsInfoService.search(goodsInfo); 
        return list; 
    }
    //返回一个文件路径
    @RequestMapping("/imgUrl")
    public String  imgUrl(HttpServletResponse response, HttpServletRequest request, GoodsInfo goodsInfo) throws Exception {
        String s = upload.handleFileUpload(request, response);
        String[] split = s.split(",");
        for (int i = 0; i < split.length; i++) {
            goodsInfo.setImage(split[i+1]);
            goodsInfoService.updateSelective(goodsInfo);
        }

        return split[0];

    }
    @RequestMapping("cartAdd")
    public boolean cartAdd(GoodsInfo goodsInfo, HttpSession session){
     boolean flag=   goodsInfoService.cartAdd(goodsInfo,session);
     if(flag){
         return true;
     }
     return false;

    }
    @RequestMapping("Delcart")
    public boolean Delcart(Integer shopid, HttpSession session){
        boolean flag=   goodsInfoService.Delcart(shopid,session);
        if(flag){
            return true;
        }
        return false;

    }

} 
