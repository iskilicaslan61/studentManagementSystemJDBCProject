import java.sql.*;

// class which we will create connection to DB(connection
// ,Statements, PrepareStatements )
public class StudentRepository {

   public Connection con;
   public Statement statement;
   public PreparedStatement prs;


    //3-method for connection
    public void getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/jdbc_db",
                    "dev_user", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //4- method to create statements
    public void getStatements(){
        try {
            this.statement=con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //5- create preParedStatements
    public void getPreparedStatements(String query){
        try {
            this.prs = con.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //6-method create Table

    public void createTable(){
        getConnection();//create connection
        getStatements();//to be able to run queries

        String query="CREATE TABLE IF NOT EXISTS tbl_student (id SERIAL , name VARCHAR(50), lastname VARCHAR(50)," +
                "city VARCHAR(30), age INT ) ";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                statement.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        
  
    }

    //step-10 method to persist data into database


    public void save(Student newStudent) {

        getConnection();
        String query="INSERT INTO tbl_student(name,lastname,city,age) VALUES(?,?,?,?)";
        getPreparedStatements(query);
        try {
            prs.setString(1,newStudent.getName());
            prs.setString(2,newStudent.getLastName());
            prs.setString(3,newStudent.getCity());
            prs.setInt(4,newStudent.getAge());
            prs.executeUpdate();//to persist/store values into table/DB
            System.out.println("Student registered successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                prs.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


    }

    //step 12- //method to bring all students from DB
    public void findAll() {
        getConnection();
        String query = "SELECT * FROM tbl_student";
        getStatements();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.print("id:"+resultSet.getInt("id"));
                System.out.print(" Name:"+resultSet.getString("name"));
                System.out.print(" LastName: "+resultSet.getString("lastname"));
                System.out.print(" City:"+resultSet.getString("city"));
                System.out.print(" Age:"+resultSet.getInt("age"));
                System.out.println(); //to start from new line for info about next student


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                statement.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    //step 14- method to find student from table by provided id
    public Student findStudentById(int id) {
        getConnection();
        Student student=null;
        String query = "SELECT * FROM tbl_student WHERE id=?";

        try {
            getPreparedStatements(query);
            prs.setInt(1, id);
            //Data type coming from table is result set
           ResultSet resultSet= prs.executeQuery();
           //if resultSet has some value(there is a student information)
           while(resultSet.next()) {
               student=new Student();

               student.setId(resultSet.getInt("id"));
               student.setName(resultSet.getString("name"));
               student.setLastName(resultSet.getString("lastName"));
               student.setCity(resultSet.getString("city"));
               student.setAge(resultSet.getInt("age"));

           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }finally {
            try {
                prs.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        return student;
    }

    //step 16-method that will run query to delete student from table
    public void delete(int id) {
        getConnection();
        String query="DELETE FROM tbl_student WHERE id=?";
        getPreparedStatements(query);
        try {
            prs.setInt(1, id);
         int deletedRows = prs.executeUpdate();//number of deleted rows
            if (deletedRows > 0) {
                System.out.println("Student with id : "+id+" is deleted successfully");
            }else {
                System.out.println("Student with id : "+id+" could not be found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

//step 18- method to run update query for to update student information
    public void update(Student newStudentInfo) {
        getConnection();
        String query = "UPDATE tbl_student SET name=?,lastname=?,city=?,age=? WHERE id=?";
        getPreparedStatements(query);
        try {
            prs.setString(1,newStudentInfo.getName());
            prs.setString(2,newStudentInfo.getLastName());
            prs.setString(3,newStudentInfo.getCity());
            prs.setInt(4,newStudentInfo.getAge());
            prs.setInt(5,newStudentInfo.getId());
            int updated=prs.executeUpdate();
            if (updated > 0) {
                System.out.println("Student updated successfully");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                prs.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


    }
}