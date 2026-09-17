package com.phn.service;

import java.util.List;

import com.phn.entity.Goods;
import com.phn.entity.Pages;
import com.phn.entity.User;

public interface GoodsService {
    public Pages GetAllLostForPage(int nowPage, int pageSize);
    public Pages GetAllFoundForPage(int nowPage, int pageSize);
    public void newInfoUpdateUser(User us);
    public void newInfo(Goods goods);
    public Goods find(int index);
    public List getAllType();
    public List getAllArea();
    public List<Goods> getLast();
    public boolean delete(int index);
    public List<Goods> GetAllSearTxt(String searchTxt);
}
