package hu.yokudlela.haccp.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This class represents a single control of the storage facility.
 * @author csabakoos
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class WasteControl {
    private String id;
    private LocalDateTime date;
    private Integer amount;
    private boolean dumped;

    @Builder
    public WasteControl(String id, LocalDateTime date, Integer amount, boolean dumped) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.dumped = dumped;
    }
}
