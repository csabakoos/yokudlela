package hu.yokudlela.haccp.model;

import java.time.LocalDateTime;

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
    private LocalDateTime date;
    @Schema(description = "Checked Item")
    private String item;
    @Schema(description = "Packaging status")
    private boolean packaging;
    @Schema(description = "Conditions Status")
    private boolean conditions;
    @Schema(description = "Warranty Status")
    private boolean warranty;

    @Builder
    public StorageControl(String id, LocalDateTime date, String item, boolean packaging, boolean conditions, boolean warranty) {
        this.id = id;
        this.date = date;
        this.item = item;
        this.packaging = packaging;
        this.conditions = conditions;
        this.warranty = warranty;
    }
}


