import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Income {
    private int fee;

    public Income() {
        fee = 0;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getFee() {
        return this.fee;
    }

    public int getStaffFee(int staffID) {
        int workingHour = getCounter(staffID);
        int fee = workingHour * this.fee;
    }

    public String getReportStaff(int staffID) {
        return fileReader(staffID);
    }

    public int getCounter(int staffID) {
        int[] timeAll = new int[2];
        String text = "";
        String[] hourStart = new String[2];
        String[] hourEnd = new String[2];
        try {
            File staffFile = new File("Staff" + staffID + ".txt");
            Scanner scanner = new Scanner(staffFile);
            while (scanner.hasNextLine()) {
                text = scanner.nextLine();
                String[] st = text.split(" ");
                if (st[0].equals("start") && st[1].equals("working")) {
                    hourStart = st[3].split(":");
                } else if (st[0].equals("end") && st[1].equals("of") && st[2].equals("working")) {
                    hourEnd = st[4].split(":");
                }
                int[] jjj = hourCounter(hourStart, hourEnd);
                timeAll[0] += jjj[0];
                timeAll[1] += jjj[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (timeAll[1] >= 60) {
            timeAll[1] -= 60;
            timeAll[0] += 1;
        }
        return timeAll[0];
    }

    public String fileReader(int staffID) {
        String texts = "";
        try {
            File file = new File("Staff" + staffID + ".txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                texts += scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texts;
    }

    public int[] hourCounter(String[] start, String[] end) {
        int[] times = new int[2];
        if (Integer.parseInt(end[1]) < Integer.parseInt(start[1]))
            start[1] = String.valueOf(Integer.parseInt(end[1] + 60));
        if (Integer.parseInt(end[0]) < Integer.parseInt(start[0]))
            start[0] = String.valueOf(Integer.parseInt(end[0] + 24));
        for (int i = 0; i < 2; i++) {
            times[i] = Integer.parseInt(end[i]) - Integer.parseInt(start[i]);
        }
        return times;
    }
}
