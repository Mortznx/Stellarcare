import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Patient {
    public String st;
    private LocalDate localDate;
    private String firstName;
    private String lastName;
    private String fatherName;
    private boolean isMale;
    private boolean single;
    private long identifyID;
    private String birthday;
    private String birthdayLocation;
    private String location;
    private String phone;
    private String companion;
    private String companionPhone;
    private String arrivalDate;
    private int getCheckUp = 0;
    private int id;
    private static int newId;

    public Patient() {
    }

    public Patient(String st) {
        newId = 0;
    }

    public Patient(String firstName , String lastName, String fatherName, boolean isMale,
                   boolean single,long identifyID,String birthday,String birthdayLocation,
                   String location,String phone, String companion, String companionPhone,
                   String arrivalDate,int getCheckUp ,int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.isMale = isMale;
        this.single = single;
        this.identifyID = identifyID;
        this.birthday = birthday;
        this.birthdayLocation = birthdayLocation;
        this.location = location;
        this.phone = phone;
        this.companion = companion;
        this.companionPhone = companionPhone;
        this.arrivalDate = arrivalDate;
        this.getCheckUp = getCheckUp;
        this.id = id;
    }

    private String getDate() {
        this.localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        return (year + "/" + month + "/" + day);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setMale(boolean gender) {
        this.isMale = gender;
    }

    public void setSingle(boolean isSingle) {
        this.single = isSingle;
    }

    public void setIdentifyID(long identifyID) {
        this.identifyID = identifyID;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setBirthdayLocation(String birthdayLocation) {
        this.birthdayLocation = birthdayLocation;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCompanion(String companion) {
        this.companion = companion;
    }

    public void setCompanionPhone(String phone) {
        this.companionPhone = phone;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGetCheckUp(int check) {
        this.getCheckUp = check;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public boolean getIsMale() {
        return this.isMale;
    }

    public boolean getSingle() {
        return this.single;
    }

    public long getIdentifyID() {
        return this.identifyID;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String getBirthdayLocation() {
        return this.birthdayLocation;
    }

    public String getLocation() {
        return this.location;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getCompanion() {
        return this.companion;
    }

    public String getCompanionPhone() {
        return this.companionPhone;
    }

    public String getArrivalDate() {
        return this.arrivalDate;
    }

    public int getGetCheckUp() {
        return this.getCheckUp;
    }

    public int getId() {
        return this.id;
    }

    public int getNewId() {
        newId += 1;
        return newId;
    }

    public void writerDetailsFile(int id) {
        try {
            File detailsFile =new File("details" + newId);
            if (detailsFile.createNewFile()) {
                System.out.println("file created!");
            } else {
                System.out.println();
            }
            FileWriter fileWriter = new FileWriter("details" + id);
            fileWriter.write(toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writerReportFile(int id, String text) {
        try {
            File reportFile = new File("report" + newId);
            if (reportFile.createNewFile()) {
                System.out.println("file created!");
            } else {
                System.out.println();
            }
            FileWriter fileWriter = new FileWriter("report" + id);
            fileWriter.write(getDate() + text);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readerDetailsFile(int id) {
        try {
            File detailsFile = new File("details" + newId);
            Scanner scanner = new Scanner(detailsFile);
            setFirstName(scanner.nextLine());
            setLastName(scanner.nextLine());
            setFatherName(scanner.nextLine());
            setMale(Boolean.parseBoolean(scanner.nextLine()));
            setSingle(Boolean.parseBoolean(scanner.nextLine()));
            setIdentifyID(Long.parseLong(scanner.nextLine()));
            setBirthday(scanner.nextLine());
            setBirthdayLocation(scanner.nextLine());
            setLocation(scanner.nextLine());
            setPhone(scanner.nextLine());
            setCompanion(scanner.nextLine());
            setCompanionPhone(scanner.nextLine());
            setArrivalDate(scanner.nextLine());
            setId(Integer.parseInt(scanner.nextLine()));
            setGetCheckUp(Integer.parseInt(scanner.nextLine()));
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void changeCheckUpIng(int id, int count) {
        File detailsFile = new File("details" + newId);
        String information = null;
        try {
            Scanner scan = new Scanner(detailsFile);
            for (int i = 0; i < 15; i++) {
                if (i == 14) {
                    information += String.valueOf(count);
                    writingNewDetailsFile(id, information);
                } else {
                    information += scan.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writingNewDetailsFile(int id, String input) {
        File detailsFile = new File("details" + newId);
        if (detailsFile.exists()) {
            detailsFile.delete();
        }
        try {
            detailsFile.createNewFile();
            FileWriter writer = new FileWriter("details" + id);
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readerReportFile(int id) {
        File reportFile = new File("report" + newId);
        String answer = null;
        try {
            Scanner scanner = new Scanner(reportFile);
            while (scanner.hasNextLine()) {
                answer += scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public void arrayToStrings(ArrayList<String> input) {
        setFirstName(input.get(0));
        setLastName(input.get(1));
        setFatherName(input.get(2));
        setMale(Boolean.parseBoolean(input.get(3)));
        setSingle(Boolean.parseBoolean(input.get(4)));
        setIdentifyID(Long.parseLong(input.get(5)));
        setBirthday(input.get(6));
        setBirthdayLocation(input.get(7));
        setLocation(input.get(8));
        setPhone(input.get(9));
        setCompanion(input.get(10));
        setCompanionPhone(input.get(11));
        setArrivalDate(input.get(12));
        setId(Integer.parseInt(input.get(13)));
        setGetCheckUp(Integer.parseInt(input.get(14)));
    }

    public String toStringNew() {
        return this.firstName
                + "\n" + this.lastName
                + "\n" + this.fatherName
                + "\n" + this.isMale
                + "\n" + this.single
                + "\n" + this.identifyID
                + "\n" + this.birthday
                + "\n" + this.birthdayLocation
                + "\n" + this.location
                + "\n" + this.phone
                + "\n" + this.companion
                + "\n" + this.companionPhone
                + "\n" + this.arrivalDate
                + "\n" + newId
                + "\n" + this.getCheckUp;
    }

    public String toString() {
        return this.firstName
                + "\n" + this.lastName
                + "\n" + this.fatherName
                + "\n" + this.isMale
                + "\n" + this.single
                + "\n" + this.identifyID
                + "\n" + this.birthday
                + "\n" + this.birthdayLocation
                + "\n" + this.location
                + "\n" + this.phone
                + "\n" + this.companion
                + "\n" + this.companionPhone
                + "\n" + this.arrivalDate
                + "\n" + this.id        //13
                + "\n" + this.getCheckUp;
    }
}
