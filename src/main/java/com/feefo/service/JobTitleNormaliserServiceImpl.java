package com.feefo.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

public class JobTitleNormaliserServiceImpl implements JobTitleNormaliserService {

    private final Set<String> acceptedJobTitles;

    public JobTitleNormaliserServiceImpl(final Set<String> acceptedJobTitles) {
        this.acceptedJobTitles = Set.copyOf(acceptedJobTitles);
    }

    @Override
    public String normalise(final String jobTitle) {
        return acceptedJobTitles
                .stream()
                .min(Comparator.comparingInt(acceptedJobTitle -> calculateLevenshteinDistance(jobTitle, acceptedJobTitle)))
                .orElse(null);
    }

    // Take two strings and calculate the Levenshtein distance between them
    // The more similar the strings, the lower the score
    private static int calculateLevenshteinDistance(
            final String lhs, final String rhs
    ) {
        // store intermediate results in a lookup table for later use
        // [i][j] will store the distance between lhs[:i] and rhs[:j]
        final int[][] lookupTable = new int[lhs.length() + 1][rhs.length() + 1];

        for (int i = 0; i <= lhs.length(); i++) {
            for (int j = 0; j <= rhs.length(); j++) {
                if (i == 0) {
                    lookupTable[i][j] = j;
                } else if (j == 0) {
                    lookupTable[i][j] = i;
                } else {
                    final int substitution = lookupTable[i - 1][j - 1]
                            + costOfSubstitution(lhs.charAt(i - 1), rhs.charAt(j - 1));
                    final int insertion = lookupTable[i - 1][j] + 1;
                    final int deletion = lookupTable[i][j - 1] + 1;
                    lookupTable[i][j] = min(
                            substitution, insertion, deletion
                    );
                }
            }
        }
        return lookupTable[lhs.length()][rhs.length()];
    }

    private static int costOfSubstitution(final char a, final char b) {
        return a == b ? 0 : 1;
    }

    private static int min(final int... numbers) {
        return Arrays
                .stream(numbers)
                .min()
                .orElse(Integer.MAX_VALUE);
    }
}