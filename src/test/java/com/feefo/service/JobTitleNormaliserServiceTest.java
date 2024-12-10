package com.feefo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.feefo.utils.ApplicationConstants.ACCEPTED_JOB_TITLES;
import static org.assertj.core.api.Assertions.assertThat;

public class JobTitleNormaliserServiceTest {

    private final JobTitleNormaliserServiceImpl jobTitleNormaliserService =
            new JobTitleNormaliserServiceImpl(ACCEPTED_JOB_TITLES);

    @ParameterizedTest
    @CsvSource({
            "Architect,Architect",
            "Software engineer,Software engineer",
            "Quantity surveyor,Quantity surveyor",
            "Accountant,Accountant",
    })
    @DisplayName("Test exact job title matches")
    void testNormaliseStringWhenInputMatchesJobTitle(
            final String inputJob,
            final String expectedJob
    ) {
        // When
        final String actualJob = jobTitleNormaliserService.normalise(inputJob);

        // Then
        assertThat(actualJob).isEqualTo(expectedJob);
    }

    @ParameterizedTest
    @CsvSource({
            "Java engineer,Software engineer",
            "C# engineer,Software engineer",
            "Junior accountant,Accountant",
            "Chief Accountant,Accountant",
    })
    @DisplayName("test almost matches accepted job title")
    void testNormaliseStringWhenInputDoesNotMatchJobTitle(
            final String inputJob,
            final String expectedJob
    ) {
        // When
        final String actualJob = jobTitleNormaliserService.normalise(inputJob);

        // Then
        assertThat(actualJob).isEqualTo(expectedJob);
    }
}