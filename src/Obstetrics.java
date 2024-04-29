import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class Obstetrics extends Patient {
    private final static int countBed = 100;
    private static HashMap<Integer, Patient> beds;
    private ArrayList<Integer> morning = new ArrayList<>();
    private ArrayList<Integer> afternoon = new ArrayList<>();
    private ArrayList<Integer> night = new ArrayList<>();

    public Obstetrics() {
        super();
    }

    public Obstetrics(String st) {
        super();
        beds = new HashMap<>();
    }

    public int isEmptyBeds() {
        //اولین تخت خالی رو میگه و اگه تخت خالی نداشته باشیم -1 میده
        for (int i = 0; i < countBed; i++) {
            if (beds.containsKey(i)) {
                continue;
            } else {
                return i;
            }
        }
        return -1;
    }

    public int emptyBeds() {
        //تعداد تخت خالی
        return (countBed - beds.size());
    }

    public boolean isEmpty(int input) {
        return (beds.containsKey(input));
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
        //افزودن بیمار
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
        //خالی کردن تخت
        Date date = new Date();
        if (!beds.containsKey(num)) {
            System.out.println("error");
        } else {
            beds.remove(num);
            super.writerReportFile(getPatientInformation(num).getId(), "the patient was dischrged in :"
                    + date.getYear() + "/" + date.getMonth() + "/" + date.getDay());
        }
    }

    public int searchPatient(String name) {
        for (int i = 0; i < countBed; i++) {
            if (beds.isEmpty())
                break;
            if (!beds.containsKey(i))
                continue;
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
        Patient patient = beds.get(num);
        Billing billing = new Billing();
        switch (newUnit) {
            case "Neurology":
                Neurology neurology = new Neurology();
                super.readerDetailsFile(getPatientInformation(num).getId());
                neurology.addPatient(patient);
                super.writerReportFile(getPatientInformation(num).getId(), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                billing.changUnit(patient.getId(),"obstetrics",newUnit);
                return true;
            case "ICU":
                ICU icu = new ICU();
                super.readerDetailsFile(getPatientInformation(num).getId());
                icu.addPatient(patient);
                super.writerReportFile(getPatientInformation(num).getId(), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                billing.changUnit(patient.getId(),"obstetrics",newUnit);
                return true;
            case "CCU":
                CCU ccu = new CCU();
                super.readerDetailsFile(getPatientInformation(num).getId());
                ccu.addPatient(patient);
                super.writerReportFile(getPatientInformation(num).getId(), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                billing.changUnit(patient.getId(),"obstetrics",newUnit);
                return true;
            case "NICU":
                NICU nicu = new NICU();
                super.readerDetailsFile(getPatientInformation(num).getId());
                nicu.addPatient(patient);
                super.writerReportFile(getPatientInformation(num).getId(), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                billing.changUnit(patient.getId(),"obstetrics",newUnit);
                return true;
            case "PICU":
                PICU picu = new PICU();
                super.readerDetailsFile(getPatientInformation(num).getId());
                picu.addPatient(patient);
                super.writerReportFile(getPatientInformation(num).getId(), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                billing.changUnit(patient.getId(),"obstetrics",newUnit);
                return true;
            case "Internal medicine" :
                InternalMedicine internalMedicine = new InternalMedicine();
                super.readerDetailsFile(getPatientInformation(num).getId());
                internalMedicine.addPatient(patient);
                super.writerReportFile(getPatientInformation(num).getId(),"the patient was transferred to unit " + newUnit);
                beds.remove(num);
                billing.changUnit(patient.getId(),"obstetrics",newUnit);
                return true;
        }
        return false;
    }

    /*public static void makeBeds() {
        //اضافه کردن تخت به بخش
        for (int i = 0; i < countBed; i++) {
            beds.putIfAbsent(i, new ArrayList<>());
        }
    }*/

    public void getCheckUp() {
        //نیازه که هر 24 ساعت یکبار فراخوانی بشه
        CheckUp check = new CheckUp(beds);
        check.handler(countBed);
        this.morning = check.checkUpList("moorning");
        this.afternoon = check.checkUpList("afternoon");
        this.night = check.checkUpList("night");
    }

    public ArrayList<Integer> getCheckUpList(String input) {
        //متدی که باید توسط کلاس دکتر فراخوانی بشه تا لیست بیمارانی که باید چک بکنه رو دریافت کنه
        switch (input) {
            case "morinig":
                return this.morning;
            case "afternoon":
                return this.afternoon;
            case "night":
                return this.night;
            default:
                return null;
        }
    }

    public ArrayList<Integer> sendCheckUpList() {
        Date date = new Date();
        short hour = (short) date.getHours();
        String time = null;
        if (hour < 8)
            time = "night";
        else if (hour < 16)
            time = "morning";
        else
            time = "afternoon";
        return getCheckUpList(time);
    }

    public ArrayList<Integer> withOutCheckUpTime() {
        //بررسی میکنه که آیا بیماری هست که تعداد دفعات چک کردنش مشخص نشده باشه
        CheckUp checkUp = new CheckUp(beds);
        return checkUp.checkUp0();
    }
}

