package hu.yokudlela.haccp.rest;

import hu.yokudlela.haccp.logic.SupplyRepository;
import hu.yokudlela.haccp.logic.WasteRepository;
import hu.yokudlela.haccp.model.StorageControl;
import hu.yokudlela.haccp.model.SupplyControl;
import hu.yokudlela.haccp.model.WasteControl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.NoSuchElementException;

/**
 * REST controller class for waste solutions.
 *
 * @author csabakoos
 */
@RestController
@RequestMapping(path = "/waste")
public class WasteController {

    @Autowired
    WasteRepository wasteService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The requested supply record",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StorageControl.class)) }),
            @ApiResponse(responseCode = "500", description = "Unsuccessful query",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(summary = "Request supply record by id")
    @GetMapping(path = "/getRecordById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WasteControl getRecordById(
            @Parameter(description="Record's id", required = true, example = "0x3FFF000000000000")
            @PathVariable(name = "id", required = true) String id) throws NoSuchElementException {
        return this.wasteService.getRecord(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New record added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StorageControl.class)) }),
            @ApiResponse(responseCode = "500", description = "Record already exists.",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(summary = "Add new storage control record.")
    @PostMapping(path = "/addRecord", produces = MediaType.APPLICATION_JSON_VALUE)
    public WasteControl add(@Parameter(description = "The new record",required = true) @RequestBody(required = true) WasteControl record) throws NoSuchElementException, InstanceAlreadyExistsException {
        this.wasteService.addRecord(record);
        return record;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record deleted succesfully",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "No such record to delete",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(summary = "Delete a storage record")
    @DeleteMapping(path = "/deleteRecord/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteRecord(
            @Parameter(description = "Storage record's id", required = true, example = "0x3FFF000000000000")
            @PathVariable(name = "id", required = true) String id) throws InstanceNotFoundException {
        this.wasteService.deleteRecord(id);
    }

}
