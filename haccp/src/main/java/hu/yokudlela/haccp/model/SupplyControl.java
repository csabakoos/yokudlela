package hu.yokudlela.haccp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This class represents a single control of an incoming supply.
 *
 * @author csabakoos
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@Schema(description = "Supply")
public class SupplyControl {
    @Schema(description = "Control ID")
    private String id;
    @Schema(description = "Control Date")
    private LocalDate date;
    @Schema(description = "Checked Item")
    private String item;
    @Schema(description = "Invoice")
    private boolean invoice;
    @Schema(description = "Packaging Status")
    private boolean packaging;
    @Schema(description = "Warranty Status")
    private boolean warranty;

    @Builder
    public SupplyControl(String id, LocalDate date, String item, boolean invoice, boolean packaging, boolean warranty) {
        this.id = id;
        this.date = date;
        this.item = item;
        this.invoice = invoice;
        this.packaging = packaging;
        this.warranty = warranty;
    }
}
