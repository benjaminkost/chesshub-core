package management;

import java.security.*;

public class StringEncrypter {

    /**
     * Returns the SHA-256 hash of the given string.
     *
     * @param string the string to be encrypted
     * @return the SHA-256 hash of the given string
     * @throws RuntimeException if the SHA-256 algorithm is not available
     *
     * @author Lukas Zander
     */
    public static String encryptString(String string) {
        try {
            // Create MessageDigest object for SHA-256 hashing
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Apply SHA-256 hashing
            byte[] hashedPassword = digest.digest(string.getBytes());

            // hashed string -> hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedPassword) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    // single-digit numbers -> number + leading zero
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encrypting string: " + e.getMessage());
        }
    }
}