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
 * Ȩ�� - ��Դ����
 * ��Դ����ʵ����
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
	private Integer resId; //��ԴId
	private String name; //��Դ����
	private String path; //��Դ·��
	private Integer parentId; //���ڵ�Id
	private Double rorder; // ����
	private Date createTs; //����ʱ��
	private String note; //��ע
	
	
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
