package hu.yokudlela.haccp.model;

import java.time.LocalDate;
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
@Schema(description = "Waste")
public class WasteControl {
    @Schema(description = "Control ID")
    private String id;
    @Schema(description = "Control Date")
    private LocalDate date;
    @Schema(description = "Waste Amount")
    private Integer amount;
    @Schema(description = "Dumped Status")
    private boolean dumped;

    @Builder
    public WasteControl(String id, LocalDate date, Integer amount, boolean dumped) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.dumped = dumped;
    }
}
