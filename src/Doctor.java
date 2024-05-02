import java.util.ArrayList;

public class Doctor extends Patient {
    private Sender sender;
    private int grade;
    private int patientId;
    private String doctorName;
    private String unit;
    private int staffNumber;
    private static ArrayList<Integer> messege;
    private static ArrayList<Integer> checking;
    private int bedNumber;

    public Doctor() {
        messege = new ArrayList<>();
    }

    public Doctor(int staffNumber,int grade, String doctorName, String unit, int id) {
        super();
        this.sender = new Sender(staffNumber,grade, doctorName, unit, id);
        this.staffNumber = staffNumber;
        this.grade = grade;
        this.patientId = 0;
        this.doctorName = doctorName;
        this.unit = unit;
    }

    public void startShift() {
        this.sender.start();
    }

    public void endShift() {
        this.sender.end();
        if (!checking.isEmpty()) {
            this.sender.writeReport("the doctor ded not examine " + checking.size() + "bed");
        }
    }

    public void getInformation(int num) {
        this.sender.getPatientInfo(num);
    }

    public String getReports(int num) {
        this.sender.getPatientInfo(num);
        return readerReportFile(getId());
    }

    public String patient(int num) {
        getInformation(num);
        return getReports(num);
    }

    public void writeReport(int num, String text) {
        getInformation(num);
        writerReportFile(getId(), text);
    }

    public void getCheckUpList() {
        checking = this.sender.getCheckingList();
    }

    public void getCheck() {
        this.bedNumber = checking.get(0);
        this.sender.getCheck(checking.get(0));
        checking.remove(0);
        String report = patient(bedNumber);    // حالا به تمام اطلاعات بیمار در سوپر کلاس دسترسی داریم
    }

    public void discharge() {
        this.sender.discharge(this.bedNumber);
    }

    public boolean changUnit(int num, String newUnit) {
        return this.sender.changUnit(num, newUnit);
    }

    public void getTest(int num,String testName,String request) {
        this.sender.getTest(num,testName,request);
    }

    public void getOperating(int num,String text) {
        this.sender.getOperating(num,text);
    }
}