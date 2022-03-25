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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * REST controller class for waste solutions.
 *
 * @author csabakoos
 */
@RestController
@RequestMapping(path = "/waste")
@Validated
public class WasteController {

    @Autowired
    WasteRepository wasteService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The requested supply record",
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
            summary = "Request supply record by id",
            security = {
                    @SecurityRequirement(name = "apikey",scopes = {"haccp"}),
                    @SecurityRequirement(name = "openid",scopes = {"haccp"}),
                    @SecurityRequirement(name = "oauth2",scopes = {"haccp"})
            }
    )
    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WasteControl> getRecordsByDate(
            @Parameter(description = "Date", required = true) @PathVariable(name = "date", required = true) LocalDate date
            ) throws NoSuchElementException {
        return this.wasteService.findByDate(date);
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
    public void save(@Valid @Parameter(description = "The new record",required = true) @RequestBody(required = true) WasteControl record) throws NoSuchElementException, InstanceAlreadyExistsException {
        this.wasteService.save(record);
    }

}
