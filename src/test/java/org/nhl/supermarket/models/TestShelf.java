package org.nhl.supermarket.models;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.rules.ExpectedException;
import org.nhl.supermarket.models.Product;
import org.nhl.supermarket.models.Shelf;

import java.math.BigDecimal;

/**
 * Created by ruben on 23/10/14.
 */
public class TestShelf {
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    private Shelf shelf;

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Code executed before the first test method
    }

    @Before
    public void setUp() throws Exception {
        shelf = new Shelf(1);
    }

    @Test
    public void testAddProduct() {
        assertTrue(shelf.getProducts().isEmpty());

        Product product = new Product(1, "cheese", new BigDecimal("1"));
        shelf.addProduct(product);

        assertFalse(shelf.getProducts().isEmpty());
    }

    @Test
    public void testAddProductIllegal() {
        Product product = new Product(2, "milk", new BigDecimal("1"));
        thrown.expect(IllegalArgumentException.class);
        shelf.addProduct(product);
    }

    @After
    public void tearDown() throws Exception {
        // Code executed after each test
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code executed after the last test method
    }
}
