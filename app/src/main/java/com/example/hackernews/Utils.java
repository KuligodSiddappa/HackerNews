package com.example.hackernews;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Utils {
    public static final int SPORTS = 0;
    public static final int BOLLYWOOD = 1;
    public static final int POLITICS = 2;
    public static final int ART = 3;

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     * <p>
     * Copied from <a href="https://git.io/v5CeL">Guava Library</a>
     *
     * @param reference    an object reference
     * @param errorMessage the exception message to use if the check fails; will be converted to a
     *                     string using {@link String#valueOf(Object)}
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }
}
