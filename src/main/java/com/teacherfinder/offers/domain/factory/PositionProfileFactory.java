package com.teacherfinder.offers.domain.factory;

import com.teacherfinder.offers.domain.model.Enum.Experience;
import com.teacherfinder.offers.domain.model.Enum.Modality;
import com.teacherfinder.offers.domain.model.Enum.Type;
import com.teacherfinder.offers.domain.model.entity.PositionProfile;
import com.teacherfinder.offers.domain.model.valueObjects.Course;
import org.springframework.stereotype.Component;

@Component
public class PositionProfileFactory {
    private static final String EMPTY_STRING = "position profile";
    public PositionProfile createPositionProfile(){
        Course course = new Course(EMPTY_STRING);
        return new PositionProfile(null, EMPTY_STRING,course, Modality.ON_SITE, Experience.NONE,Type.FULL_TIME);
    }
}
