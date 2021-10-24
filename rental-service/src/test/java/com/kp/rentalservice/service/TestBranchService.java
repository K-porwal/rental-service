package com.kp.rentalservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kp.rentalservice.model.Branch;
import com.kp.rentalservice.repository.BranchRepository;
import com.kp.rentalservice.requests.AddBranchRequest;
import com.kp.rentalservice.service.utilities.MessageUtil;

public class TestBranchService {

//	@InjectMocks
//	private BranchService branchService;
//
//	@Mock
//	private BranchRepository branchRepository;
//	
//	@BeforeAll
//	public void init() {
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	
//	@Test
//	public void testAddBranchSuccess() {
//		AddBranchRequest addBranchRequest = new AddBranchRequest("Test");
//		
//		Branch branch = new Branch();
//		branch.setName("Test");
//		when(branchRepository.save(branch)).thenReturn(branch);
//		String response = branchService.addBranch(addBranchRequest);
//		assertEquals(MessageUtil.SUCCESS_ADD_BRANCH, response);
//	}
}
