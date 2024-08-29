package com.heimdallauth.auth.bifrost.constants;

public enum MailType {
    PROFILE_VERIFICATION("Verify your email address","profile-verification"),
    PASSWORD_RESET("Reset your password","password-reset");

    public final String subject;
    public final String templateName;

    MailType(String subject, String templateName) {
        this.subject = subject;
        this.templateName = templateName;
    }
}
