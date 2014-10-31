package org.nhl.supermarket;

import java.util.HashSet;
import java.util.Observable;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.nhl.supermarket.actors.Customer;
import org.nhl.supermarket.actors.Employee;
import org.nhl.supermarket.actors.NewDutchCustomer;
import org.nhl.supermarket.interfaces.BuyZone;
import org.nhl.supermarket.interfaces.Person;
import org.nhl.supermarket.interfaces.Task;
import org.nhl.supermarket.models.CashRegister;
import org.nhl.supermarket.models.Database;
import org.nhl.supermarket.models.Storage;

/**
 * Created by ruben on 02/10/14.
 */
public class Supermarket extends Observable implements Runnable {
    private Boolean running = false;

    private Database database;

    private Set<Person> persons;
    private Queue<Customer> cashRegisterQueue;
    
    private Storage storage;
    private BuyZone[] buyZones;
    private CashRegister[] cashRegisters;

    public Supermarket(BuyZone[] buyZones) {
        this.buyZones = buyZones;

        this.database = new Database();

        this.persons = new HashSet<Person>();

        this.storage = new Storage();

        this.cashRegisterQueue = new ConcurrentLinkedQueue<Customer>();
        this.cashRegisters = new CashRegister[]{ };
    }

    public BuyZone[] getBuyZones() {
        return buyZones;
    }

    public Queue<Customer> getCashRegisterQueue() {
        return cashRegisterQueue;
    }

    public CashRegister[] getCashRegisters() {
        return cashRegisters;
    }

    public Storage getStorage() {
        return storage;
    }

    public void addCustomer() {
        persons.add(new NewDutchCustomer());
    }

    public boolean hasEmployee(Task task) {
        for (Person person : persons) {
            if (person instanceof Employee) {
                // Well isn't this ugly.
                Employee employee = (Employee)person;
                if (employee.getTask() == task) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void tick() {
    	this.database.initCommit();
    	// code for actor updates
    	this.database.doCommit();
    }

    public void run() {
        running = true;

        System.out.println("start");
        while (running) {
        	setChanged();
            tick();
            notifyObservers();
            System.out.println("running");
        }

        close();
        System.out.println("stop");
    }
    
    public void close() {
    	this.database.close();
    }
    
    @Override
    public String toString() {
    	return String.format("Aantal personen: %d", persons.size());
    }
}
