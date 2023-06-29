import java.util.Scanner;

public class Runner {

      /*
    MiniProject: Student Management System
        1. Create Student Management System that can be used by any educational institution
        2. User (Admin) can: CRUD operations
            -register user (id, name, lastName, city, age fields)
            -list/display students/student
            -update student by id
            -delete student by id

 */

    //1. Create menu
    //2. Create Student class(Entity)
    //3. create method for student
    //4. create class to connect database
    public static void main(String[] args) {

        start();
    }

    public static void start() {
        Scanner input = new Scanner(System.in);

        //to reach method from  service class we need to create instance of StudentService class
        StudentService service = new StudentService();
        //8-table should be created when start() method is called..
        service.CreateTable();
        System.out.println("Table is created successfully");



        int select;

        do {

            System.out.println("-------------------------------");
            System.out.println("------------Student Admin Panel-------------");
            System.out.println("1-Student Register .");
            System.out.println("2-List All student .");
            System.out.println("3-Delete Student .");
            System.out.println("4-Update Student .");
            System.out.println("5.Find student by Id .");
            System.out.println("6-Exit .");
            System.out.println("Select Activity :");

            select = input.nextInt();
            input.nextLine();//to call next enter/new line

            int id;
            switch (select) {
                case 1:
                    //Register
                    service.saveStudent();
                    break;
                case 2:
                    //disülay all student
                    service.getAllStudents();
                    break;
                case 3:
                    //delete student by Id
                    id=getStudentbyId(input);
                    service.deleteStudent(id);
                    break;
                case 4:
                    //Update student by Id
                    id=getStudentbyId(input);
                    service.updateStudent(id);
                    break;
                case 5:
                    //find student by Id
                    id=getStudentbyId(input);
                    Student student=service.getStudentById(id);
                    System.out.println(student);
                    break;
                    //exit app.
                case 0:
                    System.out.println("Thank you for using app. Have a nice time!");
                    break;


                default:
                    System.out.println("Incorrect Input. Try numbers between 0 and 5");
            }

        }while (select!=0);
    }

    public static int getStudentbyId(Scanner inp){
        System.out.println("Enter Student Id :");
        int id= inp.nextInt();
        inp.nextLine();
        return id;
    }

}
