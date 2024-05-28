public class Management {
    private String password;
    private Staff staff;
    private Income income;

    public Management() {
        this.password = "1234";
        this.staff = new Staff();
        this.income = new Income();
    }

    public void changePassword(String lastPassword, String newPassword) {
        if (lastPassword.equals(this.password)) {
            this.password = newPassword;
        }
    }

    public void addStaff(String password, String firstName, String lastName, int staffID, int identifyID, String unit) {
        if (password.equals(this.password)) {
            Staff newStaff = new Staff(firstName, lastName, staffID, identifyID, unit);
            this.staff.addStaff(newStaff);
        }
    }

    public void changUnit(String password, int staffID, String newUnit) {
        if (password.equals(this.password)) {
            this.staff.changUnit(staffID, newUnit);
        }
    }

    public boolean removeStaff(String password, int staffID) {
        if (password.equals(this.password)) {
            return this.staff.removeStaff(staffID);
        } else {
            return false;
        }
    }

    public String getStaffInformation(String password, int staffID) {
        if (password.equals(this.password)) {
            return this.staff.getStaffInformation(staffID);
        } else {
            return null;
        }
    }

    public int getFee() {
        return this.income.getFee();
    }

    public void setFee(int fee) {
        this.income.setFee(fee);
    }

    public int getStaffFee(String password, int staffID) {
        if (password.equals(this.password)) {
            return this.income.getStaffFee(staffID);
        } else {
            return 0;
        }
    }

    public String readStaffReport(String password, int staffID) {
        return (password.equals(this.password)) ? this.income.getReportStaff(staffID) : null;
    }

}
