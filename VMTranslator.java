import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class VMTranslator{

  static ArrayList<String> fileInput = new ArrayList<String>();
  public static void firstPass(String path) throws FileNotFoundException{
    File file = new File(path);
    Scanner sc = new Scanner(file);
    
    int count = 0;
    while(sc.hasNextLine()){
      
      String line = sc.nextLine();
      String trimmed = line.trim();
      String firstElement = trimmed.split("")[0];
      if(firstElement.equals("") | firstElement.equals("/")){ //take out values that are not comments or whitespace
        
      }
      else{
        String trimming = line.trim();
        // String newVal = trimming.split(" ")[0]; //get rid of lines that have comments at the end
        // fileInput.add(newVal.trim());
        fileInput.add(trimming);

        count++;
        
      }
      
    }
    
  }
  public static void createFile(String fileName) throws FileNotFoundException{
    try {
      File hFile = new File(fileName);
      if (hFile.createNewFile()) {
      } else {

      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  public static void writeTofile(String fileName, String input, int i) throws FileNotFoundException{
    try {
      if(i==0){
        FileWriter myWriter = new FileWriter(fileName,false);
        myWriter.write(input);
        myWriter.close();
      }
      else{
        FileWriter myWriter = new FileWriter(fileName,true);
        myWriter.write(input);
        myWriter.close();
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static String push( String val){
    return val;
  }

  public static void main(String[] args) throws FileNotFoundException{
    String originalFile = args[0];
    String strippedName = originalFile.split("\\.")[0];
    String myFileNameNoPath = originalFile.split("\\.")[0] + ".asm";  // the name of the new file we are creating
    String folderName = args[1];
    String path = "/Users/yemi/Desktop/nand2tetris/projects/07/" +folderName +"/"+ originalFile;  //file we are reading
    String myFileName = "/Users/yemi/Desktop/nand2tetris/projects/07/" + folderName + "/"+ myFileNameNoPath;  //file we are writing to
    createFile(myFileName);
    System.out.println("path is " + strippedName);

    String startline="";
    startline =  startline + "// Initialize stack pointer\n";
    startline =  startline + "@256\n";
    startline =  startline + "D=A\n";
    startline =  startline + "@SP\n";
    startline =  startline + "M=D\n";
    writeTofile(myFileName, startline+"\n",0);


    firstPass(path); //takes out lines with whitespace and comments
    for(int i = 0; i<fileInput.size(); i++){
      String currentLine = fileInput.get(i);
      String firstElement = currentLine.split(" ")[0];
      String commandType = Parser.getCommand(firstElement);
      writeTofile(myFileName, "//"+currentLine+"\n",1);

      if(commandType.equals("C_PUSH")){
        String arg1 = currentLine.split(" ")[1];
        String parsArg = currentLine.split(" ")[2];
        int arg2 = Integer.parseInt(parsArg);

        String input = CodeWriter.push(arg1, arg2, strippedName);
        writeTofile(myFileName, input+"\n",1);
      }
      else if(commandType.equals("C_POP")){
        String arg1 = currentLine.split(" ")[1];
        String parsArg = currentLine.split(" ")[2];
        int arg2 = Integer.parseInt(parsArg);

        String input = CodeWriter.pop(arg1, arg2, strippedName);
        writeTofile(myFileName, input+"\n",1);
      }
      else if(commandType.equals("C_ARITHMETIC")){
        String input = CodeWriter.arithmetic( firstElement );
        writeTofile(myFileName, input+"\n",1);
      }


    }

    String endline="";
    endline = endline + "//end of program \n";
    endline = endline + "(end)\n";
    endline = endline + "@end\n";
    endline = endline + "0;JMP\n";
    writeTofile(myFileName, endline+"\n",2);

  }

}