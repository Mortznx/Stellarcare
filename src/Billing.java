import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class Billing {
    private HashMap<String, Integer> prices;
    private HashMap<String,Integer> bill;

    public Billing() {
    }

    public Billing(String st) {
        this.prices = new HashMap<>();
    }

    public void changPrice(String option, int price) {
        this.prices.put(option, price);
    }

    public int getPrice(String option) {
        return this.prices.get(option);
    }

    public String getAllPrices() {
        String answer = "";
        for (String i : this.prices.keySet()) {
            answer += i + " " + this.prices.get(i) + ",";
        }
        return answer;
    }

    public void getEmergency(int id) {
        try {
            File file = new File("BILLING" + id + ".txt");
            if (file.createNewFile()) {
                System.out.println();
            }
            writeBillReport(id, "start"+ ":"+ dateNow());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void changUnit(int id, String oldUnit, String newUnit) {
        writeBillReport(id, "Chang unit" + ":"+dateNow() + "\\$" + newUnit);
    }

    public void getTest(int id, String input) {
        writeBillReport(id, input);
    }

    public void getOperating(int id, int price) {
        writeBillReport(id, "get operating" + ":" + price);
    }
    public void discharging(int id) {
        writeBillReport(id,"discharging" +":"+ dateNow());
    }

    public void getBilling(int id) {
        String unit = "";
        String firstDate = "";
        String lastDate = "";
        try {
            File file = new File("BILLING" + id + ".txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String[] text = scan.nextLine().split(":");
                switch (text[0]) {
                    case "start" :
                        unit = "emergency";
                        firstDate = text[1];
                    case "chang unit" :
                        String[] st = text[1].split("\\$");
                        lastDate = st[0];
                        if (this.bill.containsKey(unit)) {
                            this.bill.put(unit,this.bill.get(unit) + dayCounter(firstDate,lastDate)*this.prices.get(unit));
                        } else {
                            this.bill.put(unit,dayCounter(firstDate,lastDate)*this.prices.get(unit));
                        }
                        firstDate = lastDate;
                        lastDate = "";
                        unit = st[1];
                    case "CTScan" :
                        if (this.bill.containsKey(unit)) {
                            this.bill.put(unit,this.bill.get(unit) + this.prices.get("CTScan"));
                        } else {
                            this.bill.put(unit,this.prices.get("CTScan"));
                        }
                    case "get operating" :
                        if (this.bill.containsKey(unit)) {
                            this.bill.put(unit,this.bill.get(unit) + Integer.parseInt(text[1]));
                        } else {
                            this.bill.put(unit,Integer.parseInt(text[1]));
                        }
                    case "discharging" :
                        String[] str = text[1].split("\\$");
                        lastDate = str[0];
                        if (this.bill.containsKey(unit)) {
                            this.bill.put(unit,this.bill.get(unit) + dayCounter(firstDate,lastDate)*this.prices.get(unit));
                        } else {
                            this.bill.put(unit,dayCounter(firstDate,lastDate)*this.prices.get(unit));
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int pay(int id) {
        for (String i : this.bill.keySet()) {
            this.bill.putIfAbsent("pay",this.bill.get(i) + this.bill.get("pay"));
        }
        return this.bill.get("pay");
    }
    public void getPay(int id) {
        File file = new File("BILLING" + id + ".txt");
        file.delete();
    }

    public void writeBillReport(int id, String text) {
        try {
            FileWriter writer = new FileWriter("BILLING" + id + ".txt");
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int dayCounter(String string) {
        String[] input = string.split("/");
        LocalDate past = LocalDate.of(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]));
        LocalDate localDate = LocalDate.now();
        int year = (localDate.getYear() - past.getYear());
        int month = (localDate.getMonthValue() - past.getMonthValue());
        int day = (localDate.getDayOfMonth() - past.getDayOfMonth());
        month = (month < 0) ? month + 12 : month;
        day = (day < 0) ? day + 30 : day;
        return ((year * 365) + (month * 30) + day);
    }

    public int dayCounter(String input1, String input2) {
        String[] first = input1.split("/");
        String[] last = input2.split("/");
        LocalDate past = LocalDate.of(Integer.parseInt(first[0]), Integer.parseInt(first[1]), Integer.parseInt(first[2]));
        LocalDate localDate = LocalDate.of(Integer.parseInt(last[0]), Integer.parseInt(last[1]), Integer.parseInt(last[3]));
        int year = (localDate.getYear() - past.getYear());
        int month = (localDate.getMonthValue() - past.getMonthValue());
        int day = (localDate.getDayOfMonth() - past.getDayOfMonth());
        month = (month < 0) ? month + 12 : month;
        day = (day < 0) ? day + 30 : day;
        return ((year * 365) + (month * 30) + day);
    }

    public String dateNow() {
        LocalDate time = LocalDate.now();
        return time.getYear() + "/" + time.getMonthValue() + "/" + time.getYear();
    }

    /*public int FinalCost() {
        for (int i : this.operatonPays) {
            this.pay += this.operatonPays.get(i);
        }
        return 0;
    }*/
}
