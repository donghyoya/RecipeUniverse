package com.recipe.universe.domain.user.oauth2.exception;

public class UnSupportedProviderException extends RuntimeException{
    private static String format = "The provider '%s' is not supported.";
    public UnSupportedProviderException(String provider) {
        super(String.format(format, provider));
    }
}
