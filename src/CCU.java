import java.util.ArrayList;
import java.util.HashMap;

public class CCU extends Patient {
    private final static int countBeds = 100;
    private static HashMap<Integer, Patient> beds;
    private Emergency emergency;

    public CCU() {
        super();
    }

    public CCU(String string) {
        beds = new HashMap<>();
        this.emergency = new Emergency();
    }

    public int isEmptyBeds() {
        for (int i = 0; i < this.countBeds; i++) {
            if (beds.containsKey(i)) {
                return i;
            }
        }
        return -1;
    }

    public int emptyBeds() {
        return (this.countBeds - beds.size());
    }

    public boolean isEmpty(int input) {
        if (beds.containsKey(input))
            return true;
        else
            return false;
    }

    public Patient getPatientInformation(int num) {
        return beds.get(num);
    }

    public void addPatient(Patient patient) {
        //افزودن بیمار
        if (isEmptyBeds() == -1) {
            System.out.println("There are no empty beds!");
        } else {
            beds.put(isEmptyBeds(), patient);
        }
    }

    public void addPatient(ArrayList<String> input) {
        if (isEmptyBeds() == -1) {
            System.out.println("There are no empty beds!");
        } else {
            Patient patient = new Patient();
            patient.arrayToStrings(input);
            beds.put(isEmptyBeds(), patient);
        }

    }

    public void changPatientBed(int from, int to) {
        //این متد توسط پرستار دسترسی داره ولی یه ایرادی داره که هنگام تغییر تخت باید توی لیست دکتر این تغییرات لحاظ شه
        //تعویض تخت بیمار
        if (beds.containsKey(from)) {
            System.out.println("error");
        } else if (beds.containsKey(to)) {
            System.out.println("error");
        } else {
            Patient patient = beds.get(from);
            beds.remove(from);
            beds.put(to,patient);
        }
    }

    public void discharge(int num) {
        if (this.beds.get(num) == null) {
            System.out.println("error");
        } else {
            beds.remove(num);
        }
    }

    public int searchPatient(String name) {
        for (int i = 0; i < countBeds; i++) {
            if (beds.get(i).getFirstName().equals(name))
                return i;
            else if (beds.get(i).getLastName().equals(name))
                return i;
        }
        return -1;
    }

    public boolean changeUnits(int num, String newUnit) {
        //انتقال بیمار به بخش دیگه
        if (!beds.containsKey(num)) {
            return false;
        }
        Patient patient = new Patient();
        Billing billing = new Billing();
        switch (newUnit) {
            case "Neurology":
                Neurology neurology = new Neurology();
                super.readerDetailsFile(getPatientInformation(num).getId());
                neurology.addPatient(patient);
                super.writerReportFile(getPatientInformation(num).getId(), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                billing.changUnit(patient.getId(),"CCU",newUnit);
                return true;
            case "ICU":
                ICU icu = new ICU();
                super.readerDetailsFile(getPatientInformation(num).getId());
                icu.addPatient(patient);
                super.writerReportFile(getPatientInformation(num).getId(), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                billing.changUnit(patient.getId(),"CCU",newUnit);
                return true;
            case "NICU":
                NICU nicu = new NICU();
                super.readerDetailsFile(getPatientInformation(num).getId());
                nicu.addPatient(patient);
                super.writerReportFile(getPatientInformation(num).getId(), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                billing.changUnit(patient.getId(),"CCU",newUnit);
                return true;
            case "PICU":
                PICU picu = new PICU();
                super.readerDetailsFile(getPatientInformation(num).getId());
                picu.addPatient(patient);
                super.writerReportFile(getPatientInformation(num).getId(), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                billing.changUnit(patient.getId(),"CCU",newUnit);
                return true;
            case "Internal medicine" :
                InternalMedicine internalMedicine = new InternalMedicine();
                super.readerDetailsFile(getPatientInformation(num).getId());
                internalMedicine.addPatient(patient);
                super.writerReportFile(getPatientInformation(num).getId(),"the patient was transferred to unit " + newUnit);
                beds.remove(num);
                billing.changUnit(patient.getId(),"CCU",newUnit);
                return true;
            case "Obstetrics" :
                Obstetrics obstetrics = new Obstetrics();
                super.readerDetailsFile(getPatientInformation(num).getId());
                obstetrics.addPatient(patient);
                super.writerReportFile(getPatientInformation(num).getId(),"the patient was transferred to unit " + newUnit);
                beds.remove(num);
                billing.changUnit(patient.getId(),"CCU",newUnit);
                return true;
        }
        return false;
    }

    /*public static void makeBeds() {
        for (int i = 0; i < countBeds; i++) {
            beds.putIfAbsent(i, null);
        }
    }*/
}
