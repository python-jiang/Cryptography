public class Vernam {
    public static void main(String[] args){
        int toCode = 1, toReal = -1;
        String message = "HowAreYou";
        String key = "NCBTZQARX";
        String code = change(message, key, toCode);
        System.out.println("密文：" + code);
        String real = change(code, key, toReal);
        System.out.println("解密：" + real);
    }
    static String change(String message, String key, int k){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < message.length(); ++i){
            sb.append(Character.toUpperCase(message.charAt(i)));
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < sb.length(); ++i){
            int a = sb.charAt(i) - 'A';
            int b = key.charAt(i) - 'A';
            int c = (a + k*b + 26)%26;
            result.append((char)('A' + c));
        }
        return result.toString();
    }
}
