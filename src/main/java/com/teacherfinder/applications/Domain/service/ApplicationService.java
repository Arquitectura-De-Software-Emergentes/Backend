package com.teacherfinder.applications.domain.service;

import com.teacherfinder.applications.application.dto.ApplyResource;
import com.teacherfinder.applications.domain.model.aggregate.Application;

public interface ApplicationService {
    Application apply(ApplyResource applyResource);
}
