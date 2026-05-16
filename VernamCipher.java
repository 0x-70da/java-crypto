import java.util.Base64;

public class VernamCipher {
    /**
     * Encrypts plaintext using the Vernam Cipher (XOR-based stream cipher)
     * @param plaintext The text to encrypt
     * @param key The encryption key (should be same length as plaintext)
     * @return The encrypted ciphertext (Base64 encoded for safe transmission)
     */
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();

        // Ensure key is at least as long as plaintext, repeat if necessary
        String expandedKey = expandKey(plaintext, key);

        for (int i = 0; i < plaintext.length(); i++) {
            // XOR operation: plaintext character XOR key character
            char encryptedChar = (char) (plaintext.charAt(i) ^ expandedKey.charAt(i));
            ciphertext.append(encryptedChar);
        }

        // Encode to Base64 for safe transmission and display
        return Base64.getEncoder().encodeToString(ciphertext.toString().getBytes());
    }

    /**
     * Decrypts ciphertext using the Vernam Cipher
     * Decryption is the same as encryption in Vernam Cipher (XOR property)
     * @param ciphertext The Base64 encoded ciphertext to decrypt
     * @param key The decryption key (must be the same key used for encryption)
     * @return The decrypted plaintext
     */
    public static String decrypt(String ciphertext, String key) {
        // Decode from Base64
        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);
        String decodedCiphertext = new String(decodedBytes);

        // XOR operation again to decrypt (XOR is self-inverse)
        StringBuilder plaintext = new StringBuilder();
        String expandedKey = expandKey(decodedCiphertext, key);

        for (int i = 0; i < decodedCiphertext.length(); i++) {
            char decryptedChar = (char) (decodedCiphertext.charAt(i) ^ expandedKey.charAt(i));
            plaintext.append(decryptedChar);
        }

        return plaintext.toString();
    }

    /**
     * Expands the key to match the length of the plaintext by repeating it
     * @param plaintext The plaintext whose length determines the required key length
     * @param key The base key to expand
     * @return The expanded key
     */
    private static String expandKey(String plaintext, String key) {
        StringBuilder expandedKey = new StringBuilder();
        int plaintextLength = plaintext.length();
        
        for (int i = 0; i < plaintextLength; i++) {
            expandedKey.append(key.charAt(i % key.length()));
        }
        
        return expandedKey.toString();
    }
}
