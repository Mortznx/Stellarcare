import java.util.ArrayList;
import java.util.HashMap;

public class CheckUp {
    private HashMap<Integer, Patient> beds;
    private ArrayList<Integer> morning = new ArrayList<>();
    private ArrayList<Integer> afternoon = new ArrayList<>();
    private ArrayList<Integer> night = new ArrayList<>();

    public CheckUp(HashMap<Integer, Patient> a) {
        this.beds = a;
    }

    public void handler(int count) {
        for (int i = 0; i < count; i++) {
            if (this.beds.get(i) == null) {
                continue;
            }
            switch (this.beds.get(i).getGetCheckUp()) {
                case 3:
                    this.morning.add(i);
                    this.afternoon.add(i);
                    this.night.add(i);
                case 2:
                    this.morning.add(i);
                    this.night.add(i);
                case 1:
                    this.afternoon.add(i);
            }
        }
    }

    public ArrayList<Integer> checkUpList(String input) {
        switch (input) {
            case "moorning":
                return this.morning;
            case "afternoon":
                return this.afternoon;
            case "night":
                return this.night;
            default:
                return null;
        }
    }

    public ArrayList<Integer> checkUp0() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < this.beds.size(); i++) {
            if (this.beds.get(i).getGetCheckUp() == 0)
                list.add(i);
        }
        return list;
    }
}
