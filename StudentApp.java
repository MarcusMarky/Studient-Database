import java.util.Scanner;

public class StudentApp {
public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
    StudentDataBase.students = new Student[10];
    int option;
    String secondOptions = "";
    while (true){
        //HW6/7:
        option = consoleUI.printMenu();
        
       switch (option) {
                case 1:
                    StudentDataBase.printAllStudents();
                    break;
                case 2:
                    System.out.println("Enter student's data: ");
                    StudentDataBase.addStudent(in.nextLine(), in.nextLine(), in.nextInt());
                    in.nextLine();
                    break;
                case 3:
                   System.out.println("Enter student's index to delete: ");
                    int deleteIndex = in.nextInt();
                    in.nextLine();
                    StudentDataBase.deleteStudent(deleteIndex);
                    break;
                case 4:
                    System.out.println("Edit student information, press Y to process");
                    secondOptions = in.nextLine();

                    if (secondOptions.equals("Y")) {
                        System.out.println("Enter student's index: ");
                        int index = in.nextInt();
                        in.nextLine();
                        System.out.println("Enter student's new data: ");
                        String fullName = in.nextLine();
                        String groupName = in.nextLine();
                        int generalGrade = in.nextInt();
                        in.nextLine();
                        StudentDataBase.generalUpdateStudent(index, fullName, groupName, generalGrade);
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
    
    
    //HW1:
    //StudentDataBase.addStudent(in.nextLine(), in.nextLine(), in.nextInt());
}
class consoleUI {
    public static int printMenu(){
        System.out.println("MENU");
        System.out.println("1. To view student list\n2. To add student\n3. To delete a student\n4. To edit information about a studient");
        System.out.print(">> ");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        return option;
    }
}

class StudentDataBase {
public static Student[] students;
public static int lastIndex = 0;
    
public static void addStudent(String fullName, String groupName, int generalGrade){
   //HW3: 
    try {
    if (lastIndex >= students.length) {
            
            Student[] newStudents = new Student[students.length * 2];
        
            for (int i = 0; i < students.length; i++) {
                newStudents[i] = students[i];
            }
    
            newStudents[lastIndex] = new Student(fullName, groupName, generalGrade);
            students = newStudents;
            lastIndex++;

        } else {
            students[lastIndex] = new Student(fullName, groupName, generalGrade);
            lastIndex++;
        }  
   } catch ( ArrayIndexOutOfBoundsException e){
    System.out.println("\nError! No more students can be added, you have reached capacity!");
   }

    }
    public static void onlyGeneralGradeUpdateStudent(int generalGrade) {
}
    public static void onlyGroupNameUpdateStudent(String groupName) {
}
    public static void onlyNameUpdateStudent(String fullName) {
}
//HW2: 
public static void printAllStudents() {
        System.out.println("\nStudent List: ");
        for (Student student : students) {
            if (student != null) {
            System.out.println(student);
            } else {
                System.out.println("Empty places! ");
            }
        }
    }
//HW4:
public static void generalUpdateStudent(int index, String fullName, String groupName, int generalGrade){
    students[index].setFullName(fullName);
    students[index].setGroupName(groupName);
    students[index].setGeneralGrade(generalGrade);
    }

public static void onlyNameUpdateStudient(int index, String fullName){
    students[index].setFullName(fullName);
}

public static void onlyGroupNameUpdateStudient(int index, String groupName){  
    students[index].setGroupName(groupName);
}

public static void onlyGeneralGradeUpdateStudient(int index, int generalGrade){
students[index].setGeneralGrade(generalGrade);    
}

public static void deleteStudent(int index){
   if (index < 0 || index >= lastIndex) {
        return;
    }

    for (int i = index; i < lastIndex - 1; i++) {
        students[i] = students[i + 1];
    }
    students[lastIndex - 1] = null;
    lastIndex--;
}
}
class Student {
    private String fullName;
    private String groupName;
    private int    generalGrade;
    
public Student(String fullName, String groupName, int generalGrade) {
        this.fullName = fullName;
        this.groupName = groupName;
        this.generalGrade = generalGrade;
    }

public String getFullName() {
        return fullName;
    }

public void setFullName(String fullName) {
        this.fullName = fullName;
    }

public String getGroupName() {
        return groupName;
    }

public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

public int getGeneralGrade() {
        return generalGrade;
    }

public void setGeneralGrade(int generalGrade) {
        this.generalGrade = generalGrade;
    }

    @Override
public String toString() {
        return "Student name: " + fullName + ", Group name: " + groupName + ", General grade: " + generalGrade;
    } 
}
