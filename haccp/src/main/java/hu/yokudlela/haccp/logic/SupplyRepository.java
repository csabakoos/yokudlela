package hu.yokudlela.haccp.logic;

import hu.yokudlela.haccp.model.StorageControl;
import hu.yokudlela.haccp.model.SupplyControl;
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
 * This class is responsible for handling the data regarding supply management.
 *
 * @author csabakoos
 */
@Repository
public interface SupplyRepository extends CrudRepository<SupplyControl, String> {

    /**
     * Returns records on the given date.
     * @param pDate The exact date.
     * @return
     */
    public List<SupplyControl> findByDate(LocalDate pDate);

    /**
     * Adds a new record.
     * @param pSupplyControl The new record's "description".
     * @return
     */
    public SupplyControl save(SupplyControl pSupplyControl);

    /**
     * Deletes the given record.
     * @param pSupplyControl The record to be deleted.
     */
    public void delete (SupplyControl pSupplyControl);

}
