package com.revhire.service;

import static org.junit.Assert.*;
import org.junit.Test;

import java.sql.ResultSet;

import com.revhire.dao.JobSeekerDAO;

public class JobSeekerServiceTest {

	@Test
	public void testGetJobSeekerProfile() {

	    JobSeekerDAO dao = new JobSeekerDAO();

	    try {
	        ResultSet rs =
	            dao.getAnyJobSeeker();   // helper method

	        assertNotNull(rs);
	        assertTrue(rs.next());
	        assertNotNull(rs.getString("email"));

	    } catch (Exception e) {
	        fail("Exception while fetching job seeker profile");
	    }
	}
