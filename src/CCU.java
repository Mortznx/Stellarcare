import java.util.ArrayList;
import java.util.HashMap;

public class CCU extends Patient {
    private final static int countBeds = 100;
    private static HashMap<Integer, ArrayList<String>> beds;
    private Emergency emergency;

    public CCU() {
        super();
    }

    public CCU(String string) {
        this.beds = new HashMap<>();
        this.emergency = new Emergency();
    }

    public int isEmptyBeds() {
        for (int i = 0; i < this.countBeds; i++) {
            if (this.beds.get(i).equals(null)) {
                return i;
            }
        }
        return -1;
    }

    public int emptyBeds() {
        return (this.countBeds - this.beds.size());
    }

    public boolean isEmpty(int input) {
        if (beds.get(input) == null)
            return true;
        else
            return false;
    }

    public ArrayList<String> getPatientInformation(int num) {
        return this.beds.get(num);
    }

    public String addPatient(String input) {
        if (isEmptyBeds() == -1) {
            return ("There are no empty beds!");
        } else {
            beds.put(isEmptyBeds(), new ArrayList<>());
            ArrayList<String> info = beds.get(isEmptyBeds());
            String[] strings = input.split("\n");
            for (int i = 0; i < strings.length; i++) {
                info.add(strings[i]);
            }
        }
        return null;
    }

    public String addPatient(ArrayList<String> input) {
        if (isEmptyBeds() == -1) {
            return ("There are no empty beds!");
        } else {
            beds.put(isEmptyBeds(), input);
        }
        return null;
    }

    public void changPatientBed(int from, int to) {
        if (this.beds.get(from) == null) {
            System.out.println("error");
        } else if (this.beds.get(to) != null) {
            System.out.println("error");
        } else {
            beds.put(to, new ArrayList<>());
            ArrayList<String> info = this.beds.get(from);
            beds.put(from, null);
            beds.put(to, info);
        }
    }

    public void discharge(int num) {
        if (this.beds.get(num) == null) {
            System.out.println("error");
        } else {
            this.beds.put(num, null);
        }
    }

    public int searchPatient(String name) {
        for (int i = 0; i < countBeds; i++) {
            if (beds.get(i).get(0).equals(name))
                return i;
            else if (beds.get(i).get(1).equals(name))
                return i;
        }
        return -1;
    }

    public boolean changeUnits(int num, String newUnit) {
        //انتقال بیمار به بخش دیگه
        if (!beds.containsKey(num)) {
            return false;
        }
        ArrayList<String> information = beds.get(num);
        switch (newUnit) {
            case "Neurology":
                Neurology neurology = new Neurology();
                super.readerDetailsFile(num);
                neurology.addPatient(super.toString());
                super.writerReportFile(Integer.parseInt(getPatientInformation(num).get(13)), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                return true;
            case "ICU":
                ICU icu = new ICU();
                super.readerDetailsFile(num);
                icu.addPatient(super.toString());
                super.writerReportFile(Integer.parseInt(getPatientInformation(num).get(13)), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                return true;
            case "NICU":
                NICU nicu = new NICU();
                super.readerDetailsFile(num);
                nicu.addPatient(super.toString());
                super.writerReportFile(Integer.parseInt(getPatientInformation(num).get(13)), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                return true;
            case "PICU":
                PICU picu = new PICU();
                super.readerDetailsFile(num);
                picu.addPatient(super.toString());
                super.writerReportFile(Integer.parseInt(getPatientInformation(num).get(13)), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                return true;
        }
        return false;
    }

    public static void makeBeds() {
        for (int i = 0; i < countBeds; i++) {
            beds.putIfAbsent(i, null);
        }
    }
}
