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
public class StorageControl {
    private String id;
    private LocalDateTime date;
    private String item;
    private boolean packaging;
    private boolean conditions;
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


