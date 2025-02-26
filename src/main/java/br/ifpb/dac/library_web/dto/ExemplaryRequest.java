package br.ifpb.dac.library_web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExemplaryRequest {

    @NotNull(message = "The number of copies is required")
    private Integer numberExemplary;

    @NotNull(message = "The book ID is required")
    private Long bookId;
}
