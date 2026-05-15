public class Main {
    public static void main(String[] args) {
        TranspositionCipher transpositionCipher = new TranspositionCipher("megabuck");
        String transpositionEncrypted = transpositionCipher.encrypt("Hello Geek");
        String transpositionDecrypted = transpositionCipher.decrypt(transpositionEncrypted);

        String aesOriginal = "GeeksforGeeks";
        String aesEncrypted = AES.encrypt(aesOriginal);
        String aesDecrypted = AES.decrypt(aesEncrypted);

        String otpPlain = "HELLO";
        String otpKey = "MONEY";
        String otpEncrypted = OneTimePad.encrypt(otpPlain, otpKey);
        String otpDecrypted = OneTimePad.decrypt(otpEncrypted, otpKey);

        System.out.println("Transposition Cipher Text: " + transpositionEncrypted);
        System.out.println("Transposition Decrypted: " + transpositionDecrypted);

        System.out.println("AES Original: " + aesOriginal);
        System.out.println("AES Encrypted: " + aesEncrypted);
        System.out.println("AES Decrypted: " + aesDecrypted);

        System.out.println("OTP Cipher Text: " + otpEncrypted);
        System.out.println("OTP Decrypted: " + otpDecrypted);
    }
}
