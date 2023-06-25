package com.teacherfinder.profile.application.service;

import com.teacherfinder.profile.domain.factory.ApplicantProfileFactory;
import com.teacherfinder.profile.domain.model.aggregate.Applicant;
import com.teacherfinder.profile.domain.model.entity.ApplicantProfile;
import com.teacherfinder.profile.domain.model.entity.JobExperienceInformation;
import com.teacherfinder.profile.domain.model.valueObjects.AcademicInformation;
import com.teacherfinder.profile.domain.model.valueObjects.ContactInformation;
import com.teacherfinder.profile.domain.model.valueObjects.CurriculumVitae;
import com.teacherfinder.profile.domain.model.valueObjects.PersonalInformation;
import com.teacherfinder.profile.domain.repository.ApplicantProfileRepository;
import com.teacherfinder.profile.domain.repository.ApplicantRepository;
import com.teacherfinder.profile.domain.repository.JobExperienceInformationRepository;
import com.teacherfinder.shared.exception.ResourceValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ApplicantServiceImplTest {

    @Mock
    private ApplicantRepository applicantRepository;

    @Mock
    private ApplicantProfileRepository profileRepository;

    @Mock
    private JobExperienceInformationRepository experienceRepository;

    @Mock
    private ApplicantProfileFactory profileFactory;

    @Mock
    private Validator validator;

    @InjectMocks
    private ApplicantServiceImpl applicantService;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {
        Applicant applicant = new Applicant();
        applicant.setApplicantId(1L);
        applicant.setUsername("testFirstName");
        applicant.setPassword("123413");

        when(validator.validate(applicant)).thenReturn(Collections.emptySet());
        when(applicantRepository.save(applicant)).thenReturn(applicant);

        // Call method to test
        Applicant result = applicantService.register(applicant);

        // Verify results
        assertEquals(applicant, result);
    }

    @Test
    void testUploadCV() {
        Long applicantId = 1L;
        CurriculumVitae cv = new CurriculumVitae();
        Applicant applicant = new Applicant();
        when(applicantRepository.findById(applicantId)).thenReturn(Optional.of(applicant));
        when(applicantRepository.save(applicant)).thenReturn(applicant);
        CurriculumVitae result = applicantService.uploadCV(applicantId, cv);
        assertEquals(cv, result);
    }


    @Test
    void getCv() {
        Long applicantId = 1L;
        CurriculumVitae cv = new CurriculumVitae();
        cv.setCv("testFileName.pdf");

        Applicant existingApplicant = new Applicant();
        existingApplicant.setApplicantId(applicantId);
        existingApplicant.setCv(cv);

        when(applicantRepository.findById(applicantId)).thenReturn(Optional.of(existingApplicant));

        // Call method to test
        CurriculumVitae result = applicantService.getCv(applicantId);

        // Verify results
        assertEquals(cv, result);
    }

    @Test
    void testUpdateProfileWithInvalidProfile() {
        // given
        Long applicantId = 1L;
        ApplicantProfile profile = new ApplicantProfile();
        Set<ConstraintViolation<ApplicantProfile>> violations = new HashSet<>();
        violations.add(mock(ConstraintViolation.class));
        when(validator.validate(profile)).thenReturn(violations);

        // when / then
        assertThrows(ResourceValidationException.class, () -> applicantService.updateProfile(applicantId, profile));
    }

    @Test
    void addJobExperience() {
        JobExperienceInformation experience = new JobExperienceInformation();
        experience.setJobExperienceInformationId(1L);
        experience.setCompany("testCompanyName");
        experience.setPosition("testPosition");
        when(validator.validate(experience)).thenReturn(Collections.emptySet());
        when(experienceRepository.save(experience)).thenReturn(experience);
        JobExperienceInformation result = applicantService.addJobExperience(experience);
        assertEquals(experience, result);
    }

    @Test
    void getApplicantProfile() {
        Long applicantId = 1L;
        ApplicantProfile expectedProfile = new ApplicantProfile();
        expectedProfile.setApplicantProfileId(applicantId);
        expectedProfile.setAcademicInformation(new AcademicInformation("math","idk","jorge"));
        expectedProfile.setContactInformation( new ContactInformation("021831","bezos@gmail.com","99823712"));
        expectedProfile.setPersonalInformation(new PersonalInformation());

        when(profileRepository.findById(applicantId)).thenReturn(Optional.of(expectedProfile));

        ApplicantProfile result = applicantService.getApplicantProfile(applicantId);

        assertEquals(expectedProfile, result);
    }


}