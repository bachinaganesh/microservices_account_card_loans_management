package com.eazybytes.accounts.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
    name = "CustomerRequest",
    description = "Customer Resquest DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @Schema(
        name = "name", defaultValue = "ganesh"
    )
    @NotBlank(message = "Name shouldn't be empty")
    @Size(min = 5, max = 20, message = "Name should be atleast 5 characters to upto 20")
    private String name;

    @Schema(
        name = "email",
        defaultValue = "ganesh@gmail.com"
    )
    @NotBlank(message = "Email shouldn't be empty")
    @Email
    private String mail;

    @Schema(
        name = "gender",
        defaultValue = "male"
    )
    @NotBlank(message = "Gender shouldn't be empty")
    private String gender;

    @Schema(
        name = "mobileNumber",
        defaultValue = "8688638878"
    )
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number should be 10 numbers")
    private String mobileNumber;

    @Schema(
        name = "account"
    )
    @Valid
    @NotNull(message = "Account request shouldn't be empty")
    private AccountRequest accountRequest;
}
