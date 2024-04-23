import java.util.Arrays;

public class Sort {
    public double miane(int[] a, int[] b) {
        int[] c = new int[a.length+b.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        for (int i = a.length; i < c.length; i++) {
            c[i] = b[i];
        }
        Arrays.sort(c);
        if (c.length%2==0) {
            return (double) (((c[c.length]) / 2) + ((c[c.length]) / 2)) /2;
        } else {
            return c[c.length/2];
        }
    }
}
