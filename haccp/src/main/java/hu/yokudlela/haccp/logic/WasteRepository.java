package hu.yokudlela.haccp.logic;

import hu.yokudlela.haccp.model.WasteControl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class is responsible for handling the data regarding waste management.
 * @author csabakoos
 */
public class WasteRepository {

    private final List<WasteControl> wasteControlList;

    /**
     * The constructor of the class
     */
    public WasteRepository() {
        this.wasteControlList = new ArrayList<>();
    }

    /**
     * Adds a new record to the collection of the waste management's control records.
     *
     * @param record The new record to be added.
     */
    public void addRecord(WasteControl record) {
        this.wasteControlList.add(record);
    }

    /**
     * Returns a record based on the given id or throws an exception accordingly.
     *
     * @param id The id that is used to search for the desired record.
     * @return The desired record.
     */
    public WasteControl getRecord(String id) {
        Optional<WasteControl> optional = wasteControlList.stream().filter(x -> x.getId().equals(id)).findFirst();
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
        this.wasteControlList.removeIf(x -> x.getId().equals(id));
    }
}
