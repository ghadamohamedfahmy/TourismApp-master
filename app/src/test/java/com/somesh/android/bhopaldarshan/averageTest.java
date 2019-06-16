package com.somesh.android.bhopaldarshan;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class averageTest {
private double sum=2.0;
private double count=1;
        @Mock
        GetData m;
    @Test
    public void mock(){

   when(m.getRate()).thenReturn("8");
   average x=new average();
    x.setAdd(Double.parseDouble(String.valueOf(m)));
    Double a=x.getAdd()+sum/count;

//Double b=a/x.getCount();
assertEquals(String.valueOf(a),"10.0");

    }

    @Test
    public void rate() {
    }
}