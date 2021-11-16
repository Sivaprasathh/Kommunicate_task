import java.text.ParseException;
import java.text.*;
import java.util.Date;
import java.util.*;
public class CompareTwoDatesTest {
   public static void main(String[] args) throws ParseException {
     Scanner s = new Scanner(System.in);
     int n = s.nextInt();
     ArrayList<String> list = new ArrayList<>();
     ArrayList<ArrayList<String>> emp = new ArrayList<ArrayList<String>>();
     for(int i = 0 ; i < n ; i++){
         String str = s.next();
         list.add(str);
     }
     ArrayList<ArrayList<String>> movie = callFunction(list,emp);   //to get the set of movies as nested array list.
     int temp = 0,count=0;
     String dec = "";
     String base = "";
     ArrayList<String> list2 = new ArrayList<>();
     ArrayList<String> list3 = new ArrayList<>();
     for(int i = 0 ; i < movie.size() ; i++){
            list2 = movie.get(i);
            for(int j = i ; j < movie.size() ; j++){
                list3 = movie.get(j);
                if(list2.get(0).equals(list3.get(0))  && i!=j ){
                    count++;
                    dec = list2.get(0);
                }
            }
            if(count>temp){
                temp = count;
                count = 0;
                base = dec;
            }
     }
     ArrayList<String> movieList = findSolution(base,movie);   // to find the exact list of movies and to calculate the amount he can earn.
     System.out.println("List of movies he can act to earn more :" + movieList);
     System.out.print("The Amount he can earn :"+movieList.size()+" crs");
   }
   
   public static ArrayList<String> findSolution(String base,ArrayList<ArrayList<String>> listt){
       ArrayList<String> tempp = new ArrayList<>();
       ArrayList<String> temp1 = new ArrayList<>();
       temp1.add(base);
       for(int i = 0 ; i < listt.size() ; i++){
           tempp = listt.get(i);
           if(tempp.get(0).equals(base)){
               temp1.add(tempp.get(1));
           }
       }
       return temp1;
   }
   public static ArrayList<ArrayList<String>> callFunction(ArrayList<String> list1,ArrayList<ArrayList<String>> empty){
       String str=list1.get(0);
      // System.out.println(str);
       String[] first = str.split("_");
       String date = first[2];
       
       SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
       try{
       Date d1 = sdformat.parse(date);                                       //to parse and compare the date and months
       
       for(int i = 1 ; i < list1.size() ; i++){
           String str1 = list1.get(i);
           String[] sec1 = str1.split("_");
           String date1 = sec1[1];
           Date d2 = sdformat.parse(date1);
           
         if(d1.compareTo(d2) < 0) {
            ArrayList<String> emmp = new ArrayList<String>();
         emmp.add(first[0]);
         emmp.add(sec1[0]);
         empty.add(emmp);
         date = sec1[2];
         d1 = sdformat.parse(date);
        }
       }
        list1.remove(0);
       if(list1.size() != 0){
           return callFunction(list1,empty);                 
       }
      
       }
      catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   
    return empty;
   }
}


/*
Sample input1:
6
Race_08-01-2018_28-01-2018
Rock_20-01-2018_30-01-2018
Policy_29-01-2018_16-02-2018
Brave_02-02-2018_14-02-2018
Drive_10-02-2018_18-02-2018
Bala_15-02-2018_28-02-2018

Sample Output 1:
List of movies he can act to earn more :[Rock, Brave, Bala]
The Amount he can earn :3 crs

Sample input2:
7
Race_08-01-2018_28-01-2018
Rock_20-01-2018_30-01-2018
Policy_29-01-2018_16-02-2018
Brave_02-02-2018_14-02-2018
Drive_10-02-2018_18-02-2018
Bala_15-02-2018_28-02-2018
Hero_01-03-2018_10-03-2018

Sample Output2:
List of movies he can act :[Rock, Brave, Bala, Hero]
The Amount he can earn :4 crs


*/