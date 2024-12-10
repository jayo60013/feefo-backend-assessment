package com.feefo;

import com.feefo.service.JobTitleNormaliserServiceImpl;

import static com.feefo.utils.ApplicationConstants.ACCEPTED_JOB_TITLES;

public class Main {
    public static void main(final String[] args) {
        //Sample usage
        final JobTitleNormaliserServiceImpl jobTitleNormaliserService = new JobTitleNormaliserServiceImpl(
                ACCEPTED_JOB_TITLES
        );

        final String javaEngineer = "Java engineer";
        final String normalisedJavaEngineer = jobTitleNormaliserService.normalise(javaEngineer);
        assert "Software engineer".equals(normalisedJavaEngineer);

        final String CSharpEngineer = "C# engineer";
        final String normalisedCSharpEngineer = jobTitleNormaliserService.normalise(CSharpEngineer);
        assert "Software engineer".equals(normalisedCSharpEngineer);

        final String chiefAccountant = "Chief Accountant";
        final String normalisedChiefAccount = jobTitleNormaliserService.normalise(chiefAccountant);
        assert "Accountant".equals(normalisedChiefAccount);
    }
}