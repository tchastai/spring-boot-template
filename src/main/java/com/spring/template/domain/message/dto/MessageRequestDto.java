package com.spring.template.domain.message.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestDto {
    @NotNull(message = "You Have to Specify a price")
    private String content;
}
