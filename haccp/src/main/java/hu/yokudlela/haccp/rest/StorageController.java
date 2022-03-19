package hu.yokudlela.haccp.rest;

import hu.yokudlela.haccp.logic.StorageRepository;
import hu.yokudlela.haccp.model.StorageControl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.NoSuchElementException;

/**
 * REST controller class for storage solutions.
 *
 * @author csabakoos
 */
@RestController
@RequestMapping(path = "/storage")
@Validated
public class StorageController {

    @Autowired
    StorageRepository storageService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The requested storage record",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StorageControl.class)) }),
            @ApiResponse(responseCode = "403", description = "Missing permission",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "302", description = "Login required, redirecting to login page",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Unsuccessful query",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(
            summary = "Request storage record by id",
            security = {
                    @SecurityRequirement(name = "apikey",scopes = {"haccp"}),
                    @SecurityRequirement(name = "openid",scopes = {"haccp"}),
                    @SecurityRequirement(name = "oauth2",scopes = {"haccp"})
            }
    )
    @GetMapping(path = "/getRecordById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StorageControl getRecordById(
            @Parameter(description="Record's id", required = true, example = "0x3FFF000000000000")
            @PathVariable(name = "id", required = true) String id) throws NoSuchElementException {
        return this.storageService.getRecord(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New record added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StorageControl.class)) }),
            @ApiResponse(responseCode = "403", description = "Missing permission",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "302", description = "Login required, redirecting to login page",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Record already exists.",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(
            summary = "Add new storage control record.",
            security = {
                    @SecurityRequirement(name = "apikey",scopes = {"haccp"}),
                    @SecurityRequirement(name = "openid",scopes = {"haccp"}),
                    @SecurityRequirement(name = "oauth2",scopes = {"haccp"})
            }
    )
    @PostMapping(path = "/addRecord", produces = MediaType.APPLICATION_JSON_VALUE)
    public StorageControl add(@Valid @Parameter(description = "The new record",required = true) @RequestBody(required = true) StorageControl record) throws NoSuchElementException, InstanceAlreadyExistsException {
        this.storageService.addRecord(record);
        return record;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record deleted succesfully",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "Missing permission",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "302", description = "Login required, redirecting to login page",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "No such record to delete",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(
            summary = "Delete a storage record",
            security = {
                @SecurityRequirement(name = "apikey",scopes = {"haccp"}),
                @SecurityRequirement(name = "openid",scopes = {"haccp"}),
                @SecurityRequirement(name = "oauth2",scopes = {"haccp"})
            }
    )
    @DeleteMapping(path = "/deleteRecord/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteRecord(
            @Parameter(description = "Storage record's id", required = true, example = "0x3FFF000000000000")
            @PathVariable(name = "id", required = true) String id) throws InstanceNotFoundException {
        this.storageService.deleteRecord(id);
    }

}
