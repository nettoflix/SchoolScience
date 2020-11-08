package sem.allscience.Manager;

import java.text.DecimalFormat;

public class Handler {
    public static boolean isIntParsable(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public static boolean isDoubleParsable(String input){
        try{
            Double.parseDouble(input);
            return true;
        }catch(Exception e){
            return false;
        }
    }
  public static String formartDoubleToString(Double x) {
      DecimalFormat numberFormat = new DecimalFormat("0.00");
      return "R$" + numberFormat.format(x);
  }
}
