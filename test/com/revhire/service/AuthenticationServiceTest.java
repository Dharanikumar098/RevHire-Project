package com.revhire.service;

import static org.junit.Assert.*;
import org.junit.Test;

import com.revhire.auth.AuthenticationService;

public class AuthenticationServiceTest {

    AuthenticationService authService =
        new AuthenticationService();

    @Test
    public void testJobSeekerLoginValid() {

        int jobSeekerId =
            authService.loginJobSeeker(
                "dhanu1@gmail.com",  // MUST exist in DB
                "123456"
            );

        assertTrue(jobSeekerId > 0);
    }

    @Test
    public void testJobSeekerLoginInvalid() {

        int jobSeekerId =
            authService.loginJobSeeker(
                "wrong@gmail.com",
                "wrong"
            );

        assertEquals(-1, jobSeekerId);
    }
}
