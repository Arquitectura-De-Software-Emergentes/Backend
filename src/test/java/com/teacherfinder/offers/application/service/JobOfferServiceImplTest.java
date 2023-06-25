package com.teacherfinder.offers.application.service;

import com.teacherfinder.offers.domain.factory.PositionProfileFactory;
import com.teacherfinder.offers.domain.model.Enum.*;
import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.domain.model.entity.PositionProfile;
import com.teacherfinder.offers.domain.repository.JobOfferRepository;
import com.teacherfinder.offers.domain.repository.PositionProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Validator;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)class JobOfferServiceImplTest {
    @Mock
    private Validator validator;

    @Mock
    private JobOfferRepository jobOfferRepository;

    @Mock
    private PositionProfileRepository positionProfileRepository;

    /*@Mock
    private ApplicationFacade applicationFacade;*/

    @InjectMocks
    private JobOfferServiceImpl jobOfferService;

     @Mock
    private PositionProfileFactory  profileFactory;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateJobOffer() {
        // given
        JobOffer request = new JobOffer();
        PositionProfile positionProfile = new PositionProfile();
        when(profileFactory.createPositionProfile()).thenReturn(positionProfile);
        when(positionProfileRepository.save(positionProfile)).thenReturn(positionProfile);
        when(jobOfferRepository.save(request.withPositionProfile(positionProfile))).thenReturn(request);
    }

    @Test
    void getJobOfferById() {
        Long jobOfferId = 1L;
        JobOffer expectedJobOffer = new JobOffer();
        expectedJobOffer.setId(jobOfferId);

        when(jobOfferRepository.findById(jobOfferId)).thenReturn(Optional.of(expectedJobOffer));

        JobOffer result = jobOfferService.getJobOfferById(jobOfferId);

        assertEquals(expectedJobOffer, result);
    }


    @Test
    void getAllJobOffer() {
        List<JobOffer> expectedJobOffers = new ArrayList<>();
        expectedJobOffers.add(new JobOffer());
        expectedJobOffers.add(new JobOffer());

        when(jobOfferRepository.findAll()).thenReturn(expectedJobOffers);

        List<JobOffer> result = jobOfferService.getAllJobOffer();

        assertEquals(expectedJobOffers, result);
    }

    @Test
    void getAllJobOfferByRecruiterId() {
        Long recruiterId = 1L;
        List<JobOffer> expectedJobOffers = new ArrayList<>();
        expectedJobOffers.add(new JobOffer());
        expectedJobOffers.add(new JobOffer());

        when(jobOfferRepository.findByRecruiterId(recruiterId)).thenReturn(expectedJobOffers);

        List<JobOffer> result = jobOfferService.getAllJobOfferByRecruiterId(recruiterId);

        assertEquals(expectedJobOffers, result);
    }

    @Test
    void testUpdatePositionProfile() {
        // given
        Long positionProfileId = 1L;
        PositionProfile request = new PositionProfile();
        PositionProfile profile = new PositionProfile();
        when(positionProfileRepository.findById(positionProfileId)).thenReturn(Optional.of(profile));
        when(positionProfileRepository.save(any(PositionProfile.class))).thenReturn(request);

        // when
        PositionProfile result = jobOfferService.updatePositionProfile(positionProfileId, request);

        // then
        verify(positionProfileRepository).findById(positionProfileId);
        verify(positionProfileRepository).save(any(PositionProfile.class));
        assertEquals(request, result);
    }




    @Test
    void enable() {
        Long jobOfferId = 1L;
        JobOffer existingJobOffer = new JobOffer();
        existingJobOffer.setId(jobOfferId);
        existingJobOffer.setAvailability(Availability.UNAVAILABLE);

        when(jobOfferRepository.findById(jobOfferId)).thenReturn(Optional.of(existingJobOffer));
        when(jobOfferRepository.save(any(JobOffer.class))).thenAnswer(i -> i.getArguments()[0]);

        ResponseEntity<String> result = jobOfferService.enable(jobOfferId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("The offer has been enabled", result.getBody());
    }

    @Test
    void disable() {
        Long jobOfferId = 1L;
        JobOffer existingJobOffer = new JobOffer();
        existingJobOffer.setId(jobOfferId);
        existingJobOffer.setAvailability(Availability.AVAILABLE);

        when(jobOfferRepository.findById(jobOfferId)).thenReturn(Optional.of(existingJobOffer));
        when(jobOfferRepository.save(any(JobOffer.class))).thenAnswer(i -> i.getArguments()[0]);

        ResponseEntity<String> result = jobOfferService.disable(jobOfferId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("The offer has been disabled", result.getBody());
    }
/* 
    @Test
    void apply() {
        Long jobOfferId = 1L;
        Long applicantId = 2L;
        JobOffer existingJobOffer = new JobOffer();
        existingJobOffer.setId(jobOfferId);
        existingJobOffer.setAvailability(Availability.AVAILABLE);
        existingJobOffer.setMaxApplications(10L);
        existingJobOffer.setNumberApplications(5L);

        when(jobOfferRepository.findById(jobOfferId)).thenReturn(Optional.of(existingJobOffer));
        when(applicationFacade.apply(jobOfferId, applicantId)).thenReturn(new ResponseEntity<>("Application successful", HttpStatus.OK));
        when(jobOfferRepository.save(any(JobOffer.class))).thenAnswer(i -> i.getArguments()[0]);

        ResponseEntity<String> result = jobOfferService.apply(jobOfferId, applicantId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Application successful", result.getBody());
    }*/
}