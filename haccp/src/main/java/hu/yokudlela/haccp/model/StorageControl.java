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
    private String id;
    @Schema(description = "Control Date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    @Schema(description = "Checked Item")
    private String item;
    @Schema(description = "Packaging status")
    private boolean packaging;
    @Schema(description = "Conditions Status")
    private boolean conditions;
    @Schema(description = "Warranty Status")
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


