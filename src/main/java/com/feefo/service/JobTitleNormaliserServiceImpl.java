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

    private static int calculateLevenshteinDistance(
            final String lhs, final String rhs
    ) {
        final int[][] dp = new int[lhs.length() + 1][rhs.length() + 1];

        for (int i = 0; i <= lhs.length(); i++) {
            for (int j = 0; j <= rhs.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    final int substitutionCost = costOfSubstitution(lhs.charAt(i - 1), rhs.charAt(j - 1));
                    dp[i][j] = min(
                            dp[i - 1][j - 1] + substitutionCost,
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1
                    );
                }
            }
        }
        return dp[lhs.length()][rhs.length()];
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