package com.revhire.dao;

import static org.junit.Assert.*;
import org.junit.Test;

import java.sql.Connection;

import com.revhire.util.DBConnection;

public class DBConnectionTest {

    @Test
    public void testDBConnection() {

        Connection con = DBConnection.getConnection();

        assertNotNull("DB Connection should not be null", con);
    }
}
