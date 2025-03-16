package Users;

public class Encrypt {
    private static boolean arrayContains(String value, String[] array) {
        if (array != null && value != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(value)) return true;
            }
        }
        return false;
    }
    private static int getIndex(String value, String[] array) {
        int index = -1;
        if (array != null && value != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(value)) return i;
            }
        }
        return index;
    }
    static String encrypt(String word) {
        String encryp = "";
        if (word != null) {
            String symb[] = {".",",","*","!","@","#", "?", "/", ";", ":", "$", "%", "&", "(", ")", "[", "]", "{", "}", "'", "\"", "=", "+", "-", "_" };
            String num[] = {"1","2","3","4","5","6","7","8","9","0"};
            String lower[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
            String upper[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

            for (int i = 0; i < word.length(); i++) {
                int index;
                if (arrayContains(String.valueOf(word.charAt(i)), lower)) {
                    index = i + getIndex(String.valueOf(word.charAt(i)), lower);
                    while (index > (symb.length - 1)) index -= symb.length;
                    encryp += symb[index];
                } else if (arrayContains(String.valueOf(word.charAt(i)), upper)) {
                    index = i + getIndex(String.valueOf(word.charAt(i)), upper);
                    while (index > (num.length - 1)) index -= num.length;
                    encryp += num[index];
                } else if (arrayContains(String.valueOf(word.charAt(i)), num)) {
                    index = i + getIndex(String.valueOf(word.charAt(i)), num);
                    while (index > (lower.length - 1)) index -= lower.length;
                    encryp += lower[index];
                } else if (arrayContains(String.valueOf(word.charAt(i)), symb)) {
                    index = i + getIndex(String.valueOf(word.charAt(i)), symb);
                    while (index > (upper.length - 1)) index -= upper.length;
                    encryp += upper[index];
                }
            }
        }
        return encryp;
    }
}
