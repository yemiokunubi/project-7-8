public class CodeWriter {

  public static String push( String placer, int arg2, String root){

    if(placer.equals("constant")){
      String line = "";
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "M=M+1\n";
      return line;
    }
    else if(placer.equals("local")){
      String line = "";
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@LCL\n";
      line = line + "A=M+D\n";
      line = line + "D=M\n";
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "M=M+1\n";
      
      return line;

    }
    else if(placer.equals("argument")){
      String line = "";
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@ARG\n";
      line = line + "A=M+D\n";
      line = line + "D=M\n";
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "M=M+1\n";

      
      return line;
    }
    else if(placer.equals("that")){
      String line = "";
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@THAT\n";
      line = line + "A=M+D\n";
      line = line + "D=M\n";
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "M=M+1\n";
      
      return line;
    }
    else if(placer.equals("this")){
      String line = "";
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@THIS\n";
      line = line + "A=M+D\n";
      line = line + "D=M\n";
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "M=M+1\n";

   
      return line;
    }
    else if(placer.equals("temp")){
      String line = "";
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@ARG\n";
      line = line + "@5\n";
      line = line + "A=D+A\n";
      line = line + "D=M\n";
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "M=M+1\n";



      return line;
    }
    else if(placer.equals("pointer")){
      int map = 3;
      if(arg2 == 0){ //pointer 0 goes to THIS
        map = 3;
      }
      else if(arg2 == 1){ //pointer 1 goes to THAT
        map = 4;
      }
      
      String line = "";
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@"+3+"\n";
      line = line + "A=A+D\n";
      line = line + "D=M\n";
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "M=M+1\n";



      return line;
    }
    else if(placer.equals("static")){
      String line = "";
      line = line + "@"+root+"."+arg2+"\n";
      line = line + "D=M\n";
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "M=M+1\n";

      
      return line;

    }
    else{
      return "";
    }
    

  }

  public static String arithmetic( String command){
    String line = "";

    if(command.equals("add")){
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "A=A-1\n";
      line = line + "M=D+M\n";
      return line;
      
    }
    else if(command.equals("sub")){
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "A=A-1\n";
      line = line + "M=M-D\n";
      return line;

    }
    else if(command.equals("neg")){
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "A=A-1\n";
      line = line + "M=-M\n";
      return line;

    }
    else if(command.equals("eq")){
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M-D\n";
      line = line + "@labelTrueEQ\n";
      line = line + "D;JEQ\n";
      line = line + "D=0\n";
      line = line + "@labelFalseEQ\n";
      line = line + "0;JMP\n";
      line = line + "(labelTrueEQ)\n";
      line = line + "D=-1\n";
      line = line + "(labelFalseEQ)\n";
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "M=M+1\n";
      return line;
    }
    else if(command.equals("lt")){
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M-D\n";
      line = line + "@labelTrueLT\n";
      line = line + "D;JLT\n";
      line = line + "D=0\n";
      line = line + "@labelFalseLT\n";
      line = line + "0;JMP\n";
      line = line + "(labelTrueLT)\n";
      line = line + "D=-1\n";
      line = line + "(labelFalseLT)\n";
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "M=M+1\n";
      return line;
    }
    else if(command.equals("gt")){
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M-D\n";
      line = line + "@labelTrueGT\n";
      line = line + "D;JGT\n";
      line = line + "D=0\n";
      line = line + "@labelFalseGT\n";
      line = line + "0;JMP\n";
      line = line + "(labelTrueGT)\n";
      line = line + "D=-1\n";
      line = line + "(labelFalseGT)\n";
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "M=M+1\n";
      return line;
    }
    else if(command.equals("and")){
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "A=A-1\n";
      line = line + "M=D&M\n";
      return line;

    }
    else if(command.equals("or")){
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "A=A-1\n";
      line = line + "M=D|M\n";
      return line;

    }
    else if(command.equals("not")){
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "A=A-1\n";
      line = line + "M=!M\n";
      return line;
    
    }

    
    return line;
  }

  public static String pop( String placer, int arg2, String root){
    String line = "";

    if(placer.equals("constant")){
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@SP\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "M=M+1\n";
      return line;
    }
    else if(placer.equals("local")){
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@LCL\n";
      line = line + "D=M+D\n";
      line = line + "@R13\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "@R13\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      return line;

    }
    else if(placer.equals("argument")){
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@ARG\n";
      line = line + "D=M+D\n";
      line = line + "@R13\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "@R13\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      return line;

    }
    else if(placer.equals("this")){
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@THIS\n";
      line = line + "D=M+D\n";
      line = line + "@R13\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "@R13\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      return line;
    }
    else if(placer.equals("that")){
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@THAT\n";
      line = line + "D=M+D\n";
      line = line + "@R13\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "@R13\n";
      line = line + "A=M\n";
      line = line + "M=D\n";
      return line;

    }
    else if(placer.equals("temp")){
      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@5\n";
      line = line + "D=D+A\n";
      line = line + "@R13\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "@R13\n";
      line = line + "A=M\n";
      line = line + "M=D\n";

      return line;
      
    }
    else if(placer.equals("pointer")){
      int map = 3;
      if(arg2 == 0){ //pointer 0 goes to THIS
        map = 3;
      }
      else if(arg2 == 1){ //pointer 1 goes to THAT
        map = 4;
      }
      

      line = line + "@"+arg2+"\n";
      line = line + "D=A\n";
      line = line + "@"+3+"\n";
      line = line + "D=A+D\n";
      line = line + "@R13\n";
      line = line + "M=D\n";
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "@R13\n";
      line = line + "A=M\n";
      line = line + "M=D\n";

      return line;
    }
    else if(placer.equals("static")){
      line = line + "@SP\n";
      line = line + "AM=M-1\n";
      line = line + "D=M\n";
      line = line + "@"+root+"."+arg2+"\n";
      line = line + "M=D\n";


      
      return line;

    }


    return line;
  }
}
