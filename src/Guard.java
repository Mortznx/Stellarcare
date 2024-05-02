import java.io.File;
import java.io.IOException;

public class Guard {
    public Guard() {

    }

    public Patient searchPatient(String name,String unit) {
        return switch (unit) {
            case "emergency" -> new Emergency().getPatientInformation(new Emergency().searchPatient(name));
            case "ICU" -> new ICU().getPatientInformation(new ICU().searchPatient(name));
            case "CCU" -> new CCU().getPatientInformation(new CCU().searchPatient(name));
            case "PICU" -> new PICU().getPatientInformation(new PICU().searchPatient(name));
            case "NICU" -> new NICU().getPatientInformation(new NICU().searchPatient(name));
            case "InternalMedicine" -> new InternalMedicine().getPatientInformation
                    (new InternalMedicine().searchPatient(name));
            case "Neurology" -> new Neurology().getPatientInformation(new Neurology().searchPatient(name));
            case "Obstetrics" -> new Obstetrics().getPatientInformation(new Obstetrics().searchPatient(name));
            default -> null;
        };
    }

    public boolean IsDischarged(String name,String unit) {
        int id = searchPatient(name,unit).getId();
        File file = new File("BILLING" + id+".txt");
        return !file.exists();
    }
}
