package com.javadevjournal.service;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

import com.javadevjournal.domain.AbstractEmailContext;

public interface EmailService {

void sendMail(AbstractEmailContext email) throws MessagingException;
void sendSimpleEmail(final String toAddress, final String subject, final String message);
void sendEmailWithAttachment(final String toAddress, final String subject, final String message, final String attachment) throws MessagingException, FileNotFoundException;

}
