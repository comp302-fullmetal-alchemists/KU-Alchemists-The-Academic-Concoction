package system.domain.interfaces;


public interface Collector {

	/* 
	 * Collector interface works with mediator to maintain low coupling between game areas like IngredientStorage,
	 * PotionBrewingArea and current player. Collectors are the game areas, they wait for the items that player may
	 * send to them. They handle those items that are given to them individually. 
	 * activation is about whether the collector is connected to the mediator or not.
	 * */
	
    <T> boolean collectItem(T item);

    void activate();
    
    void deactivate();

    boolean isActive();
}
