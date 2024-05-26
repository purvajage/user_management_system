package com.demo.resource.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	
	@NotBlank(message = "rolename column must not be blank.")
	@NotEmpty(message = "rolename column must not be empty.")
	private String roleName;
	
	@NotBlank(message = "roledescription column must not be blank.")
	@NotEmpty(message = "roledescription column must not be empty.")
	private String roleDescription;
	
	@ElementCollection
	private List<String> rolePermission;
	
	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnore
	private User user;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public List<String> getRolePermission() {
		return rolePermission;
	}

	public void setRolePermission(List<String> rolePermission) {
		this.rolePermission = rolePermission;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Roles(Long roleId, String roleName, String roleDescription, User user) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.user = user;
	}

	
	
	
}
