package com.efostach.employeesmanager.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class TwilioSmsVerification {

    public static final String ACCOUNT_SID = "ACd0aa135af494cd834baf9632973831ec";
    public static final String AUTH_TOKEN = "235dcec71001ddef977000fb89243139";
    public static final String TWILIO_FROM_PHONE_NUMBER = "+14057087785";

    public String sentMsg(String phoneNumber) {
        String sendCode = generateVerificationCode();
        log.info("Verification Code: {}", sendCode);

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(phoneNumber), //to
                new PhoneNumber(TWILIO_FROM_PHONE_NUMBER), //from
                sendCode)
                .create();
        log.info("SID: {}", message.getSid());
        return sendCode;
    }

    private String generateVerificationCode() {
        int length = 4;
        Random random = new Random();
        // 48 (unicode for 0),  57 (unicode for 9) -> the digits 0-9
        return random.ints(48, 57)
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
