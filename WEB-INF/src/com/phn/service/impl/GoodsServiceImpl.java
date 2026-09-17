package com.phn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phn.dao.AreaDao;
import com.phn.dao.GoodsDao;
import com.phn.dao.PageDao;
import com.phn.dao.TypeDao;
import com.phn.dao.UserDao;
import com.phn.entity.Goods;
import com.phn.entity.Pages;
import com.phn.entity.User;

@Service("goodsService")
public class GoodsServiceImpl implements com.phn.service.GoodsService {

    @Resource
    private PageDao pageDao;
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private UserDao userDao;
    @Resource
    private TypeDao typeDao;
    @Resource
    private AreaDao areaDao;

    public Pages GetAllLostForPage(int nowPage, int pageSize) {
        String sql = "from Goods goods where goods.goodsstatus = 1 order by goods.id desc";
        int allRecords = pageDao.getAllRowCount(sql);
        int totalPage = pageDao.calculateTotalPage(pageSize, allRecords);
        int currentoffset = pageDao.currentPage_startRecord(nowPage, pageSize);
        int length = pageSize;
        int currentPage = pageDao.judgeCurrentPage(totalPage, nowPage);
        List<Goods> listGoods = pageDao.query_Objects_ForPages(sql, currentoffset, length);
        Pages pagebean = new Pages();
        pagebean.setPageSize(pageSize);
        pagebean.setAllRecords(allRecords);
        pagebean.setCurrentPage(currentPage);
        pagebean.setTotalPages(totalPage);
        pagebean.setListGoods(listGoods);
        return pagebean;
    }

    public Pages GetAllFoundForPage(int nowPage, int pageSize) {
        String sql = "from Goods goods where goods.goodsstatus = 2 order by goods.id desc";
        int allRecords = pageDao.getAllRowCount(sql);
        int totalPage = pageDao.calculateTotalPage(pageSize, allRecords);
        int currentoffset = pageDao.currentPage_startRecord(nowPage, pageSize);
        int length = pageSize;
        int currentPage = pageDao.judgeCurrentPage(totalPage, nowPage);
        List<Goods> listGoods = pageDao.query_Objects_ForPages(sql, currentoffset, length);
        Pages pagebean = new Pages();
        pagebean.setPageSize(pageSize);
        pagebean.setAllRecords(allRecords);
        pagebean.setCurrentPage(currentPage);
        pagebean.setTotalPages(totalPage);
        pagebean.setListGoods(listGoods);
        return pagebean;
    }

    public void newInfoUpdateUser(User us) {
        userDao.update(us);
    }

    public void newInfo(Goods goods) {
        // 如果是寻物启事(goodsstatus=1)，设置reward_points=1
        if (goods.getGoodsstatus() == 1) {
            goods.setReward_points(1);
        }
        goodsDao.insert(goods);
    }

    public Goods find(int index) {
        return goodsDao.find(index);
    }

    public List getAllType() {
        return typeDao.getAll();
    }

    public List getAllArea() {
        return areaDao.getAll();
    }

    public List<Goods> getLast() {
        return goodsDao.getLast();
    }

    public boolean delete(int index) {
        return goodsDao.delete(index);
    }

    public List<Goods> GetAllSearTxt(String searchTxt) {
        return goodsDao.getSearchTxt(searchTxt);
    }
}
