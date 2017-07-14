package com.cuiboshi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色管理实体类
 * 
 * @author Administrator
 *
 */
@Table(name="sys_author_role")
@Entity
public class AuthorRole {

	@Id
    @Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	
	@Column
	private String roleName;
	
	@Column
	private Date createTs;
	
	@Column
	private double orderBy;
	
	@Column
	private String note;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	public double getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(double orderBy) {
		this.orderBy = orderBy;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
