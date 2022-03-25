package hu.yokudlela.haccp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
@Schema(description = "Waste")
@Entity
@javax.persistence.Table(name = "rwaste")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WasteControl implements Serializable {

    @Schema(description = "Control ID")
    @NotBlank(message = "error.waste.id.notset")
    @NotNull(message = "error.waste.id.notset")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Schema(description = "Control Date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotBlank(message = "error.waste.date.notset")
    @NotNull(message = "error.waste.date.notset")
    @PastOrPresent(message = "error.waste.date.future")
    private LocalDate date;

    @Schema(description = "Waste Amount")
    @NotBlank(message = "error.waste.amount.notset")
    @NotNull(message = "error.waste.amount.notset")
    private Integer amount;

    @Schema(description = "Dumped Status")
    @NotBlank(message = "error.waste.dumped.notset")
    @NotNull(message = "error.waste.dumped.notset")
    private boolean dumped;

    @Builder
    public WasteControl(String id, LocalDate date, Integer amount, boolean dumped) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.dumped = dumped;
    }
}
