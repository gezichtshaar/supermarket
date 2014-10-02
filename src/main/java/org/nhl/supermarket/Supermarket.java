package org.nhl.supermarket;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Created by ruben on 02/10/14.
 */
public class Supermarket {
    private Database database;

    private Storage storage;
    private Set<Person> persons;
    private BuyZone[][] buyZone;
    private Queue<Customer> cashRegisterQueue;
    private CashRegister[] cashRegisters;

    public Supermarket() {
        this.database = new Database();

        this.persons = new HashSet<Person>();

        this.storage = new Storage();
        this.buyZone = new BuyZone[][]{{ }};

        this.cashRegisterQueue = new ArrayDeque<Customer>();
        this.cashRegisters = new CashRegister[]{ };
    }

    public void simulate() {
        while (true) {
            // Logic
        }
    }
}
