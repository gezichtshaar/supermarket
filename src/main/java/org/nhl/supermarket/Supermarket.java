package org.nhl.supermarket;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Created by ruben on 02/10/14.
 */
public class Supermarket {
    private Boolean running = false;

    private Database database;

    private Storage storage;
    private Set<Person> persons;
    private ZoneLayout zoneLayout;
    private Queue<Customer> cashRegisterQueue;
    private CashRegister[] cashRegisters;

    public Supermarket() {
        this.database = new Database();

        this.persons = new HashSet<Person>();

        this.storage = new Storage();
        this.zoneLayout = new ZoneLayout();

        this.cashRegisterQueue = new ArrayDeque<Customer>();
        this.cashRegisters = new CashRegister[]{ };
    }

    public Queue<Customer> getCashRegisterQueue() {
        return cashRegisterQueue;
    }

    public void simulate() {
        running = true;

        while (running) {
            // Logic
        }
    }
}
