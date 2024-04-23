import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Neurology extends Patient {
    private final static int countBed = 100;
    private static HashMap<Integer, ArrayList<String>> beds;
    private ArrayList<Integer> morning = new ArrayList<>();
    private ArrayList<Integer> afternoon = new ArrayList<>();
    private ArrayList<Integer> night = new ArrayList<>();

    public Neurology() {
        super();
    }

    public Neurology(String st) {
        super();
        this.beds = new HashMap<>();
    }

    public int isEmptyBeds() {
        //اولین تخت خالی رو میگه و اگه تخت خالی نداشته باشیم -1 میده
        for (int i = 0; i < countBed; i++) {
            if (this.beds.get(i).equals(null)) {
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
        if (beds.get(input) == null)
            return true;
        else
            return false;
    }

    public ArrayList<String> getPatientInformation(int num) {
        return this.beds.get(num);
    }

    public void addPatient(String input) {
        //افزودن بیمار
        if (isEmptyBeds() == -1) {
            System.out.println("There are no empty beds!");
        } else {
            super.writerDetailsFile(super.getNewId());
            beds.put(isEmptyBeds(), new ArrayList<>());
            ArrayList<String> info = beds.get(isEmptyBeds());
            String[] strings = input.split("\n");
            for (int i = 0; i < strings.length; i++) {
                info.add(strings[i]);
            }
        }
    }

    public void addPatient(ArrayList<String> input) {
        //افزودن بیمار
        if (isEmptyBeds() == -1) {
            System.out.println("There are no empty beds!");
        } else {
            beds.put(isEmptyBeds(), input);
        }
    }

    public void changPatientBed(int from, int to) {
        //این متد توسط پرستار دسترسی داره ولی یه ایرادی داره که هنگام تغییر تخت باید توی لیست دکتر این تغییرات لحاظ شه
        //تعویض تخت بیمار
        if (this.beds.get(from) == null) {
            System.out.println("error");
        } else if (beds.get(to) != null) {
            System.out.println("error");
        } else {
            beds.put(to, new ArrayList<>());
            ArrayList<String> info = this.beds.get(from);
            beds.put(from, null);
            beds.put(to, info);
        }
    }

    public void discharge(int num) {
        //خالی کردن تخت
        Date date = new Date();
        if (beds.get(num) == null) {
            System.out.println("error");
        } else {
            beds.put(num, null);
            super.writerReportFile(Integer.parseInt(getPatientInformation(num).get(13)), "the patient was dischrged in :"
                    + date.getYear() + "/" + date.getMonth() + "/" + date.getDay());
        }
    }

    public int searchPatient(String name) {
        for (int i = 0; i < countBed; i++) {
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
            case "ICU":
                ICU icu = new ICU();
                super.readerDetailsFile(num);
                icu.addPatient(super.toString());
                super.writerReportFile(Integer.parseInt(getPatientInformation(num).get(13)), "the patient was transferred to unit " + newUnit);
                beds.remove(num);
                return true;
            case "CCU":
                CCU ccu = new CCU();
                super.readerDetailsFile(num);
                ccu.addPatient(super.toString());
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
        //اضافه کردن تخت به بخش
        for (int i = 0; i < countBed; i++) {
            beds.putIfAbsent(i, null);
        }
    }

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
