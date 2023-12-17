package com.peace.GetInternship.dao;


import com.peace.GetInternship.model.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
