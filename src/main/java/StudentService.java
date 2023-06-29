import java.util.Scanner;

//this class is Service or Business Logic
    public class StudentService {
        //to get access to method in studentRepo
        StudentRepository repository = new StudentRepository();
        Scanner input = new Scanner(System.in);


        //step 7 . method to call createTable method  rom Repository

        public void CreateTable() {
            repository.createTable();
        }


        //step 9: method to register student
        public void saveStudent() {
            System.out.println("Name: ");
            String name = input.nextLine();
            System.out.println("Last Name: ");
            String lastName = input.nextLine();
            System.out.println("City: " );
            String city = input.nextLine();
            System.out.println("Age: ");
            int age = input.nextInt();
            input.nextLine();//to call next enter/new line

            //After getting all data from user, we create new student object using info enter by user
            Student newStudent = new Student(name,lastName,city,age);
            //new student should be saved in our DB
            repository.save(newStudent);
        }

        //step 11- method to display students in a List
    public void getAllStudents(){

            repository.findAll();
    }

    //step 13- method to find student by id

    public Student getStudentById(int id){
          Student student = repository.findStudentById(id);
        return student;
    }

    //step 15- method to delete student by id
    public void deleteStudent(int id){
            //we can handle error message if there is no student find with id
        repository.delete(id);
    }
    //step 17- method to update student information
    public void updateStudent(int id){

         //find student from table
        Student existingStudent=getStudentById(id);
        //check if user exist in table or not
        if (existingStudent == null) {
            System.out.println("Student with id "+id+" not found");
        }else {//We have found one student and we can start updating
        //ask user to enter new data to be updated
            System.out.println("Name: ");
            String name = input.nextLine();
            System.out.println("Last Name: ");
            String lastName = input.nextLine();
            System.out.println("City: " );
            String city = input.nextLine();
            System.out.println("Age: ");
            int age = input.nextInt();
            input.nextLine();//to call next enter/new line

            //start update fields of existing students
            existingStudent.setName(name);
            existingStudent.setLastName(lastName);
            existingStudent.setCity(city);
            existingStudent.setAge(age);
            //existingStudent.setId(); //id should remain same


            repository.update(existingStudent);



        }


    }








    }

