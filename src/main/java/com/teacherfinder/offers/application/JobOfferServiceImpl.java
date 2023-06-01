package com.teacherfinder.offers.application;

import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.domain.model.aggregate.PositionProfile;
import com.teacherfinder.offers.domain.model.repository.JobOfferRepository;
import com.teacherfinder.offers.domain.model.repository.PositionProfileRepository;
import com.teacherfinder.offers.domain.model.services.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;
import java.util.Optional;
@Service
public class JobOfferServiceImpl implements JobOfferService {

   @Autowired
    private  JobOfferRepository jobOfferRepository;
    @Autowired
    PositionProfileRepository positionProfileRepository;
    private  Validator validator;

    public JobOffer createJobOffer(JobOffer jobOffer) {
        PositionProfile positionProfile = jobOffer.getPositionProfile();

        if (positionProfile != null && positionProfile.getId() != null) {
            Long positionProfileId = positionProfile.getId();
            if (!ifExistsPositionProfile(positionProfileId)) {
               PositionProfile positionProfileCreated =  this.createPositionProfile(new PositionProfile());
               jobOffer.setPositionProfile(positionProfileCreated);
            }
        }

        return jobOfferRepository.save(jobOffer);
    }
    @Override
    public JobOffer getJobOfferById(Long id) {
        Optional<JobOffer> optionalJobOffer = this.jobOfferRepository.findById(id);
        return optionalJobOffer.orElse(null);
    }

    @Override
    public PositionProfile createPositionProfile(PositionProfile positionProfile) {
        return this.positionProfileRepository.save(positionProfile);
    }

    @Override
    public PositionProfile getPositionProfileById(Long id) {
        return null;
    }

    @Override
    public boolean ifExistsPositionProfile(Long id) {
        return positionProfileRepository.existsById(id);
    }

}
