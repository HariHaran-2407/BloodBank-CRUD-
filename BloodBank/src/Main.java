import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {

    static Donor donors = new Donor();
    static Logic lo=new Logic();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        //ArrayList<Donor> donors = new ArrayList<>();
        String DonorName;
        String BloodGroup;
        String StudentId;
        String CollegeName;
        long  DonorContact;
        String DonorEmail;
        System.out.println("===============================================================================================================================================");
        System.out.println("Blood Bank Management");
        System.out.println("===============================================================================================================================================");


        char o='y';

        while(o=='y'){
            System.out.println("Enter Options:");
            System.out.println("1.Add \n2.Update \n3.View By BloodGroup \n4.View All \n5.Delete \n6.Exit");
            int option=in.nextInt();
            System.out.println("===============================================================================================================================================");
            switch(option){
                case 1:
                    addDonor();break;
                case 2:
                    updateDonor();break;
                case 3:
                    viewDonor();break;
                case 4:
                    viewDonors();break;
                case 5:
                    deleteDonor();break;
                case 6:
                    o='n';break;
                default :
                    System.out.println("Enter Valid Option");break;
            }
            System.out.println("===============================================================================================================================================");

        }
    }


    private static void addDonor() {
        Scanner in = new Scanner(System.in);
        System.out.println("Adding Donor:");
        System.out.println("Enter Donor Name");
        Donor.DonorName=in.nextLine();
        System.out.println("Enter Donor BloodGroup");
        Donor.BloodGroup=in.nextLine();
        Donor.BloodGroup=Donor.BloodGroup.toUpperCase();
        System.out.println("Enter Student Id");
        Donor.StudentId=in.nextLine();
        System.out.println("Enter Donor College Name");
        Donor.CollegeName=in.nextLine();
        System.out.println("Enter Donor Contact");
        Donor.DonorContact=in.nextLine();
        System.out.println("Enter Donor Email");
        Donor.DonorEmail=in.nextLine();

        if(lo.addDonors(donors)){
            System.out.println("Donor Added Successfully");
        }
        else {
            System.out.println("Donor Not added");
        }

    }


    private static void updateDonor() {

        Scanner in = new Scanner(System.in);
        System.out.println("Updating Donor:");
        System.out.println("Enter Student ID:");
        int studid=in.nextInt();
        System.out.println("Enter Options:\n 1.DonorName \n2.Donor BloodGroup\n3.Student ID\n4.Donor CollegeName\n5.Donor Contact\n6.Donor Email");
        int updopt= in.nextInt();

        switch (updopt){
            case 1:
                System.out.println("Enter Donor Name");
                Donor.DonorName=in.nextLine();
                break;
            case 2:
                System.out.println("Enter Donor BloodGroup");
                Donor.BloodGroup=in.nextLine();
                break;
            case 3:
                System.out.println("Enter Student Id");
                Donor.StudentId=in.nextLine();
                break;
            case 4:
                System.out.println("Enter Donor College Name");
                Donor.CollegeName=in.nextLine();
                break;
            case 5:
                System.out.println("Enter Donor Contact");
                Donor.DonorContact=in.nextLine();
                break;
            case 6:
                System.out.println("Enter Donor Email");
                Donor.DonorEmail=in.nextLine();
                break;
            default :
                System.out.println("Enter valid Option");
                break;
        }


        if(lo.updateDonor(donors,studid,updopt)){
            System.out.println("Donor Updated Successfully");
        }
        else {
            System.out.println("Donor Not added");
        }
    }

    private static void deleteDonor() {
        Scanner in=new Scanner(System.in);
        System.out.println("Delete Donor");
        System.out.println("Enter Student ID:");
        int studid=in.nextInt();
        if(lo.deleteDonor(donors,studid)){
            System.out.println("Donor Deleted Successfully");
        }
        else {
            System.out.println("Donor Not Deleted");
        }
    }

    private static void viewDonors() {

        System.out.println("View All Donors:");
        ArrayList<Donor> res=lo.viewAllDonors();
        if(res.isEmpty()){
            System.out.println("No Donors are Added");
        }
        else {
            System.out.println(res);
        }

    }

    private static void viewDonor() {
        Scanner in=new Scanner(System.in);
        System.out.println("View Donor By Blood Group:");
        System.out.println("Enter Blood Group:");
        String bg=in.nextLine();
        bg=bg.toUpperCase();
        ArrayList<Donor> res=lo.viewDonor(bg);
        if(res.isEmpty()){
            System.out.println("No Donors are in " + bg+  " Blood Group");
        }
        else {
            System.out.println(res);
        }
    }






        static class Donor  {



        public static int DonorId;
        public static String DonorName;
        public static String BloodGroup;
        public static String StudentId;
        public static String CollegeName;
        public static String  DonorContact;
        public static String DonorEmail;

        public Donor() {
            this.DonorId=DonorId;
            this.DonorName = DonorName;
            this.BloodGroup = BloodGroup;
            this.StudentId = StudentId;
            this.CollegeName = CollegeName;
            this.DonorContact = DonorContact;
            this.DonorEmail = DonorEmail;
        }



        @Override
        public String toString() {
            String format = "| %-15s | %-15s | %-12s | %-12s | %-20s | %-15s | %-25s |%n";
            return String.format(format,"Donor Id", "Donor Name", "Blood Group", "Student ID",
                    "College Name", "Contact", "Email") +
                    String.format(format,DonorId, DonorName, BloodGroup, StudentId,
                            CollegeName, DonorContact, DonorEmail);
        }



        public static int getDonorId() {
            return DonorId;
        }

        public static void setDonorId(int donorId) {
            DonorId = donorId;
        }
        public String getDonorEmail() {
            return DonorEmail;
        }

        public void setDonorEmail(String donorEmail) {
            DonorEmail = donorEmail;
        }

        public String getDonorName() {
            return DonorName;
        }

        public void setDonorName(String donorName) {
            DonorName = donorName;
        }

        public String getBloodGroup() {
            return BloodGroup;
        }

        public void setBloodGroup(String bloodGroup) {
            BloodGroup = bloodGroup;
        }

        public String getStudentId() {
            return StudentId;
        }

        public void setStudentId(String studentId) {
            StudentId = studentId;
        }

        public String getCollegeName() {
            return CollegeName;
        }

        public void setCollegeName(String collegeName) {
            CollegeName = collegeName;
        }

        public String getDonorContact() {
            return DonorContact;
        }

        public void setDonorContact(String DonorContact) {
            DonorContact = DonorContact;
        }


    }

}