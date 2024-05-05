import java.time.LocalDate;
import java.util.HashMap;

public class Nurse {
    private String unitName;
    public void addReport(String nurseName,String unitName, int bedNum,String text) {
        this.unitName = unitName;
        Patient patient = new Patient();
        patient.writerReportFile(getPatientInfo(bedNum).getId(),nurseName + " in " + getNow() + "\n" + text);
    }


    public Patient getPatientInfo(int num) {
        return switch (unitName) {
            case "emergency" -> new Emergency().getPatientInformation(num);
            case "ICU" -> new ICU().getPatientInformation(num);
            case "CCU" -> new CCU().getPatientInformation(num);
            case "NICU" -> new NICU().getPatientInformation(num);
            case "PICU" -> new PICU().getPatientInformation(num);
            case "neurology" -> new Neurology().getPatientInformation(num);
            case "internalMedicine" -> new InternalMedicine().getPatientInformation(num);
            case "obstetrics" -> new Obstetrics().getPatientInformation(num);
            default -> null;
        };
    }
    public String getNow() {
        LocalDate localDate = LocalDate.now();
        return localDate.getYear()+"/" + localDate.getMonthValue() + "/" + localDate.getDayOfMonth();
    }
}
