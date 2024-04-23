import java.beans.DesignMode;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Sender {
    private int unitNum;
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
    private OperatingRoom operatingRoom;
    private CTScan ctScan;
    private File file;

    public Sender(int unitNum, String doctorName, String unit, int id) {
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
        this.operatingRoom = new OperatingRoom();
        this.ctScan = new CTScan();
        this.file = new File("doctor" + doctorName + unit + id);
    }

    public Sender(String unitName, int grade) {
        this.doctor = new Doctor();
    }

    public ArrayList<String> getPatientInfo(int num) {
        return switch (unitNum) {
            case 0 -> emergency.getPatientInformation(num);
            case 1 -> icu.getPatientInformation(num);
            case 2 -> ccu.getPatientInformation(num);
            case 3 -> nicu.getPatientInformation(num);
            case 4 -> picu.getPatientInformation(num);
            case 5 -> neurology.getPatientInformation(num);
            default -> null;
        };
    }

    public void writeReport(String text) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter("doctor" + this.doctorName + this.unit + this.id);
            writer.write(now() + text);
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
        }
    }

    public boolean changUnit(int num, String newUnit) {
        if (changeUnitHandler(newUnit)) {
            return switch (unitNum) {
                case 0 -> emergency.changeUnits(num, newUnit);
                case 1 -> icu.changeUnits(num, newUnit);
                case 2 -> ccu.changeUnits(num, newUnit);
                case 3 -> nicu.changeUnits(num, newUnit);
                case 4 -> picu.changeUnits(num, newUnit);
                default -> false;
            };
        } else {
            return false;
        }
    }

    public boolean changeUnitHandler(String newUnit) {
        return switch (newUnit) {
            case "neurology" -> neurology.isEmptyBeds() != -1;
            case "emergency" -> emergency.isEmptyBeds() != -1;
            case "icu" -> icu.isEmptyBeds() != -1;
            case "ccu" -> ccu.isEmptyBeds() != -1;
            case " nicu" -> nicu.isEmptyBeds() != -1;
            case "picu" -> picu.isEmptyBeds() != -1;
            default -> false;
        };
    }

    public void getTest(int num, String request, String test) {
        switch (test) {
            case "CTScan":
                ctScan.newRequest(getPatientInfo(num), request);
            case "":
        }
    }

    public void getOperating(int num, String string) {
        ArrayList<String> info = getPatientInfo(num);
        patient.arrayToStrings(info);
        this.operatingRoom.addRequest(Integer.parseInt(getPatientInfo(num).get(13)), patient.toString());
    }
}
