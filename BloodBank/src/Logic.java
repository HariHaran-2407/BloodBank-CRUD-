import java.sql.*;
import java.util.ArrayList;

public class Logic {

    static final String DB_URL = "jdbc:mysql://localhost:3306/blooddonor";
    static final String USER = "root";
    static final String PASS = "root";

    public boolean addDonors(Main.Donor donors){


        String query = "INSERT INTO donors(DonorName, BloodGroup, StudentId, CollegeName , DonorContact, DonorEmail ) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        boolean flag = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            System.out.println("Connected to the database successfully");


            preparedStatement.setString(1, donors.getDonorName());
            preparedStatement.setString(2, donors.getBloodGroup());
            preparedStatement.setString(3, donors.getStudentId());
            preparedStatement.setString(4, donors.getCollegeName());
            preparedStatement.setString(5, donors.getDonorContact())    ;
            preparedStatement.setString(6, donors.getDonorEmail());


            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                flag = true;
            }

            conn.close();

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 2627) {
                System.out.println("This record already exists in the database. Enter a valid user id.");
            } else {
                System.out.println("SQL Exception: " + ex.getMessage());
                System.out.println("Error Code: " + ex.getErrorCode());
                System.out.println("SQL State: " + ex.getSQLState());
            }
        }
        return flag;
    }

    public boolean updateDonor(Main.Donor donors, int studid, int updopt) {

        String query="";



        //query = "UPDATE donors SET DonorName = ?, BloodGroup = ?, CollegeName = ?, DonorContact = ?, DonorEmail = ? WHERE StudentId = ?";

        boolean flag = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            System.out.println("Connected to the database successfully");

            switch (updopt){
                case 1:
                    query = "UPDATE donors SET DonorName = ? WHERE StudentId = ?";
                    preparedStatement.setString(1, donors.getDonorName());
                    preparedStatement.setString(2, donors.getStudentId());
                    break;
                case 2:
                    query = "UPDATE donors SET BloodGroup = ? WHERE StudentId = ?";
                    preparedStatement.setString(1, donors.getBloodGroup());
                    preparedStatement.setString(2, donors.getStudentId());
                    break;
                case 3:
                    query = "UPDATE donors SET CollegeName = ? WHERE StudentId = ?";
                    preparedStatement.setString(1, donors.getCollegeName());
                    preparedStatement.setString(2, donors.getStudentId());
                    break;
                case 4:
                    query = "UPDATE donors SET DonorContact = ? WHERE StudentId = ?";
                    preparedStatement.setString(1, donors.getDonorContact());
                    preparedStatement.setString(2, donors.getStudentId());
                    break;
                case 5:
                    query = "UPDATE donors SET DonorEmail = ? WHERE StudentId = ?";
                    preparedStatement.setString(1, donors.getDonorEmail());
                    preparedStatement.setString(2, donors.getStudentId());
                    break;

            }
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                flag = true;
                System.out.println("Record updated successfully");
            } else {
                System.out.println("No record found with the given StudentId.");
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("SQL State: " + ex.getSQLState());
        }

        return flag;


    }
    public boolean deleteDonor(Main.Donor donors, int studid) {
        String query = "DELETE FROM donors WHERE StudentId = ?";

        boolean flag = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            System.out.println("Connected to the database successfully");

            // Set the value for the prepared statement (ID of the donor to be deleted)
            preparedStatement.setString(1, String.valueOf(studid));

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                flag = true;
                System.out.println("Donor with StudentId " + studid + " deleted successfully");
            } else {
                System.out.println("No record found with the given StudentId.");
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("SQL State: " + ex.getSQLState());
        }

        return flag;

    }

    public ArrayList<Main.Donor> viewAllDonors() {
        ArrayList<Main.Donor> donors = new ArrayList<>();
        String query = "SELECT DonorId, DonorName, BloodGroup, StudentId, CollegeName, DonorContact, DonorEmail FROM donors";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Main.Donor donor = new Main.Donor();
                donor.setDonorId(Integer.parseInt(resultSet.getString("DonorId")));
                donor.setDonorName(resultSet.getString("DonorName"));
                donor.setBloodGroup(resultSet.getString("BloodGroup"));
                donor.setStudentId(resultSet.getString("StudentId"));
                donor.setCollegeName(resultSet.getString("CollegeName"));
                donor.setDonorContact(resultSet.getString("DonorContact"));
                donor.setDonorEmail(resultSet.getString("DonorEmail"));
                donors.add(donor);
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("SQL State: " + ex.getSQLState());
        }

        return donors;
    }

    public ArrayList<Main.Donor> viewDonor(String bg) {
        ArrayList<Main.Donor> donors = new ArrayList<>();
        String query = "SELECT DonorName, BloodGroup, StudentId, CollegeName, DonorContact, DonorEmail FROM donors WHERE BloodGroup = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, bg);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Main.Donor donor = new Main.Donor();
                donor.setDonorName(resultSet.getString("DonorName"));
                donor.setBloodGroup(resultSet.getString("BloodGroup"));
                donor.setStudentId(resultSet.getString("StudentId"));
                donor.setCollegeName(resultSet.getString("CollegeName"));
                donor.setDonorContact(resultSet.getString("DonorContact"));
                donor.setDonorEmail(resultSet.getString("DonorEmail"));
                donors.add(donor);
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("SQL State: " + ex.getSQLState());
        }

        return donors;

    }
}
