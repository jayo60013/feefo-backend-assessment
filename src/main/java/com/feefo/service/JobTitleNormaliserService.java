package com.feefo.service;

public interface JobTitleNormaliserService {

    /**
     * Converts a job title to one of the predefined job titles
     *
     * @param jobTitle input job title
     * @return a job title from the accepted job title set
     */
    String normalise(final String jobTitle);
}
