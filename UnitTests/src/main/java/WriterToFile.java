import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterToFile {

    public static String filePath;

    public WriterToFile(String filePath){
        this.filePath = filePath;
    }

    private void createFile(){
        try {
            File myObj = new File(filePath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToFile(String input){
        try (FileWriter myWriter = new FileWriter(filePath)){
            myWriter.write(input);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
