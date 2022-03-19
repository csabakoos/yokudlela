package hu.yokudlela.haccp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

/**
 * This class represents a single control of the storage facility.
 *
 * @author csabakoos
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@Schema(description = "Storage")
public class StorageControl {

    @Schema(description = "Control ID")
    @NotBlank(message = "error.storage.id.notset")
    @NotNull(message = "error.storage.id.notset")
    private String id;

    @Schema(description = "Control Date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotBlank(message = "error.storage.date.notset")
    @NotNull(message = "error.storage.date.notset")
    @PastOrPresent(message = "error.storage.date.future")
    private LocalDate date;

    @Schema(description = "Checked Item")
    @NotBlank(message = "error.storage.item.notset")
    @NotNull(message = "error.storage.item.notset")
    private String item;

    @Schema(description = "Packaging status")
    @NotBlank(message = "error.storage.packaging.notset")
    @NotNull(message = "error.storage.packaging.notset")
    private boolean packaging;

    @Schema(description = "Conditions Status")
    @NotBlank(message = "error.storage.conditions.notset")
    @NotNull(message = "error.storage.conditions.notset")
    private boolean conditions;

    @Schema(description = "Warranty Status")
    @NotBlank(message = "error.storage.warranty.notset")
    @NotNull(message = "error.storage.warranty.notset")
    private boolean warranty;

    @Builder
    public StorageControl(String id, LocalDate date, String item, boolean packaging, boolean conditions, boolean warranty) {
        this.id = id;
        this.date = date;
        this.item = item;
        this.packaging = packaging;
        this.conditions = conditions;
        this.warranty = warranty;
    }
}


