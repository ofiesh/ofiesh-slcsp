package com.ofiesh.slcsp;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ZipFactoryTest {

    private ZipFactory zipFactory = new ZipFactory();

    @Test
    public void testValidZipColumns() {
        Zip zip = zipFactory.createZip(new String[]{"36749", "AL", "01001", "Autauga", "11"});
        assertEquals("Zipcode didn't match", "36749", zip.getZipcode());
        assertEquals("State didn't match", "AL", zip.getState());
        assertEquals("County code didn't match", "01001", zip.getCountyCode());
        assertEquals("Name didn't match", "Autauga", zip.getName());
        assertEquals("Area didn't match", "11", zip.getRateArea());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidZipColums() {
        zipFactory.createZip(new String[]{"1", "2"});
    }
}
