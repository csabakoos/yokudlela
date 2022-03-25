package hu.yokudlela.haccp.logic;

import hu.yokudlela.haccp.model.StorageControl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * This class is responsible for handling the data regarding storage management.
 *
 * @author csabakoos
 */
@Repository
public interface StorageRepository extends CrudRepository<StorageControl, String> {

    /**
     * Returns records on the given date.
     * @param pDate The exact date.
     * @return
     */
    public List<StorageControl> findByDate(LocalDate pDate);

    /**
     * Returns a record based on the given ID.
     * @param id The id for the record.
     * @return The desired record with the it.
     */
    public Optional<StorageControl> findById(String id);

    /**
     * Adds a new record.
     * @param pStorageControl The new record's "description".
     * @return
     */
    public StorageControl save(StorageControl pStorageControl);

    /**
     * Deletes the given record.
     * @param pStorageControl The record to be deleted.
     */
    public void delete (StorageControl pStorageControl);

}
