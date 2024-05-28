import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Staff {
    private String firstName;
    private String lastName;
    private int staffID;
    private int identifyID;
    private String password;
    private String unit;
    private static HashMap<Integer, String> staffLog;

    public Staff() {
    }

    public Staff(String firstName, String lastName, int staffID, int identifyID, String unit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.staffID = staffID;
        this.identifyID = identifyID;
        this.unit = unit;
    }

    public void setFirstName(String input) {
        this.firstName = input;
    }

    public void setLastName(String input) {
        this.lastName = input;
    }

    public void setIdentifyID(int input) {
        this.identifyID = input;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPassword(String input) {
        this.password = input;
    }

    public Staff(String string) {
        staffLog = new HashMap<>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getIdentifyID() {
        return this.identifyID;
    }

    public String getUnit() {
        return this.unit;
    }

    public int getStaffID() {
        return this.staffID;
    }

    public void addStaff(Staff staff) {
        this.firstName = staff.getFirstName();
        this.lastName = staff.getLastName();
        this.identifyID = staff.getIdentifyID();
        this.staffID = staff.getStaffID();
        this.unit = staff.getUnit();
        staffLog.put(this.staffID, String.valueOf(this.identifyID));
        try {
            File file = new File("Staff" + staffID + ".txt");
            if (file.createNewFile()) {
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changUnit(int staffID, String newUnit) {
        Staff staff = readerFile(staffID);
        staff.setUnit(newUnit);
        File staffFile = new File("Staff" + staffID + ".txt");
        staffFile.delete();
        writeFile(staffID, staff);
    }

    public boolean removeStaff(int staffID) {
        File file = new File("Staff" + staffID + ".txt");
        staffLog.remove(staffID);
        return file.delete();
    }

    public void writeFile(int staffID, Staff staff) {
        try {
            FileWriter writer = new FileWriter("Staff" + staffID + ".txt");
            writer.write(staff.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Staff readerFile(int staffID) {
        Staff staff = new Staff();
        try {
            File file = new File("Staff" + staffID + ".txt");
            Scanner scanner = new Scanner(file);
            staff.setFirstName(scanner.nextLine());
            staff.setLastName(scanner.nextLine());
            staff.setStaffID(scanner.nextInt());
            staff.setIdentifyID(scanner.nextInt());
            staff.setUnit(scanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return staff;
    }

    public String getStaffInformation(int staffID) {
        StringBuilder answer = new StringBuilder();
        try {
            File file = new File("Staff" + staffID + ".txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                answer.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return String.valueOf(answer);
    }

    public boolean isCorrectPassword(int staffID, String password) {
        return Objects.equals(staffLog.get(staffID), password);
    }

    public void changPassword(int staffID, String password) {
        if (!staffLog.containsKey(staffID)) {
            System.out.println("there is not this person in this hospital");
        } else {
            staffLog.put(staffID, password);
        }
    }


    public String toString() {
        return this.firstName + "\n" + this.lastName + "\n" + this.staffID + "\n" + this.identifyID + "\n" + this.unit;
    }
}
