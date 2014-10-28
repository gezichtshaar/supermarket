package org.nhl.supermarket.actors;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.Person;
import org.nhl.supermarket.interfaces.Task;

/**
 * Created by remy on 06/10/14.
 */
public class Employee implements Person {
    private Task task;

    @Override
    public void act(Supermarket supermarket) {
        if (task != null) {
            task.update(supermarket);
        }
    }
}
