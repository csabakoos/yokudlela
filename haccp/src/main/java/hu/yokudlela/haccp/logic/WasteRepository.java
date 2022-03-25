package hu.yokudlela.haccp.logic;

import hu.yokudlela.haccp.model.StorageControl;
import hu.yokudlela.haccp.model.WasteControl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class is responsible for handling the data regarding waste management.
 *
 * @author csabakoos
 */
@Repository
public interface WasteRepository extends CrudRepository<WasteControl, String> {

    /**
     * Returns records on the given date.
     * @param pDate The exact date.
     * @return
     */
    public List<WasteControl> findByDate(LocalDate pDate);

    /**
     * Adds a new record.
     * @param pWasteControl The new record's "description".
     * @return
     */
    public WasteControl save(WasteControl pWasteControl);

    /**
     * Deletes the given record.
     * @param pWasteControl The record to be deleted.
     */
    public void delete (WasteControl pWasteControl);

}
