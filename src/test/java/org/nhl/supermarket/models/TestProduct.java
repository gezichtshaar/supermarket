package org.nhl.supermarket.models;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by ruben on 27/10/14.
 */
public class TestProduct {
    Product product;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Code executed before the first test method
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code executed after the last test method
    }

    @Before
    public void setUp() throws Exception {
        product = new Product(1, "cheese", new BigDecimal("1"));
    }

    @Test
    public void testGetPrice() {
        assertEquals(new BigDecimal("1"), product.getPrice());
    }

    @Test
    public void testGetPriceDiscount() {
        product.setDiscount(new BigDecimal("0.75"));
        assertEquals(new BigDecimal("0.75"), product.getPrice());
    }

    @After
    public void tearDown() throws Exception {
        // Code executed after each test
    }
}
