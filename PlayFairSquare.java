import java.util.*;
class Coordinate{
    int x, y;
    Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}
// 此算法假设X不会出现在明文末尾，且不会出现两个连续的X
public class PlayFairSquare {
    public static void main(String[] args){
        String keyword = "Playfair Example";
        int toCode = 1, toReal = -1;

        char[][] key = square(keyword);
        System.out.println("根据密钥生成5X5矩阵（其中I=J）:");
        for(int i = 0; i < key.length; ++i){
            for(int j = 0; j < key[0].length; ++j){
                System.out.print(key[i][j] + " ");
            }
            System.out.println();
        }

        String message = "My name is Atul.";
        String code = change(message, key, toCode);
        System.out.println("密文1：" + code);
        String real = change(code, key, toReal);
        System.out.println("解密1：" + real);

        message = "Come home today.";
        code = change(message, key, toCode);
        System.out.println("密文2：" + code);
        real = change(code, key, toReal);
        System.out.println("解密2：" + real);

        message = "ABCDEFG";
        code = change(message, key, toCode);
        System.out.println("密文3：" + code);
        real = change(code, key, toReal);
        System.out.println("解密3：" + real);

        message = "joker fly";
        code = change(message, key, toCode);
        System.out.println("密文4：" + code);
        real = change(code, key, toReal);
        System.out.println("解密4：" + real);
    }
    static char[][] square(String keyword){
        char[][] m = new char[5][5];
        List<Character> v = new ArrayList<>();
        for(int i = 0; i < keyword.length(); i++){
            if(Character.isAlphabetic(keyword.charAt(i))){
                Character temp = Character.toUpperCase(keyword.charAt(i));
                if(!v.contains(temp)){
                    v.add(temp);
                }
            }
        }
        for(int i = 0; i < 5; ++i){
            for(int j = 0; j < 5; ++j){
                if(i*5 + j < v.size()){
                    m[i][j] = v.get(i*5 + j);
                }else{
                    for(char k = 'A'; k <= 'Z'; k++){
                        if(v.contains(k) || k == 'J') continue;
                        v.add(k);
                        m[i][j] = k;
                        break;
                    }
                }
            }
        }
        return m;
    }
    static String change(String message, char[][] key, int k){
        Map<Character, Coordinate> m = new HashMap<>();
        for(int i = 0; i < key.length; ++i){
            for(int j = 0; j < key[0].length; ++j){
                m.put(key[i][j], new Coordinate(i, j));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < message.length(); ++i){
            char c = message.charAt(i);
            if(Character.isAlphabetic(c)){
                if(c == 'j') c = 'I';
                if(c == 'J') c = 'I';
                sb.append(Character.toUpperCase(c));
            }
        }
        int len = sb.length();
        for(int i = 0; i < sb.length(); i += 2){
            if(i + 1 == sb.length()) sb.append('X');
            char a = sb.charAt(i), b = sb.charAt(i + 1);
            if(a == b) sb.insert(i, 'X');
            b = sb.charAt(i + 1);
            int rowa = m.get(a).x;
            int cola = m.get(a).y;
            int rowb = m.get(b).x;
            int colb = m.get(b).y;
            if(rowa == rowb){
                sb.setCharAt(i, key[rowa][(cola + k + 5)%5]);
                sb.setCharAt(i + 1, key[rowb][(colb + k + 5)%5]);
            }else if(cola == colb){
                sb.setCharAt(i, key[(rowa + k + 5)%5][cola]);
                sb.setCharAt(i + 1, key[(rowb + k + 5)%5][colb]);
            }else{
                sb.setCharAt(i, key[rowa][colb]);
                sb.setCharAt(i + 1, key[rowb][cola]);
            }
        }
        return sb.toString();
    }
}
