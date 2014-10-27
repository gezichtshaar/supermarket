package org.nhl.supermarket.actors;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by ruben on 27/10/14.
 */
public class TestNewDutchCustomer {
    Customer customer;

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
        customer = new NewDutchCustomer();
        HashMap<Integer, Integer> desiredProducts = new HashMap<Integer, Integer>();
        desiredProducts.put(1, 3); // Wants three products of ID 1
        desiredProducts.put(2, 1); // Wants one product of ID 2.
        customer.setDesiredProductIds(desiredProducts);
    }

    @Test
    public void testStep() {
        // TODO
    }

    @Test
    public void testAct() {
        // TODO
    }

    @After
    public void tearDown() throws Exception {
        // Code executed after each test
    }
}
