package ru.innopolis.uni.course3.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 */
public interface PasswordAuthentication {

    PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    /**
     *  Gets the random salt
     *
     *  @return String  salt in hex
     */
    String getSalt();

    /**
     *  Hashes and salts password
     *
     *  @return String  hashed password in hex
     */
    String generateStrongPasswordHash(String password, String salt);

    /**
     *  Checks accordance of the password and the stored hash
     *
     *  @return boolean  true - if password is in accordance of the stored hash, false - otherwise
     */
    boolean isExpectedPassword(String password, String salt, String storedPasswordHash);

    /**
     *  Gets the random password
     *
     *  @return String  random password
     */
    String generateRandomPassword(int length);

    static PasswordEncoder getPasswordEncoder() {
        return PASSWORD_ENCODER;
    }

}
