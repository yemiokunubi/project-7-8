public class Parser {

  public static String getCommand(String input){

    if(input.equals("push")){
      return "C_PUSH";

    }
    else if(input.equals("pop")){
      return "C_POP";
    }
    else if(input.equals("add")){
      return "C_ARITHMETIC";
    }
    else if(input.equals("sub")){
      return "C_ARITHMETIC";
    }
    else if(input.equals("eq")){
      return "C_ARITHMETIC";
    }
    else if(input.equals("gt")){
      return "C_ARITHMETIC";
    }
    else if(input.equals("lt")){
      return "C_ARITHMETIC";
    }
    else if(input.equals("and")){
      return "C_ARITHMETIC";
    }
    else if(input.equals("or")){
      return "C_ARITHMETIC";
    }
    else if(input.equals("neg")){
      return "C_ARITHMETIC";
    }
    else if(input.equals("not")){
      return "C_ARITHMETIC";
    }
    else{
      return "";
    }


  }
  
}
