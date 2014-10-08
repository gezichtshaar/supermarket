package org.nhl.supermarket;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import org.nhl.supermarket.actors.Customer;
import org.nhl.supermarket.interfaces.BuyZone;
import org.nhl.supermarket.interfaces.Person;
import org.nhl.supermarket.models.CashRegister;
import org.nhl.supermarket.models.Database;
import org.nhl.supermarket.models.Storage;
import org.nhl.supermarket.models.Location;

/**
 * Created by ruben on 02/10/14.
 */
public class Supermarket {
    private Boolean running = false;

    private Database database;

    private Set<Person> persons;
    private Queue<Customer> cashRegisterQueue;
    
    private Storage storage;
    private BuyZone[][] layout;
    private CashRegister[] cashRegisters;

    public Supermarket(BuyZone[][] layout) {
    	this.layout = layout;
    	
        this.database = new Database();

        this.persons = new HashSet<Person>();

        this.storage = new Storage();

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
