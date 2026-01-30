package com.revhire.service;

import static org.junit.Assert.*;
import org.junit.Test;
import com.revhire.jobseeker.JobSearchService;

public class JobSearchServiceTest {

    JobSearchService service = new JobSearchService();

    @Test
    public void testViewJobs() {
        service.viewJobs();
        assertTrue(true);
    }
}
