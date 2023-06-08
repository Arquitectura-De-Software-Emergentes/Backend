package com.teacherfinder.applications.domain.service;

import com.teacherfinder.applications.domain.model.aggregate.Application;
import com.teacherfinder.applications.domain.model.valueObjects.ApplicationId;

public interface ApplicationService {
    Application apply(ApplicationId applicationId);
}
