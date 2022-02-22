package hu.yokudlela.haccp.logic;

import hu.yokudlela.haccp.model.StorageControl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class is responsible for handling the data regarding storage management.
 * @author csabakoos
 */
public class StorageRepository {
    private final List<StorageControl> storageControlList;

    /**
     * The constructor of the class
     */
    public StorageRepository() {
        this.storageControlList = new ArrayList<>();
    }

    /**
     * Adds a new record to the collection of the storage management's control records.
     *
     * @param record The new record to be added.
     */
    public void addRecord(StorageControl record) {
        this.storageControlList.add(record);
    }

    /**
     * Returns a record based on the given id or throws an exception accordingly.
     *
     * @param id The id that is used to search for the desired record.
     * @return The desired record.
     */
    public StorageControl getRecord(String id) {
        Optional<StorageControl> optional = storageControlList.stream().filter(x -> x.getId().equals(id)).findFirst();
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
        this.storageControlList.removeIf(x -> x.getId().equals(id));
    }
}
