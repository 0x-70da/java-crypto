public class TranspositionCipher {
    private String selectedKey;
    private char[] sortedKey;
    private int[] sortedKeyPos;

    public TranspositionCipher() {
        this("megabuck");
    }

    public TranspositionCipher(String key) {
        selectedKey = key;
        sortedKeyPos = new int[selectedKey.length()];
        sortedKey = selectedKey.toCharArray();
    }

    private void processKey() {
        char[] originalKey = selectedKey.toCharArray();

        for (int i = 0; i < selectedKey.length(); i++) {
            int min = i;
            for (int j = i; j < selectedKey.length(); j++) {
                if (sortedKey[min] > sortedKey[j]) {
                    min = j;
                }
            }
            if (min != i) {
                char temp = sortedKey[i];
                sortedKey[i] = sortedKey[min];
                sortedKey[min] = temp;
            }
        }

        for (int i = 0; i < selectedKey.length(); i++) {
            for (int j = 0; j < selectedKey.length(); j++) {
                if (originalKey[i] == sortedKey[j]) {
                    sortedKeyPos[i] = j;
                }
            }
        }
    }

    public String encrypt(String plainText) {
        processKey();

        int baseRows = plainText.length() / selectedKey.length();
        int extra = plainText.length() % selectedKey.length();
        int rows = baseRows + (extra == 0 ? 0 : 1);
        int totalLength = rows * selectedKey.length();

        char[][] matrix = new char[rows][selectedKey.length()];
        char[] encrypted = new char[totalLength];

        int row = 0;
        int col = -1;

        for (int i = 0; i < totalLength; i++) {
            col++;
            if (i < plainText.length()) {
                if (col == selectedKey.length()) {
                    row++;
                    col = 0;
                }
                matrix[row][col] = plainText.charAt(i);
            } else {
                matrix[row][col] = '-';
            }
        }

        int index = 0;
        for (int i = 0; i < selectedKey.length(); i++) {
            int keyColumn = 0;
            for (; keyColumn < selectedKey.length(); keyColumn++) {
                if (i == sortedKeyPos[keyColumn]) {
                    break;
                }
            }
            for (int j = 0; j <= row; j++) {
                encrypted[index++] = matrix[j][keyColumn];
            }
        }

        return new String(encrypted);
    }

    public String decrypt(String cipherText) {
        processKey();

        int rows = cipherText.length() / selectedKey.length();
        char[][] matrix = new char[rows][selectedKey.length()];
        char[] encrypted = cipherText.toCharArray();

        int readIndex = 0;
        for (int i = 0; i < selectedKey.length(); i++) {
            int keyColumn = 0;
            for (; keyColumn < selectedKey.length(); keyColumn++) {
                if (i == sortedKeyPos[keyColumn]) {
                    break;
                }
            }

            for (int j = 0; j < rows; j++) {
                matrix[j][keyColumn] = encrypted[readIndex++];
            }
        }

        char[] plain = new char[rows * selectedKey.length()];
        int plainIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < selectedKey.length(); j++) {
                if (matrix[i][j] != '-') {
                    plain[plainIndex++] = matrix[i][j];
                }
            }
        }

        return new String(plain, 0, plainIndex);
    }
}
