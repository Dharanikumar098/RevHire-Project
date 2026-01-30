package com.revhire.service;

import static org.junit.Assert.*;
import org.junit.Test;
import com.revhire.employer.EmployerService;

public class EmployerServiceTest {

    EmployerService service = new EmployerService();

    @Test
    public void testViewCompanyProfile() {
        service.viewCompanyProfile(1);
        assertTrue(true);
    }
}
