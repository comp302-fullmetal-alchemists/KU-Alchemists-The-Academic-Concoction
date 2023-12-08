package system.domain.interfaces;


public interface Collector {

    <T> boolean collectItem(T item);

    void activate();
    
    void deactivate();

    boolean isActive();
}
