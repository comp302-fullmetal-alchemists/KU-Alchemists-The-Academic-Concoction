package system.domain.interfaces;

public interface Observer {
    /*
     * Observer is the interface that makes the model-view seperation. Domain objects know only that they have an Observer UI
     * that is observing their changes. Domain objects can send messages to their Observers, and wait for them to handle it accordingly.
     * By specifying the message, domain objects can request specific changes from observers, that is to not make them render
     *  everything in the domain again and again. 
     */
    void update(String msg);
}
