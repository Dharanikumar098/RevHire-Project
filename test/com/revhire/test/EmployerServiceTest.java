package com.revhire.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.revhire.dao.EmployerDAO;

public class EmployerServiceTest {

    @Test
    public void testUpdateCompanyProfile() {

        EmployerDAO dao = new EmployerDAO();

        boolean updated =
            dao.updateCompanyProfile(
                1,
                "IT",
                "100",
                "company.com",
                "Hyderabad",
                "JUnit test"
            );

        assertTrue(updated);
    }
}
