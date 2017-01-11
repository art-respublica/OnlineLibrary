package ru.innopolis.uni.course3.service;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

/**
 *  Contains a set of methods of working with password: hashing, salting and password checking
 */
@Service
public final class PasswordAuthentication {

    private final String algorithm = "PBKDF2WithHmacSHA1";
    private final Random secureRandom = new SecureRandom(); //Always use a SecureRandom generator
    private final int iterations = 10000;
    private final int keyLength = 256;

    public PasswordAuthentication() {
    }

    /**
     *  Gets the random salt
     *
     *  @return String  salt in hex
     */
    public String getSalt()
    {
        byte[] salt = new byte[16];    //Create array for salt
        secureRandom.nextBytes(salt);  //Get a random salt
        return toHex(salt);
    }

    /**
     *  Hashes and salts password
     *
     *  @return String  hashed password in hex
     */
    public String generateStrongPasswordHash(String password, String salt)
    {
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = fromHex(salt);
        PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
        Arrays.fill(passwordChars, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            return toHex(skf.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            throw new AssertionError("Error while hashing a password: " + exception.getMessage(), exception);
        } finally {
            spec.clearPassword();
        }
    }

    /**
     *  Checks accordance of the password and the stored hash
     *
     *  @return boolean  true - if password is in accordance of the stored hash, false - otherwise
     */
    public boolean isExpectedPassword(String password, String salt, String storedPasswordHash)
    {
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = fromHex(salt);
        byte[] hash = fromHex(storedPasswordHash);
        PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            byte[] testHash = skf.generateSecret(spec).getEncoded();
            int diff = hash.length ^ testHash.length;
            for (int i = 0; i < hash.length && i < testHash.length; i++) {
                diff |= hash[i] ^ testHash[i];
            }
            return diff == 0;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            throw new AssertionError("Error while hashing a password: " + exception.getMessage(), exception);
        } finally {
            spec.clearPassword();
        }
    }

    /**
     *  Gets the random password
     *
     *  @return String  random password
     */
    public String generateRandomPassword(int length)
    {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int c = secureRandom.nextInt(62);
            if (c <= 9) {
                sb.append(String.valueOf(c));
            } else if (c < 36) {
                sb.append((char) ('a' + c - 10));
            } else {
                sb.append((char) ('A' + c - 36));
            }
        }
        return sb.toString();
    }

    private byte[] fromHex(String hex)
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++) {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    private String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
}
