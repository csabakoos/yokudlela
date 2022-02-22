package hu.yokudlela.haccp.logic;

import hu.yokudlela.haccp.model.SupplyControl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class is responsible for handling the data regarding supply management.
 * @author csabakoos
 */
public class SupplyRepository {
    private final List<SupplyControl> supplyControlList;

    /**
     * The constructor of the class
     */
    public SupplyRepository() {
        this.supplyControlList = new ArrayList<>();
    }

    /**
     * Adds a new record to the collection of the supply management's control records.
     *
     * @param record The new record to be added.
     */
    public void addRecord(SupplyControl record) {
        this.supplyControlList.add(record);
    }

    /**
     * Returns a record based on the given id or throws an exception accordingly.
     *
     * @param id The id that is used to search for the desired record.
     * @return The desired record.
     */
    public SupplyControl getRecord(String id) {
        Optional<SupplyControl> optional = supplyControlList.stream().filter(x -> x.getId().equals(id)).findFirst();
        if (!optional.isEmpty()) {
            return optional.get();
        } else {
            throw new NoSuchElementException(String.format("No record exists with the given id (%s)", id));
        }
    }

    /**
     * Removes a record that has the given ID from the collection.
     *
     * @param id The desired records ID to be deleted.
     */
    public void deleteRecord(String id) {
        this.supplyControlList.removeIf(x -> x.getId().equals(id));
    }
}
