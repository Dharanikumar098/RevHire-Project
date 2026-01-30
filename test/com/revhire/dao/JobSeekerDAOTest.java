package com.revhire.dao;


import static org.junit.Assert.*;
import org.junit.Test;

import com.revhire.dao.JobSeekerDAO;

public class JobSeekerDAOTest {

    JobSeekerDAO dao = new JobSeekerDAO();

    @Test
    public void testValidLogin() {

        int jobSeekerId =
            dao.loginAndGetJobSeekerId("dharani@gmail.com", "123456");

        assertTrue("Job seeker ID should be valid", jobSeekerId > 0);
    }

    @Test
    public void testInvalidLogin() {

        int jobSeekerId =
            dao.loginAndGetJobSeekerId("wrong@gmail.com", "wrong");

        assertEquals(-1, jobSeekerId);
    }
}

