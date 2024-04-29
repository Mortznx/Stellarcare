import java.util.ArrayList;

public class Surgical {
    private ArrayList<String> request;
    private ArrayList<String> surgery;
    private Billing billing;

    public Surgical() {
        this.billing = new Billing();
    }

    public Surgical(String st) {
        this.request = new ArrayList<>();
        this.billing = new Billing();
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

    public void addSurgery(String doctorNames, String patientId, String text,int price) {
        this.surgery.add(doctorNames + "\n" + patientId + "\n" + text);
        this.billing.getOperating(Integer.parseInt(patientId),price);
    }

    //متد واسه ارسال پیغام به پزشک
}
