import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ledger {

    private List<Student> studentList = new ArrayList<>();
    private Map<String, Integer> earningStat = new HashMap<>();
    private String []subjectList = {"English", "Bangla", "Mathematics"};
    private String []classList = {"eight", "nine", "ten"};

    public void displayMainMenu() {
        FileOperations fileOperations = new FileOperations();
        fileOperations.read(studentList);
        for(String subject: subjectList){
            earningStat.put(subject, 0);
        }
        for(String clas: classList){
            earningStat.put(clas, 0);
        }

        for(Student student: studentList){
            if(student.getClass_no() == 8){
                String ky = "eight";
                earningStat.put(ky, earningStat.get(ky) + student.getTotal_earnings());
            }
            else if(student.getClass_no() == 9){
                String ky = "nine";
                earningStat.put(ky, earningStat.get(ky) + student.getTotal_earnings());
            }
            else if(student.getClass_no() == 10){
                String ky = "ten";
                earningStat.put(ky, earningStat.get(ky) + student.getTotal_earnings());
            }

            boolean english = student.getSubjects()[0];
            boolean bangla = student.getSubjects()[1];
            boolean math = student.getSubjects()[2];

            if(english == true){
                String ky = "English";
                earningStat.put(ky, earningStat.get(ky) + student.getDays_taught()[0]);
            }
            if(bangla == true){
                String ky = "Bangla";
                earningStat.put(ky, earningStat.get(ky) + student.getDays_taught()[1]);
            }
            if(math == true){
                String ky = "Mathematics";
                earningStat.put(ky, earningStat.get(ky) + student.getDays_taught()[2]);
            }
        }

        while(true){
            System.out.println("Main Menu");
            System.out.println("1. Add a student");
            System.out.println("2. Edit a student");
            System.out.println("3. Delete a student");
            System.out.println("4. See student details");
            System.out.println("5. See overall info");
            System.out.println("6. Exit System");
            System.out.print("Please select an option: ");
            try{
                BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
                String line= br.readLine();
                if(line.equals("1")){
                    addStudent();
                }
                else if(line.equals("2")){
                    editStudent();
                }
                else if(line.equals("3")){
                    deleteStudent();
                }
                else if(line.equals("4")){
                    showListOfStudents();
                }
                else if(line.equals("5")){
                    showOverallInfo();
                }
                else if(line.equals("6")){
                    break;
                }
                else{
                    System.out.println("Invalid option. Please select a number from 1-6");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileOperations.write(studentList);
    }

    private void addStudent(){

        String []studentParameters = new String[13];

        while(true){
            System.out.println("1. Class Eight\n" +
                    "2. Class Nine\n" +
                    "3. Class Ten\n" +
                    "4. Back to Main Menu");
            System.out.print("Choose the class of the student: ");
            try{
                BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
                String line= br.readLine();
                if(line.equals("1")){
                    studentParameters[0] = "8";
                    break;
                }
                else if(line.equals("2")){
                    studentParameters[0] = "9";
                    break;
                }
                else if(line.equals("3")){
                    studentParameters[0] = "10";
                    break;
                }
                else if(line.equals("4")){
                    return;
                }
                else{
                    System.out.println("Invalid option. Please select a number from 1-4");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try{

            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the name of the student: ");
            studentParameters[1] = br.readLine();
            System.out.print("Enter the student id of the student: ");
            studentParameters[2] = br.readLine();
            for(Student student: studentList){
                if(student.getStudent_id().equalsIgnoreCase(studentParameters[2])){
                    System.out.println("Student with same id already exists in the class.");
                    System.out.println("\n");
                    return;
                }
            }

            System.out.println("Enter the subjects the student is taught (Enter true if he is taught, Enter false otherwise)");
            System.out.print("English: ");
            studentParameters[3] = br.readLine();
            System.out.print("Bangla: ");
            studentParameters[4] = br.readLine();
            System.out.print("Mathematics: ");
            studentParameters[5] = br.readLine();

            for(int i=6;i<13;i++){
                studentParameters[i] = "0";
            }

            Student student = new Student(studentParameters);
            studentList.add(student);
            System.out.println("Student Added!");
            System.out.println("\n");

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void editStudent(){

        while(true){
            System.out.println("1. Add Day\n" +
                    "2. Add Mark\n" +
                    "3. Back to Main Menu");
            System.out.print("Please select an option: ");
            try{
                BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
                String line= br.readLine();
                if(line.equals("1")){
                    addDay();
                }
                else if(line.equals("2")){
                    addMark();
                }
                else if(line.equals("3")){
                    break;
                }
                else{
                    System.out.println("Invalid option. Please select a number from 1-3");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addDay(){

        String subject = null;
        String student_id=null;
        Student std = null;
        int found = 0, index = 0;
        System.out.print("Please enter the student id: ");
        try{
            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            student_id=br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Student student: studentList){
            if(student.getStudent_id().equalsIgnoreCase(student_id)){
                found = 1;
                std = student;
                break;
            }
            index++;
        }
        if(found == 0){
            System.out.println("No such student with this student id.");
            System.out.println("\n");
            return;
        }

        System.out.print("Please enter the subject (English, Bangla, or Mathematics): ");
        while(true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                subject = br.readLine();
                if(subject.equalsIgnoreCase("English") || subject.equalsIgnoreCase("Bangla") || subject.equalsIgnoreCase("Mathematics")){
                    break;
                }
                else
                {
                    System.out.println("Please enter a valid subject name correctly.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(subject.equalsIgnoreCase("English")){
            if(std.getSubjects()[0] == true){
                int [] std_days_taught = std.getDays_taught();
                std_days_taught[0] += 1;
                std.setDays_taught(std_days_taught);
                std.setTotal_earnings(std.getTotal_earnings()+1);
                updateEarningStat(std, "English");
                studentList.set(index, std);
            }
            else
            {
                System.out.println("Student is not taught this subject");
                System.out.println("\n");
            }
        }
        else if(subject.equalsIgnoreCase("Bangla")){
            if(std.getSubjects()[1] == true){
                int [] std_days_taught = std.getDays_taught();
                std_days_taught[1] += 1;
                std.setDays_taught(std_days_taught);
                std.setTotal_earnings(std.getTotal_earnings()+1);
                updateEarningStat(std, "Bangla");
                studentList.set(index, std);
            }
            else
            {
                System.out.println("Student is not taught this subject");
                System.out.println("\n");
            }
        }
        else if(subject.equalsIgnoreCase("Mathematics")){
            if(std.getSubjects()[2] == true){
                int [] std_days_taught = std.getDays_taught();
                std_days_taught[2] += 1;
                std.setDays_taught(std_days_taught);
                std.setTotal_earnings(std.getTotal_earnings()+1);
                updateEarningStat(std, "Mathematics");
                studentList.set(index, std);
            }
            else
            {
                System.out.println("Student is not taught this subject");
                System.out.println("\n");
            }
        }

    }

    private void addMark(){
        String student_id=null;
        Student std = null;
        int found = 0, index = 0;
        double mark = 0;
        System.out.print("Please enter the student id: ");
        try{
            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            student_id=br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Student student: studentList){
            if(student.getStudent_id().equalsIgnoreCase(student_id)){
                found = 1;
                std = student;
                break;
            }
            index++;
        }
        if(found == 0){
            System.out.println("No such student with this student id.");
            System.out.println("\n");
            return;
        }

        System.out.print("Please enter the mark: ");
        try{
            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            mark=Double.parseDouble(br.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }

        std.setTotal_marks(std.getTotal_marks()+mark);
        std.setExam_count(std.getExam_count()+1);
        std.setAvg_marks(std.getTotal_marks()/std.getExam_count());
        studentList.set(index, std);
    }

    private void updateEarningStat(Student student, String subject){
        if(student.getClass_no() == 8){
            String ky = "eight";
            earningStat.put(ky, earningStat.get(ky) + 1);
        }
        else if(student.getClass_no() == 9){
            String ky = "nine";
            earningStat.put(ky, earningStat.get(ky) + 1);
        }
        else if(student.getClass_no() == 10){
            String ky = "ten";
            earningStat.put(ky, earningStat.get(ky) + 1);
        }


        if(subject == "English"){
            String ky = "English";
            earningStat.put(ky, earningStat.get(ky) + 1);
        }
        else if(subject == "Bangla"){
            String ky = "Bangla";
            earningStat.put(ky, earningStat.get(ky) + 1);
        }
        else if(subject == "Mathematics"){
            String ky = "Mathematics";
            earningStat.put(ky, earningStat.get(ky) + 1);
        }
    }

    private void deleteStudent(){
        String student_id=null;
        //Student std = null;
        int found = 0, index = 0;
        System.out.print("Please enter the student id to delete: ");
        try{
            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            student_id=br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Student student: studentList){
            if(student.getStudent_id().equalsIgnoreCase(student_id)){
                found = 1;
                break;
            }
            index++;
        }
        if(found == 0){
            System.out.println("No such student with this student id.");
            System.out.println("\n");
            return;
        }
        studentList.remove(index);
        System.out.println("Student Deleted!");
        System.out.println("\n");
    }

    private void showListOfStudents(){
        while(true){
            System.out.println("1. Show List in a table\n" +
                    "2. Show individual student details\n" +
                    "3. Back to Main Menu");
            System.out.print("Please select an option: ");
            try{
                BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
                String line= br.readLine();
                if(line.equals("1")){
                    showList();
                }
                else if(line.equals("2")){
                    showDetails();
                }
                else if(line.equals("3")){
                    break;
                }
                else{
                    System.out.println("Invalid option. Please select a number from 1-3");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showList(){
        String class_no = null;
        while(true){
            System.out.println("1. Class Eight\n" +
                    "2. Class Nine\n" +
                    "3. Class Ten\n" +
                    "4. Back to Main Menu");
            System.out.print("Choose the class of to see the student list: ");
            try{
                BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
                String line= br.readLine();
                if(line.equals("1")){
                    class_no = "8";
                    break;
                }
                else if(line.equals("2")){
                    class_no = "9";
                    break;
                }
                else if(line.equals("3")){
                    class_no = "10";
                    break;
                }
                else if(line.equals("4")){
                    return;
                }
                else{
                    System.out.println("Invalid option. Please select a number from 1-4");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("Name of the Students", "Total Earnings", "Average Marks", "Student ID");

        for(Student student: studentList){
            if(Integer.toString(student.getClass_no()).equals(class_no)) {
                String total_earnings = Integer.toString(student.getTotal_earnings());
                String avg_marks = Double.toString(student.getAvg_marks());
                st.addRow(student.getName(), total_earnings, avg_marks, student.getStudent_id());
            }
        }
        st.print();
    }

    private void showDetails(){
        String student_id=null;
        int found = 0;
        System.out.print("Please enter the student id to see details: ");
        try{
            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            student_id=br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Student student: studentList){
            if(student.getStudent_id().equalsIgnoreCase(student_id)){
                found = 1;
                student.printParameters();
                break;
            }
        }
        if(found == 0){
            System.out.println("No such student with this student id.");
            System.out.println("\n");
            return;
        }
    }

    private void showOverallInfo(){

        int []total_days_taught_all = new int[3];
        int []total_days_taught_each = new int[3];
        int total_earn = earningStat.get("eight") + earningStat.get("nine") + earningStat.get("ten");
        double total_mark_all = 0;
        double exam_day_count_all = 0;

        for(Student student: studentList) {
            total_days_taught_all[0] += student.getDays_taught()[0];
            total_days_taught_all[1] += student.getDays_taught()[1];
            total_days_taught_all[2] += student.getDays_taught()[2];

            int largest = student.getDays_taught()[0];
            for (int i = 1; i < 3; i++) {
                if (largest < student.getDays_taught()[i]) {
                    largest = student.getDays_taught()[i];
                }
            }
            if (student.getClass_no() == 8) {
                total_days_taught_each[0] += largest;
            } else if (student.getClass_no() == 9) {
                total_days_taught_each[1] += largest;
            } else if (student.getClass_no() == 10) {
                total_days_taught_each[2] += largest;
            }

            total_mark_all += student.getTotal_marks();
            exam_day_count_all += student.getExam_count();

        }

        double avg_all = total_mark_all/exam_day_count_all;

            System.out.println("\n");
            System.out.println("Total days taught across all classes: " + (total_days_taught_each[0]+total_days_taught_each[1]+total_days_taught_each[2]));
            System.out.print("Individual days taught in each class: ");
            System.out.print("Class Eight: " + total_days_taught_each[0]);
            System.out.print(", Class Nine: " + total_days_taught_each[1]);
            System.out.print(", Class Ten: " + total_days_taught_each[2]);
            System.out.print("\n");
            System.out.println("Total Earnings " + total_earn);
            System.out.print("Individual earning in each class: ");
            System.out.print("Class Eight: " + earningStat.get("eight"));
            System.out.print(", Class Nine: " + earningStat.get("nine"));
            System.out.print(", Class Ten: " + earningStat.get("ten"));
            System.out.print("\n");
            System.out.print("Individual earning in each subject: ");
            System.out.print("English: " + earningStat.get("English"));
            System.out.print(", Bangla: " + earningStat.get("Bangla"));
            System.out.print(", Mathematics: " + earningStat.get("Mathematics"));
            System.out.print("\n");
            System.out.println("Average marks of all students: " + avg_all);
            System.out.println("\n");
    }

}
