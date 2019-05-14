package com.qhit.goodsInfo.pojo;


/** 
* Created by GeneratorCode on 2018/05/15
*/ 

public class GoodsInfo { 
    private Integer gid;    //商品id 
    private String gname;    //名称 
    private Double marketprice;    //市场价格 
    private Double shopprice;    //商品价格 
    private String image;    //图片路径 
    private String gdesc;    //说明 
    private String gdate;    //日期 
    private Integer cid;    //分类id 
    private Integer stocknumber;    //库存数量 
    private Integer salenumber;    //销量 
    private Integer shopid;    //店家id
    private Integer count;  //商品数量

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) { 
        this.gid = gid;
    } 

    public String getGname() { 
        return gname;
    }

    public void setGname(String gname) { 
        this.gname = gname;
    }
    public Double getMarketprice() { 
        return marketprice;
    }

    public void setMarketprice(Double marketprice) { 
        this.marketprice = marketprice;
    } 

    public Double getShopprice() { 
        return shopprice;
    }

    public void setShopprice(Double shopprice) { 
        this.shopprice = shopprice;
    } 

    public String getImage() { 
        return image;
    }

    public void setImage(String image) { 
        this.image = image;
    }
    public String getGdesc() { 
        return gdesc;
    }

    public void setGdesc(String gdesc) { 
        this.gdesc = gdesc;
    }
    public String getGdate() { 
        return gdate;
    }

    public void setGdate(String gdate) { 
        this.gdate = gdate;
    }
    public Integer getCid() { 
        return cid;
    }

    public void setCid(Integer cid) { 
        this.cid = cid;
    } 

    public Integer getStocknumber() { 
        return stocknumber;
    }

    public void setStocknumber(Integer stocknumber) { 
        this.stocknumber = stocknumber;
    } 

    public Integer getSalenumber() { 
        return salenumber;
    }

    public void setSalenumber(Integer salenumber) { 
        this.salenumber = salenumber;
    } 

    public Integer getShopid() { 
        return shopid;
    }

    public void setShopid(Integer shopid) { 
        this.shopid = shopid;
    } 


 }
