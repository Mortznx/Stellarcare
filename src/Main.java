import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer,Object> x = new HashMap<>();
        x.put(0,new xn("morteza"));
        x.get(0);
    }
}
class xn {
    private String name;
    public xn(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}