public class Staff {

    public String staffName;
    public String staffGender;
    public int employeeID;
    public String jobTitle;

    public Staff(String name, String gender, int employeeID, String jobTitle){
        this.staffName = name;
        this.staffGender = gender;
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
    }

    public String getStaffName(){
        return staffName;
    }

    public String getStaffGender(){
        return staffGender;
    }

    public int getEmployeeID(){
        return employeeID;
    }

    public String getJobTitle(){
        return jobTitle;
    }

}
