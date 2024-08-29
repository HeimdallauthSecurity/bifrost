package com.heimdallauth.auth.bifrost.constants;

public enum MailType {
    PROFILE_VERIFICATION("Verify your email address"),
    PASSWORD_RESET("Reset your password");

    public final String subject;

    MailType(String subject) {
        this.subject = subject;
    }
}
