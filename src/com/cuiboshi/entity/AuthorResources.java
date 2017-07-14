package com.cuiboshi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 权限 - 资源管理
 * 资源树的实体类
 * @author Administrator
 *
 */
@Table(name="sys_author_resources")
@Entity
public class AuthorResources implements Serializable {
	
	private static final long serialVersionUID = -7941384660776582002L;
	
	@Id
    @Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resId; //资源Id
	private String name; //资源名称
	private String path; //资源路径
	private Integer parentId; //父节点Id
	private Double rorder; // 排序
	private Date createTs; //创建时间
	private String note; //备注
	
	
	public Integer getResId() {
		return resId;
	}
	public void setResId(Integer resId) {
		this.resId = resId;
	}
	
	@Column
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Column
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	@Column
	public Double getRorder() {
		return rorder;
	}
	public void setRorder(Double rorder) {
		this.rorder = rorder;
	}
	 
	@Column
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTs() {
		return createTs;
	}
	
	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}
	@Column
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	
	
}
