package com.teacherfinder.assessment.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoPresentation implements Activity{

    private Long id;
    private Double duration;
    private Double Score;
    private String videoUrl;
    
    @Override
    public Boolean hasPassed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasPassed'");
    }
    
}
