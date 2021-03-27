public class Student {
    private String name;
    private boolean []subjects = new boolean[3]; //storing the taught subjects. English, Bangla and Mathematics respectively.
    private double avg_marks;
    private int []days_taught = new int[3]; //storing the days taught for each subject.
    private int total_earnings;
    private double total_marks;
    private int exam_count;
    private int class_no;
    private String student_id;

    Student(String []studentParameters){
        this.class_no = Integer.parseInt(studentParameters[0]);
        this.name = studentParameters[1];
        this.student_id = studentParameters[2];
        for(int i = 0; i < 3; i++){
            this.subjects[i]=Boolean.parseBoolean(studentParameters[i+3]);
        }
        this.avg_marks = Double.parseDouble(studentParameters[6]);
        for(int i = 0; i < 3; i++){
            this.days_taught[i]=Integer.parseInt(studentParameters[i+7]);
        }
        this.total_earnings = Integer.parseInt(studentParameters[10]);
        this.total_marks = Double.parseDouble(studentParameters[11]);
        this.exam_count = Integer.parseInt(studentParameters[12]);
    }

    public String getName(){
        return this.name;
    }

    public int getClass_no() {
        return this.class_no;
    }

    public boolean[] getSubjects() {
        return this.subjects;
    }

    public int[] getDays_taught() {
        return this.days_taught;
    }

    public double getAvg_marks() {
        return this.avg_marks;
    }

    public int getExam_count() {
        return this.exam_count;
    }

    public int getTotal_earnings() {
        return this.total_earnings;
    }

    public double getTotal_marks() {
        return this.total_marks;
    }

    public String getStudent_id(){
        return this.student_id;
    }

    public String []getStudentParameters(){
        String []studentParameters = new String[13];

        studentParameters[0] = Integer.toString(this.class_no);
        studentParameters[1] = this.name;
        studentParameters[2] = this.student_id;
        for(int i=0;i<3;i++){
            studentParameters[i+3] = Boolean.toString(this.subjects[i]);
        }
        studentParameters[6] = Double.toString(this.avg_marks);
        for(int i=0;i<3;i++){
            studentParameters[i+7] = Integer.toString(this.days_taught[i]);
        }
        //studentParameters[7] = Integer.toString(this.days_taught);
        studentParameters[10] = Integer.toString(this.total_earnings);
        studentParameters[11] = Double.toString(this.total_marks);
        studentParameters[12] = Integer.toString(this.exam_count);

        return studentParameters;
    }

    public void setDays_taught(int[] days_taught) {
        this.days_taught = days_taught;
    }

    public void setAvg_marks(double avg_marks) {
        this.avg_marks = avg_marks;
    }

    public void setTotal_marks(double total_marks) {
        this.total_marks = total_marks;
    }

    public void setExam_count(int exam_count) {
        this.exam_count = exam_count;
    }

    public void setTotal_earnings(int total_earnings) {
        this.total_earnings = total_earnings;
    }

    public void printParameters() {
        System.out.println("\n");
        System.out.println("Name: "+name);
        System.out.println("Class: "+Integer.toString(class_no));
        System.out.println("Student ID: "+student_id);
        System.out.print("Subjects taught: ");

        if(subjects[0] == true){
            System.out.print("English ");
        }
        if(subjects[1] == true){
            System.out.print("Bangla ");
        }
        if(subjects[2] == true){
            System.out.print("Mathematics");
        }
        System.out.print("\n");

        System.out.print("Total Days Taught: ");
        System.out.print("English: "+ days_taught[0] + ",");
        System.out.print(" Bangla: "+ days_taught[1] + ",");
        System.out.print(" Mathematics: "+ days_taught[2]);
        System.out.print("\n");

        System.out.println("Total Earnings: "+total_earnings);
    }

}
