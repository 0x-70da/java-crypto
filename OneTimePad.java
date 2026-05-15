public class OneTimePad {
    public static String encrypt(String text, String key) {
        StringBuilder cipherText = new StringBuilder();
        int[] cipher = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            cipher[i] = text.charAt(i) - 'A' + key.charAt(i) - 'A';
            if (cipher[i] > 25) {
                cipher[i] -= 26;
            }
            cipherText.append((char) (cipher[i] + 'A'));
        }

        return cipherText.toString();
    }

    public static String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        int[] plain = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            plain[i] = cipherText.charAt(i) - 'A' - (key.charAt(i) - 'A');
            if (plain[i] < 0) {
                plain[i] += 26;
            }
            plainText.append((char) (plain[i] + 'A'));
        }

        return plainText.toString();
    }
}
