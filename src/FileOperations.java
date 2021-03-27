import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class FileOperations {
    private static final String INPUT_FILE_PATH = "C:\\Users\\faiya\\IdeaProjects\\DSI_Assignment\\src\\students.txt";
    private static final String OUTPUT_FILE_PATH = "C:\\Users\\faiya\\IdeaProjects\\DSI_Assignment\\src\\students.txt";
    //private static final String INPUT_FILE_PATH = "src\\students.txt";
    //private static final String OUTPUT_FILE_PATH = "src\\students.txt";

    void read(List<Student> studentList){
        try{
            String line;
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_PATH));
            while(true){
                line = br.readLine();
                if(line == null){
                    break;
                }
                String []studentParameters = line.split(",");
                Student student = new Student(studentParameters);
                studentList.add(student);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    void write(List<Student> studentList){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH));
            for(Student student: studentList){
                String []studentParameters = student.getStudentParameters();
                for(int i=0;i< studentParameters.length;i++) {
                    bw.write(studentParameters[i]);
                    if (i != studentParameters.length - 1) {
                        bw.write(",");
                    } else {
                        bw.write("\n");
                    }
                }
            }
            bw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
