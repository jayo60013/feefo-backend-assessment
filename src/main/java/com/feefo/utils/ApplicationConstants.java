package com.feefo.utils;

import java.util.Set;

public final class ApplicationConstants {
    private ApplicationConstants() {
        //Utility classes cannot be instantiated
    }

    public final static Set<String> ACCEPTED_JOB_TITLES = Set.of(
            "Architect",
            "Software engineer",
            "Quantity surveyor",
            "Accountant"
    );
}
