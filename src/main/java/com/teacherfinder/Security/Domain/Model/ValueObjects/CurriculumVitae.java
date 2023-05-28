package com.teacherfinder.Security.Domain.Model.ValueObjects;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CurriculumVitae {

    private String title;

    @Lob
    private Byte[] cv;
}
