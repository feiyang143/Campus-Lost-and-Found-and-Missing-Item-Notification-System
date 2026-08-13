package com.phn.action;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.phn.entity.Goods;
import com.phn.entity.Pages;
import com.phn.entity.User;
import com.phn.service.GoodsService;
import com.phn.util.FileUpload;

@Controller("goodsAction")
@Scope("prototype")
public class GoodsAction {

    private GoodsService goodsService;
    private Goods goods;
    private User user;
    private Pages pageBean;
    private List<Goods> listLostGoods;
    private List<Goods> listFoundGoods;
    private List<Goods> listLastGoods;
    private List listType;
    private List listArea;
    private List listComment;
    private int nowPage;
    private int index;
    private int lostGoodsStatus = 1;
    private int foundGoodsStatus = 2;
    private int goodsStatus;

    private File fileUpload;
    private String fileUploadFileName;
    private String fileUploadContentType;
    private String savePath;

    private DateFormat df = new SimpleDateFormat("yyyyMMdd-hhmmss-");
    private HttpServletRequest request;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pages getPageBean() {
        return pageBean;
    }

    public void setPageBean(Pages pageBean) {
        this.pageBean = pageBean;
    }

    public List<Goods> getListLostGoods() {
        return listLostGoods;
    }

    public void setListLostGoods(List<Goods> listLostGoods) {
        this.listLostGoods = listLostGoods;
    }

    public List<Goods> getListFoundGoods() {
        return listFoundGoods;
    }

    public void setListFoundGoods(List<Goods> listFoundGoods) {
        this.listFoundGoods = listFoundGoods;
    }

    public List<Goods> getListLastGoods() {
        return listLastGoods;
    }

    public void setListLastGoods(List<Goods> listLastGoods) {
        this.listLastGoods = listLastGoods;
    }

    public List getListType() {
        return listType;
    }

    public void setListType(List listType) {
        this.listType = listType;
    }

    public List getListArea() {
        return listArea;
    }

    public void setListArea(List listArea) {
        this.listArea = listArea;
    }

    public List getListComment() {
        return listComment;
    }

    public void setListComment(List listComment) {
        this.listComment = listComment;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getSavePath() {
        return ServletActionContext.getServletContext().getRealPath(savePath);
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public GoodsService getGoodsService() {
        return goodsService;
    }

    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public String NewLostInfo() throws Exception {
        request = ServletActionContext.getRequest();
        User us = (User) request.getSession().getAttribute("sessionUser");
        FileUpload fileUp = new FileUpload();
        String goodsPicName = df.format(new Date()) + fileUploadFileName;
        System.out.println("图片上传的名称为：" + goodsPicName);
        String pic = fileUp.PicUpload(fileUpload, getSavePath(), goodsPicName, fileUploadContentType);

        if (us != null) {
            if (!(us.getUsernickname().equals(user.getUsernickname())) || !(us.getUserphone().equals(user.getUserphone())) || !(us.getUserqq().equals(user.getUserqq()))) {
                us.setUsernickname(user.getUsernickname());
                us.setUserphone(user.getUserphone());
                us.setUserqq(user.getUserqq());
                goodsService.newInfoUpdateUser(us);
                System.out.println("信息：用户基本信息改变");
            } else {
                System.out.println("信息：用户基本信息未变");
            }
        }

        // 设置用户信息
        goods.setGoodsuser(us);
        // 设置图片路径
        goods.setGoodspictures(pic);
        // 设置时间
        goods.setGoodstime(new Date());
        // 为寻物启事设置reward_points=1
        if (goods.getGoodsstatus() == 1) {
            // 直接设置reward_points字段
            try {
                java.lang.reflect.Field field = Goods.class.getDeclaredField("reward_points");
                field.setAccessible(true);
                field.set(goods, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 保存信息
        goodsService.newInfo(goods);
        // 得到最后一条记录
        List<Goods> listGoods = goodsService.getLast();
        if (listGoods.size() > 0) {
            index = listGoods.get(0).getId();
        }

        return "goods_newLost_success";
    }

    public String NewFoundInfo() throws Exception {
        request = ServletActionContext.getRequest();
        User us = (User) request.getSession().getAttribute("sessionUser");
        FileUpload fileUp = new FileUpload();
        String goodsPicName = df.format(new Date()) + fileUploadFileName;
        System.out.println("图片上传的名称为：" + goodsPicName);
        String pic = fileUp.PicUpload(fileUpload, getSavePath(), goodsPicName, fileUploadContentType);

        if (us != null) {
            if (!(us.getUsernickname().equals(user.getUsernickname())) || !(us.getUserphone().equals(user.getUserphone())) || !(us.getUserqq().equals(user.getUserqq()))) {
                us.setUsernickname(user.getUsernickname());
                us.setUserphone(user.getUserphone());
                us.setUserqq(user.getUserqq());
                goodsService.newInfoUpdateUser(us);
                System.out.println("信息：用户基本信息改变");
            } else {
                System.out.println("信息：用户基本信息未变");
            }
        }

        // 设置用户信息
        goods.setGoodsuser(us);
        // 设置图片路径
        goods.setGoodspictures(pic);
        // 设置时间
        goods.setGoodstime(new Date());

        // 保存信息
        goodsService.newInfo(goods);
        // 得到最后一条记录
        List<Goods> listGoods = goodsService.getLast();
        if (listGoods.size() > 0) {
            index = listGoods.get(0).getId();
        }

        return "goods_newFound_success";
    }

    // 其他方法...
    public String GetAllLost() throws Exception {
        return "goods_getAllLost_success";
    }

    public String GetAllFound() throws Exception {
        return "goods_getAllFound_success";
    }

    public String DeleteInfo() throws Exception {
        return "goods_deleteInfo_failed";
    }

    public String GetAllLostIndex() throws Exception {
        return "goods_getAllLostIndex_success";
    }

    public String GetAllFoundIndex() throws Exception {
        return "goods_getAllFoundIndex_success";
    }

    public String GetAllLostFront() throws Exception {
        return "goods_getAllLostFront_success";
    }

    public String GetAllFoundFront() throws Exception {
        return "goods_getAllFoundFront_success";
    }

    public String GetLastGoodsIndex() throws Exception {
        return "goods_getLastGoodsIndex_success";
    }

    public String GoNewInfo() throws Exception {
        listType = goodsService.getAllType();
        listArea = goodsService.getAllArea();
        return "goods_goNewInfo_success";
    }

    public String GoTheNewInfo() throws Exception {
        Goods goodsInfo = goodsService.find(index);
        if (goodsInfo.getGoodsstatus() == 1) {
            return "goods_goTheNewLostInfo_success";
        } else {
            return "goods_goTheNewFoundInfo_success";
        }
    }

    public String GoTheInfo() throws Exception {
        Goods goodsInfo = goodsService.find(index);
        if (goodsInfo.getGoodsstatus() == 1) {
            return "goods_goTheLostInfo_success";
        } else {
            return "goods_goTheFoundInfo_success";
        }
    }
}
