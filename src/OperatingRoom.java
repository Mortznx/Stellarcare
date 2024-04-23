import java.util.ArrayList;

public class OperatingRoom {
    private ArrayList<String> request;
    private ArrayList<String> surgery;

    public OperatingRoom() {

    }

    public OperatingRoom(String st) {
        this.request = new ArrayList<>();
    }

    public void addRequest(int id, String input) {
        this.request.add(id + "\n" + input);
    }

    public String getRequest(int id) {
        String[] string = null;
        for (int i = 0; i < this.request.size(); i++) {
            string = this.request.get(i).split("\n");
            if (string[0].equals(String.valueOf(id))) {
                return string[1];
            }
        }
        return null;
    }

    public void addSurgery(String doctorNames, String patientId, String text) {
        this.surgery.add(doctorNames + "\n" + patientId + "\n" + text);
    }

    //متد واسه ارسال پیغام به پزشک
}
