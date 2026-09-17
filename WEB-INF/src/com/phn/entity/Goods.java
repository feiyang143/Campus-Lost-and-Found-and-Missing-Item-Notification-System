package com.phn.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_goods")
public class Goods {
    private int id;
    private String goodsname;
    private String goodsdescribe;
    private Date goodstime;
    private int goodsstatus;
    private String goodspictures;
    private int reward_points;
    private Area goodsarea;
    private Type goodstype;
    private Set<Comment> goodscomments = new HashSet<Comment>();
    private User goodsuser;

    public Goods() {
    }

    public Goods(int goodsstatus) {
        this.goodsstatus = goodsstatus;
        // 如果是寻物启事，设置reward_points=1
        if (goodsstatus == 1) {
            this.reward_points = 1;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 30)
    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodsdescribe() {
        return goodsdescribe;
    }

    public void setGoodsdescribe(String goodsdescribe) {
        this.goodsdescribe = goodsdescribe;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getGoodstime() {
        return goodstime;
    }

    public void setGoodstime(Date goodstime) {
        this.goodstime = goodstime;
    }

    public int getGoodsstatus() {
        return goodsstatus;
    }

    public void setGoodsstatus(int goodsstatus) {
        this.goodsstatus = goodsstatus;
    }

    @Column(length = 255)
    public String getGoodspictures() {
        return goodspictures;
    }

    public void setGoodspictures(String goodspictures) {
        this.goodspictures = goodspictures;
    }

    public int getReward_points() {
        return reward_points;
    }

    public void setReward_points(int reward_points) {
        this.reward_points = reward_points;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id")
    public Area getGoodsarea() {
        return goodsarea;
    }

    public void setGoodsarea(Area goodsarea) {
        this.goodsarea = goodsarea;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    public Type getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(Type goodstype) {
        this.goodstype = goodstype;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentgoods")
    public Set<Comment> getGoodscomments() {
        return goodscomments;
    }

    public void setGoodscomments(Set<Comment> goodscomments) {
        this.goodscomments = goodscomments;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST }, optional = true)
    @JoinColumn(name = "user_id")
    public User getGoodsuser() {
        return goodsuser;
    }

    public void setGoodsuser(User goodsuser) {
        this.goodsuser = goodsuser;
    }
}
