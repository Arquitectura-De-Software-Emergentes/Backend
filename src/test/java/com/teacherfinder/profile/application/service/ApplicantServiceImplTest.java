package com.teacherfinder.profile.application.service;

import com.teacherfinder.profile.domain.factory.ApplicantProfileFactory;
import com.teacherfinder.profile.domain.model.entity.ApplicantProfile;
import com.teacherfinder.profile.domain.model.entity.JobExperienceInformation;
import com.teacherfinder.profile.domain.model.valueObjects.AcademicInformation;
import com.teacherfinder.profile.domain.model.valueObjects.ContactInformation;
import com.teacherfinder.profile.domain.model.valueObjects.CurriculumVitae;
import com.teacherfinder.profile.domain.model.valueObjects.PersonalInformation;
import com.teacherfinder.profile.domain.repository.ApplicantProfileRepository;
import com.teacherfinder.profile.domain.repository.JobExperienceInformationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.Validator;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

class ApplicantServiceImplTest {

    /*@Mock
    private ApplicantRepository applicantRepository;*/

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

    /*@Test
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
    void uploadCV() {
        Long applicantId = 1L;
        CurriculumVitae cv = new CurriculumVitae();
        cv.setCv("testFileName.pdf");

        Applicant existingApplicant = new Applicant();
        existingApplicant.setApplicantId(applicantId);

        when(applicantRepository.findById(applicantId)).thenReturn(Optional.of(existingApplicant));
        when(applicantRepository.save(any(Applicant.class))).thenAnswer(i -> i.getArguments()[0]);

        // Call method to test
        CurriculumVitae result = applicantService.uploadCV(applicantId, cv);

        // Verify results
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
    }*/

    @Test
    void updateProfile() {
        Long applicantId = 1L;
        ApplicantProfile profile = new ApplicantProfile();
        profile.setAcademicInformation(new AcademicInformation("A","B","C"));
        profile.setContactInformation( new ContactInformation("021831","bezos@gmail.com","99823712"));
        profile.setPersonalInformation(new PersonalInformation());

        ApplicantProfile existingProfile = new ApplicantProfile();
        existingProfile.setApplicantProfileId(applicantId);

        when(validator.validate(profile)).thenReturn(Collections.emptySet());
        when(profileRepository.findById(applicantId)).thenReturn(Optional.of(existingProfile));
        when(profileRepository.save(any(ApplicantProfile.class))).thenAnswer(i -> i.getArguments()[0]);

        // Call method to test
        ApplicantProfile result = applicantService.updateProfile(applicantId, profile);

        // Verify results
        assertEquals(profile.getAcademicInformation(), result.getAcademicInformation());
        assertEquals(profile.getContactInformation(), result.getContactInformation());
        assertEquals(profile.getPersonalInformation(), result.getPersonalInformation());
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