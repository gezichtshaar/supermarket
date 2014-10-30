package org.nhl.supermarket.actors;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;
import org.nhl.supermarket.interfaces.Person;
import org.nhl.supermarket.interfaces.Task;
import org.nhl.supermarket.models.CashRegister;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remy on 06/10/14.
 */
public class Employee implements Person {
    private Task task;

    public Task getTask() {
        return task;
    }

    private int cashRegisterEmployeesAmount(Supermarket supermarket) {
        int amount = 0;
        for (CashRegister cashRegister : supermarket.getCashRegisters()) {
            if (supermarket.hasEmployee(cashRegister)) {
                amount++;
            }
        }
        return amount;
    }

    private List<BuyZone> buyZonesWithCustomers(Supermarket supermarket) {
        List<BuyZone> buyZones = new ArrayList<BuyZone>();
        for (BuyZone buyZone : supermarket.getBuyZones()) {
            if (!buyZone.queueIsEmpty()) {
                buyZones.add(buyZone);
            }
        }
        return buyZones;
    }

    @Override
    public void act(Supermarket supermarket) {
        int employeesAtCashRegisters = cashRegisterEmployeesAmount(supermarket);
        int queueSize = supermarket.getCashRegisterQueue().size();
        int amountOfRequiredRegisters = (queueSize / 4) + 1;
        List<BuyZone> serviceableBuyZones = buyZonesWithCustomers(supermarket);

        if (employeesAtCashRegisters == 0) {
            // Go to first cash register.
            task = supermarket.getCashRegisters()[0];
        } else if (amountOfRequiredRegisters > employeesAtCashRegisters &&
                employeesAtCashRegisters < supermarket.getCashRegisters().length) {
            // Go to another cash register.
            task = supermarket.getCashRegisters()[employeesAtCashRegisters];
        } else if (!serviceableBuyZones.isEmpty() && !supermarket.hasEmployee(serviceableBuyZones.get(0))) {
            // Help a customer at a Department.
            task = serviceableBuyZones.get(0);
        } else {
            // Fill a BuyZone.
            int smallestSize = -1;
            int buyZoneSize;

            for (BuyZone buyZone : supermarket.getBuyZones()) {
                buyZoneSize = buyZone.sizeOfSmallestShelf();
                if (buyZoneSize < smallestSize || smallestSize == -1) {
                    smallestSize = buyZoneSize;
                    task = buyZone;
                }
            }
        }

        task.update(supermarket);
    }
}
