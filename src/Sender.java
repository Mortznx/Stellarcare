import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Sender {
    private int unitNum;
    private int staffNumber;
    private String doctorName;
    private String unit;
    private int id;
    private Doctor doctor;
    private Patient patient;
    private Emergency emergency;
    private Neurology neurology;
    private ICU icu;
    private CCU ccu;
    private NICU nicu;
    private PICU picu;
    private Billing billing;
    private InternalMedicine internalMedicine;
    private Obstetrics obstetrics;
    private Surgical surgical;
    private CTScan ctScan;
    private File file;

    public Sender(int staffNumber,int unitNum, String doctorName, String unit, int id) {
        this.staffNumber = staffNumber;
        this.unitNum = unitNum;
        this.doctorName = doctorName;
        this.unit = unit;
        this.id = id;
        this.patient = new Patient();
        this.emergency = new Emergency();
        this.neurology = new Neurology();
        this.icu = new ICU();
        this.ccu = new CCU();
        this.nicu = new NICU();
        this.picu = new PICU();
        this.billing = new Billing();
        this.internalMedicine = new InternalMedicine();
        this.obstetrics = new Obstetrics();
        this.surgical = new Surgical();
        this.ctScan = new CTScan();
        this.file = new File("doctor" + doctorName + unit + id);
    }

    public Sender(String unitName, int grade) {
        this.doctor = new Doctor();
    }

    public Patient getPatientInfo(int num) {
        return switch (unitNum) {
            case 0 -> this.emergency.getPatientInformation(num);
            case 1 -> this.icu.getPatientInformation(num);
            case 2 -> this.ccu.getPatientInformation(num);
            case 3 -> this.nicu.getPatientInformation(num);
            case 4 -> this.picu.getPatientInformation(num);
            case 5 -> this.neurology.getPatientInformation(num);
            case 6 -> this.internalMedicine.getPatientInformation(num);
            case 7 -> this.obstetrics.getPatientInformation(num);
            default -> null;
        };
    }

    public void writeReport(String text) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter("doctor" + this.doctorName + this.unit + this.id,true);
            writer.write(now() + text+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String now() {
        Date date = new Date();
        String day = String.valueOf(date.getDay());
        String month = String.valueOf(date.getMonth());
        String year = String.valueOf(date.getYear());
        String hour = String.valueOf(date.getHours());
        String minute = String.valueOf(date.getMinutes());
        return (day + "/" + month + "/" + year + " " + hour + ":" + minute);
    }

    public String hour() {
        Date date = new Date();
        if (date.getHours() < 8)
            return "night";
        else if (date.getHours() < 16) {
            return "morning";
        } else
            return "afternoon";
    }

    public void start() {
        writeReport("start working" + " " + now());
    }

    public void end() {
        writeReport("end of working" + " " + now());
    }

    public ArrayList<Integer> getCheckingList() {
        return switch (unitNum) {
            //case 0 -> emergency.sendCheckUpList(hour());
            case 5 -> neurology.getCheckUpList(hour());
            //case 1 ->
            default -> null;
        };
    }

    public void getCheck(int num) {
        writeReport("checking the bed " + num);
    }

    public void discharge(int num) {
        switch (unitNum) {
            case 0:
                this.emergency.discharge(num);
            case 1:
                this.icu.discharge(num);
            case 2:
                this.ccu.discharge(num);
            case 3:
                this.nicu.discharge(num);
            case 4:
                this.picu.discharge(num);
            case 5:
                this.internalMedicine.discharge(num);
            case 6:
                this.obstetrics.discharge(num);
        }
        this.billing.discharging(getPatientInfo(num).getId());
    }


    public boolean changUnit(int num, String newUnit) {
        if (changeUnitHandler(newUnit)) {
            return switch (unitNum) {
                case 0 -> emergency.changeUnits(num, newUnit);
                case 1 -> icu.changeUnits(num, newUnit);
                case 2 -> ccu.changeUnits(num, newUnit);
                case 3 -> nicu.changeUnits(num, newUnit);
                case 4 -> picu.changeUnits(num, newUnit);
                case 5 -> internalMedicine.changeUnits(num,newUnit);
                case 6 -> obstetrics.changeUnits(num,newUnit);
                default -> false;
            };
        } else {
            return false;
        }
    }

    public boolean changeUnitHandler(String newUnit) {
        return switch (newUnit) {
            case "neurology" -> this.neurology.isEmptyBeds() != -1;
            case "emergency" -> this.emergency.isEmptyBeds() != -1;
            case "icu" -> this.icu.isEmptyBeds() != -1;
            case "ccu" -> this.ccu.isEmptyBeds() != -1;
            case " nicu" -> this.nicu.isEmptyBeds() != -1;
            case "picu" -> this.picu.isEmptyBeds() != -1;
            case "internal medicine" -> this.internalMedicine.isEmptyBeds() != -1;
            case "obstetrics" -> this.obstetrics.isEmptyBeds() != -1;
            default -> false;
        };
    }

    public void getTest(int num, String request, String test) {
        ArrayList<String> info = new Patient().toArray(getPatientInfo(num));
        switch (test) {
            case "CTScan":
                ctScan.newRequest(info , request);
            case "":
        }
    }

    public void getOperating(int num, String string) {
        ArrayList<String> info = new Patient().toArray(getPatientInfo(num));
        Patient patientInfo = new Patient();

        patient.arrayToStrings(info);
        this.surgical.addRequest(getPatientInfo(num).getId(), patient.toString());
    }
}
