import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CTScan extends Patient {
    private static ArrayList<Integer> history;
    private static ArrayList<String> request;

    public CTScan() {

    }

    public CTScan(String string) {
        history = new ArrayList<>();
        request = new ArrayList<>();
    }

    public void newRequest(ArrayList<String> info,String request) {
        info.add(request);
        this.request.add(arrayToText(info));
    }

    public String[] getNextPatient() {
        String[] newRequest = request.get(0).split("\n");
        request.remove(0);
        history.add(Integer.valueOf(newRequest[0]));
        return newRequest;
    }

    public void toScan(int id, String report) throws IOException {
        FileWriter fileWrite = new FileWriter("report" + id);
        Date date = new Date();
        fileWrite.write("در تاریخ :" + date.getYear() + "/" + date.getMonth() + "/" + date.getDay() + report);
        fileWrite.write("image path" + history.size() +".png");
        //ذخیره عکسی که آدرسش توی خط بالا در پرونده ذخیره شد
        fileWrite.close();
        Billing billing = new Billing();
        billing.getTest(id,"CTScan");
        // اینجا نیازه که عکس سی تی اسکن ارسال بشه به پنل پزشک
    }

    public String[] patientList() {
        String[] names = new String[request.size()];
        for (int i = 0; i < request.size(); i++) {
            readerDetailsFile(Integer.parseInt(request.get(i)));
            names[i] = getFirstName() + " " + getLastName();
        }
        return names;
    }

    public String arrayToText(ArrayList<String> input) {
        String text = null;
        for (int i =0 ; i < input.size(); i++) {
            text += input.get(i);
            text += "\n";
        }
        return text;
    }

}
