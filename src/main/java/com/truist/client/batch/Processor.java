package com.truist.client.batch;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.truist.client.entity.Users;
import com.truist.client.entity.UsersVO;
import com.truist.client.repository.UsersRepository;

@Component
public class Processor implements ItemProcessor<UsersVO, UsersVO> {

	@Autowired
	private UsersRepository userRepo;


	@Override
	public UsersVO process(UsersVO usersVO) throws Exception {

		Optional<Users> userFromDb = userRepo.findById(usersVO.getUserId());
		
		System.out.println(" userFromDb.isPresent() ++++++++"+userFromDb.isPresent());

		if (userFromDb.isPresent()) {
			usersVO.setUserId(userFromDb.get().getUserId());
		}
		return usersVO;
	}

}
