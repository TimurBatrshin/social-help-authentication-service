package com.example.socialhelp.util;

public interface MailsGenerator {
    String getMailForConfirm(String serverUrl, String code);
}
