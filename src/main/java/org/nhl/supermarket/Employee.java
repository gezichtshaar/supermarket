package org.nhl.supermarket;

/**
 * Created by remy on 06/10/14.
 */
public class Employee implements Person {
    private Task task;
    public void act(Supermarket supermarket){
        if(task != null) {
            task.update(supermarket);
        }
    }
}
