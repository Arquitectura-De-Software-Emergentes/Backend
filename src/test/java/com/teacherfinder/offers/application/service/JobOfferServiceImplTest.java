package com.teacherfinder.offers.application.service;

import com.teacherfinder.applications.api.internal.ApplicationFacade;
import com.teacherfinder.offers.domain.model.Enum.*;
import com.teacherfinder.offers.domain.model.Enum.Currency;
import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.domain.model.entity.PositionProfile;
import com.teacherfinder.offers.domain.model.valueObjects.Course;
import com.teacherfinder.offers.domain.model.valueObjects.Money;
import com.teacherfinder.offers.domain.repository.JobOfferRepository;
import com.teacherfinder.offers.domain.repository.PositionProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Validator;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

class JobOfferServiceImplTest {
    @Mock
    private Validator validator;

    @Mock
    private JobOfferRepository jobOfferRepository;

    @Mock
    private PositionProfileRepository positionProfileRepository;

    @Mock
    private ApplicationFacade applicationFacade;

    @InjectMocks
    private JobOfferServiceImpl jobOfferService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createJobOffer() {
        JobOffer request = new JobOffer();
        request.setId(0L);
        request.setRecruiterId(0L);
        request.setTitle("string");
        request.setDescription("string");
        request.setInitialDate(new Date());
        request.setEndDate(new Date());
        Money salary = new Money();
        salary.setMount((double) 0);
        salary.setCurrency(Currency.valueOf("PEN"));
        request.setSalary(salary);
        request.setMaxApplications(0l);
        request.setNumberApplications(0l);
        request.setAvailability(Availability.valueOf("AVAILABLE"));

        when(jobOfferRepository.save(request)).thenReturn(request);

        JobOffer result = jobOfferService.createJobOffer(request);

        assertEquals(request, result);
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
    void updatePositionProfile() {
        Long positionProfileId = 1L;
        PositionProfile request = new PositionProfile();
        request.setName("testName");
        request.setCourse(new Course("programming"));
        request.setExperience(Experience.NONE);
        request.setModality(Modality.VIRTUAL);
        request.setType(Type.FULL_TIME);

        PositionProfile existingProfile = new PositionProfile();
        existingProfile.setId(positionProfileId);

        when(positionProfileRepository.findById(positionProfileId)).thenReturn(Optional.of(existingProfile));
        when(positionProfileRepository.save(any(PositionProfile.class))).thenAnswer(i -> i.getArguments()[0]);

        PositionProfile result = jobOfferService.updatePositionProfile(positionProfileId, request);

        assertEquals(request.getName(), result.getName());
        assertEquals(request.getCourse(), result.getCourse());
        assertEquals(request.getExperience(), result.getExperience());
        assertEquals(request.getModality(), result.getModality());
        assertEquals(request.getType(), result.getType());
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
    }
}