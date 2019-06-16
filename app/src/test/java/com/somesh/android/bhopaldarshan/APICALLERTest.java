package com.somesh.android.bhopaldarshan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class APICALLERTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getComment() {
    }

    @Test
    public void setComment() {
    }

    @Test
    public void getContent() {
    }

    @Test
    public void machine() {
        APICALLER obj=new APICALLER();
        String res=obj.Machine("https://testapicore.conveyor.cloud/api/values/","good");
        assertEquals("8",res);
    }
}