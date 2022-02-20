package hu.yokudlela.haccp.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This class represents a single control of an incoming supply.
 * @author csabakoos
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class SupplyControl {
    private String id;
    private LocalDateTime date;
    private String item;
    private boolean invoice;
    private boolean packaging;
    private boolean warranty;

    @Builder
    public SupplyControl(String id, LocalDateTime date, String item, boolean invoice, boolean packaging, boolean warranty) {
        this.id = id;
        this.date = date;
        this.item = item;
        this.invoice = invoice;
        this.packaging = packaging;
        this.warranty = warranty;
    }
}
