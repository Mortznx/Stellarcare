import java.util.ArrayList;

public interface Unit {
    int isEmptyBeds();
    int emptyBeds();
    boolean isEmpty(int input);
    Patient getPatientInformation(int input);
    void addPatient(Patient patient);
    void changPatientBed(int from,int to);
    void discharge(int num);
    int searchPatient(String name);
    boolean changeUnits(int num,String newUnit);
    void getCheckUp();
    ArrayList<Integer> getCheckUpList(String unit);
    ArrayList<Integer> sendCheckUpList();
    ArrayList<Integer> withOutCheckUpTime();
}
