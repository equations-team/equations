package common;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

import static java.util.Objects.hash;

public class Cryptographer {
    private static Logger LOGGER = LoggerFactory.getLogger(Cryptographer.class);

    public static Optional<byte[]> generateSalt(int length) {
        try {
            return Optional.of(SecureRandom.getInstance("SHA1PRNG").generateSeed(length));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage() + '\n' + e.getStackTrace());
            return Optional.empty();
        }
    }
    public static String getSaltedHash(String password, byte[]salt) {
        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
    }
}
