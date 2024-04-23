import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MyComparator {
    public void search(String input,String[] words) {
        a1(words,input);
        System.out.println(Arrays.toString(words));
    }
    public static void a1(String[] words,String input) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int count1 = a2(o1,input);
                int count2 = a2(o2,input);
                if (count2 != count1) {
                    return count2-count1;
                } else {
                    return a3(o2,input) - a3(o1,input);
                }
            }
            public int a2(String str,String input) {
                return str.split(input,-1).length-1;
            }
            public int a3(String str,String input) {
                int count =0;
                for (char c : str.toCharArray()) {
                    if (c==input.charAt(0))
                        count++;
                }
                return count;
            }
        });
    }

}
