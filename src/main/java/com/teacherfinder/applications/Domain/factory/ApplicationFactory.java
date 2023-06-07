package com.teacherfinder.applications.domain.factory;

import org.springframework.stereotype.Component;

import com.teacherfinder.applications.domain.model.aggregate.Application;
import com.teacherfinder.applications.domain.model.entity.ApplicationApplicantProfile;
import com.teacherfinder.applications.domain.model.valueObjects.ApplicationId;

@Component
public class ApplicationFactory {
    
    public Application createApplication(Long applicantId, Long jobOfferId, String status, ApplicationApplicantProfile profile){

        ApplicationId applicationId = new ApplicationId(applicantId, jobOfferId);

        return new Application(applicationId,status,profile);
    }

}
