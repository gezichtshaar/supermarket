package org.nhl.supermarket.models;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.EmptyStackException;

import static org.junit.Assert.*;

/**
 * Created by ruben on 23/10/14.
 */
public class TestAisle {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Aisle aisle;

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
        aisle = new Aisle(new int[]{1, 2});
    }

    @Test
    public void testHasProduct() {
        Product product = new Product(1, "cheese", new BigDecimal("1"));
        aisle.addProduct(product);
        assertTrue(aisle.hasProduct(1));
        assertFalse(aisle.hasProduct(2));
    }

    @Test
    public void testTakeProduct() {
        Product product = new Product(1, "cheese", new BigDecimal("1"));
        aisle.addProduct(product);
        Product takenProduct = aisle.takeProduct(1);
        assertEquals(product, takenProduct);
    }

    @Test
    public void testTakeProductIllegal() {
        thrown.expect(IllegalArgumentException.class);
        aisle.takeProduct(3);
    }

    @Test
    public void testTakeProductEmpty() {
        thrown.expect(EmptyStackException.class);
        aisle.takeProduct(1);
    }

    @Test
    public void testAddProduct() {
        // This code appears impossible to check without using `hasProduct()`?
        // But that would mean that `testAddProduct()` and `testHasProduct()` both call each other's methods, which
        // isn't exactly clean unit testing.
    }

    @Test
    public void testAddProductIllegal() {
        Product product = new Product(3, "bacon", new BigDecimal("1"));
        thrown.expect(IllegalArgumentException.class);
        aisle.addProduct(product);
    }

    @Test
    public void testAddShelf() {
        // TODO
    }

    @Test
    public void testUpdate() {
        // TODO
    }

    @After
    public void tearDown() throws Exception {
        // Code executed after each test
    }
}
