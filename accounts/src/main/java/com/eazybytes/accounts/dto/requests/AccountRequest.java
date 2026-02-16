package com.eazybytes.accounts.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
    name = "AccountRequest",
    description = "Account Request DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @Schema(
        name = "type",
        defaultValue = "Savings"
    )
    @NotBlank(message = "Account type shouldn't be empty")
    private String type;

    @Schema(
        name = "branch",
        defaultValue = "SBI"
    )
    @NotBlank(message = "Branch shouldn't be empty")
    private String branch;

    @Schema(
        name = "balance",
        defaultValue = "1000"
    )
    @NotNull(message = "Balance shouldn't be empty")
    @PositiveOrZero(message = "Balance must be either zero or positive number")
    private Double balance;
}
