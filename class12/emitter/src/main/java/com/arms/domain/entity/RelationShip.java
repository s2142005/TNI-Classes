package com.arms.domain.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "relation_ship")
public class RelationShip implements Serializable {

    private int id;
    private int userId;
    private int followerId;

    

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
    @Basic
    @Column(name = "follower_id")
    public Integer getFollowerId() {
        return followerId;
    }
    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }
}

