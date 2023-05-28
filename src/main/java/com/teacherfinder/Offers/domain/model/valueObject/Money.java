package com.teacherfinder.Offers.domain.model.valueObject;

import com.teacherfinder.Offers.domain.model.Enum.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Embeddable
@Getter
@Setter
public class Money {
    @NotNull
    private Double mount ;

    @NotNull
    @NotBlank
    private Currency currency;
}
