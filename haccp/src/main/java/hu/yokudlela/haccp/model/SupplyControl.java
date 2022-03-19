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
    @NotBlank(message = "error.supply.id.notset")
    @NotNull(message = "error.supply.id.notset")
    private String id;

    @Schema(description = "Control Date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotBlank(message = "error.supply.date.notset")
    @NotNull(message = "error.supply.date.notset")
    @PastOrPresent(message = "error.supply.date.future")
    private LocalDate date;

    @Schema(description = "Checked Item")
    @NotBlank(message = "error.supply.item.notset")
    @NotNull(message = "error.supply.item.notset")
    private String item;

    @Schema(description = "Invoice")
    @NotBlank(message = "error.supply.invoice.notset")
    @NotNull(message = "error.supply.invoice.notset")
    private boolean invoice;

    @Schema(description = "Packaging Status")
    @NotBlank(message = "error.supply.packaging.notset")
    @NotNull(message = "error.supply.packaging.notset")
    private boolean packaging;

    @Schema(description = "Warranty Status")
    @NotBlank(message = "error.supply.warranty.notset")
    @NotNull(message = "error.supply.warranty.notset")
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
