package com.truist.client.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.truist.client.entity.Department;
import com.truist.client.entity.Users;
import com.truist.client.entity.UsersVO;
import com.truist.client.repository.DepartmentRepository;
import com.truist.client.repository.UsersRepository;

@Component
public class Writer implements ItemWriter<UsersVO> {

	@Autowired
	private UsersRepository repo;

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	@Transactional
	public void write(List<? extends UsersVO> usersVO) throws Exception {
		saveUserList(usersVO);

	}

	private void saveUserList(List<? extends UsersVO> usersVOList) {
		// TODO Auto-generated method stub
		List<Users> usersList = new ArrayList<>();
		for (UsersVO usersVO : usersVOList) {
			Users users = new Users();
			users.setUserId(usersVO.getUserId());
			users.setName(usersVO.getName());

			Department depart = new Department();
			Department existing = departmentRepository.findByDeptAndAccountAndUserId(usersVO.getDept(),
					usersVO.getAccount(), usersVO.getUserId());
			if (existing != null)
				depart.setDeptId(existing.getDeptId());
			depart.setDept(usersVO.getDept());
			depart.setAccount(usersVO.getAccount());

			List<Department> deptList = new ArrayList<Department>();
			deptList.add(depart);

			users.setDepertments(deptList);
			// usersList.add(users);
			repo.saveAndFlush(users);

		}
	}

}
