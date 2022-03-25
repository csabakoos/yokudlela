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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    @Cacheable(cacheNames = "getbyid", key = "#id")
    @Operation(
            summary = "Request storage record by id",
            security = {
                    @SecurityRequirement(name = "apikey",scopes = {"haccp"}),
                    @SecurityRequirement(name = "openid",scopes = {"haccp"}),
                    @SecurityRequirement(name = "oauth2",scopes = {"haccp"})
            }
    )
    @GetMapping(path = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<StorageControl> getRecordById(
            @Parameter(description="ID", required = true) @PathVariable(name = "id", required = true) String id
    ) throws NoSuchElementException {
        return this.storageService.findById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The requested storage record(s)",
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
    @Cacheable(cacheNames = "getbydate", key = "#date")
    @Operation(
            summary = "Request storage records by date",
            security = {
                    @SecurityRequirement(name = "apikey",scopes = {"haccp"}),
                    @SecurityRequirement(name = "openid",scopes = {"haccp"}),
                    @SecurityRequirement(name = "oauth2",scopes = {"haccp"})
            }
    )
    @GetMapping(path = "/getByDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StorageControl> getRecordsByDate(
            @Parameter(description="Date", required = true) @PathVariable(name = "date", required = true) LocalDate date
    ) throws NoSuchElementException {
        return this.storageService.findByDate(date);
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
    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public void save(@Valid @Parameter(description = "The new record",required = true) @RequestBody(required = true) StorageControl record) throws NoSuchElementException, InstanceAlreadyExistsException {
        this.storageService.save(record);
    }

}
