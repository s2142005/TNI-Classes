package com.arms.domain.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {

	private int id;
	private String name;
	private String email;
	private String password;
	private Date created;
	private Date updated;
	private List<Micropost> micropostList;
	private String avatar;



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
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
	@Column(name = "created")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Basic
	@Column(name = "updated")
	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	public List<Micropost> getMicropostList() {
		return micropostList;
	}
	public void setMicropostList(List<Micropost> micropostList) {
		this.micropostList = micropostList;
	}
	@Basic
	@Column(name = "avatar")
	public String getAvatar(){
		return "http://secure.gravatar.com/avatar/" + avatar;
	}
	public void setAvatar(String avatar){
		this.avatar = avatar;
	}	

}
