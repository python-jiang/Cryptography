public class RailFenceTechnique {
    public static void main(String[] args){
        String message = "Come home tomorrow.";
        int[] key = {4, 6, 1, 2, 5, 3};
        String code = toCode(message, key);
        System.out.println("密码：" + code);
        String real = toReal(code, key);
        System.out.println("解密：" + real);

        String code2 = toCode(code, key);
        System.out.println("二次加密：" + code2);
        code = toReal(code2, key);
        System.out.println("首次解密：" + code);
        real = toReal(code, key);
        System.out.println("再次解密：" + real);
    }
    static String toCode(String message, int[] key){
        int k = key.length;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < message.length(); ++i){
            char c = message.charAt(i);
            if(Character.isAlphabetic(c)) sb.append(c);
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < k; ++i){
            int p = key[i] - 1;
            for(int j = 0; j*k + p < sb.length(); ++j){
                result.append(sb.charAt(j*k + p));
            }
        }
        return result.toString();
    }
    static String toReal(String code, int[] key){
        int k = key.length;
        int[] m = new int[k];
        int len = code.length()/k;
        int y = code.length()%k;
        for(int i = 0; i < k; ++i) m[i] = len;
        for(int i = 0; i < y; ++i) m[i]++;
        StringBuilder sb = new StringBuilder(code);
        int id = 0;
        for(int i = 0; i < k; ++i){
            int p = key[i] - 1;
            for(int j = 0; j < m[p]; ++j){
                sb.setCharAt(j*k + p, code.charAt(id++));
            }
        }
        return sb.toString();
    }
}
