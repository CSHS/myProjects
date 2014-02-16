   import java.util.*;
   import java.io.*; 
	
	
   public class parser
   {
      //public static Scanner s;
      public static HashMap<String, Integer> ha = new HashMap<String, Integer>();
      public static HashMap<String, ArrayList<Integer>> ha2 = new HashMap<String, ArrayList<Integer>>(); //for lists
      public static Stack stack = new Stack();
      public static HashMap<String, FunctionDef> ha3 = new HashMap<String, FunctionDef>();
   	//public static HashMap<String,Integer> hatemp = new HashMap<String,Integer>();
      public static String name = "";
      public static PrintWriter out;
      public static boolean check;
      public static boolean check2;
      public static boolean check3;
      public static String a = "";
      public static int returner =0;
   
	  /**Accepts an input.txt and outputs the result in parser.out.**/
      public static void main(String[] args) throws Exception
      {
         out = new PrintWriter(new BufferedWriter(new FileWriter("parser.out")));
         check=true;
         check2=true;
         Scanner s=null;
         stack.push(ha);
         try {
            s = new Scanner(new File("input.txt"));
         }
            catch (Exception e)
            {
               System.out.println("File error");
            }
         //s= new Scanner(sc3);
         try { 
            parse(s);}
            catch (Exception e)
            {
               String error = e.getMessage();
               System.out.println(error);
               if (e.getMessage()==null)
                  System.out.println("Backspace the white space after the last bracket; there's nothing after that last bit of code.");
               e.printStackTrace();
            //               e.getMessage();
            }
         out.close();
         //StringTokenizer st = new StringTokenizer(s2);
      	
      
      }
   	
   	  /**parser for GOKU language **/
      public static void parse(Scanner scan) throws Exception
      {
         while (scan.hasNextLine())
         {//find a way to not call stack when parsing through the method.
            //String temp=s.nextLine();
            String temp2 = scan.next(); //anyway to catch this error when null?
            HashMap<String,Integer> hatemp = (HashMap<String,Integer>)stack.pop();
            if (temp2.equals("null"))
            {
               throw new Exception("Backspace the white space after the last bracket; there's nothing after that last bit of code");
            }
            else if (temp2.equals("}"))
            {
               return;
            }
            if (temp2.equals("var"))
            {
               String temp3 = scan.next();
            	//HashMap<String,Integer> hatemp = stack.pop();
               hatemp.put(temp3,0);
            	
               //ha.put(temp3,0);
               stack.push(hatemp);
              // System.out.println(ha.get(temp3));
            }
            else if (temp2.equals("define"))
            {
               name = scan.next();
               String args = "";
               String temp100 = scan.next();
               while (!(temp100.equals("{")))
               {
                  if (!(temp100.equals(":")))
                  {
                     args+=temp100 +",";
                  }
                  temp100=scan.next();
               }
               String body = "";
               int counter=1;
               while (counter!=0)
               {
                  temp100 = scan.next();
               
                  if (temp100.equals("}"))
                  {
                     counter--;
                  }
                  if (temp100.equals("{"))
                  {
                     counter++;
                  }
                  body+=temp100+" ";
               }
             
			   //FunctionDef is a custom made class of any functions/methods created in GOKU.
               FunctionDef method = new FunctionDef(name,args,body);
               ha3.put(name, method);
            }
            else if (temp2.equals("list"))
            {
               String temp3 = scan.next();
               ha2.put(temp3,null);
            }
            else if (temp2.equals("enter"))
            {
               out.println();
            }
            else if (temp2.equals("space"))
            {
               out.print(" ");
            }
            else if (temp2.equals("let"))
            {
               String temp13 = scan.next();
               String temp4 = scan.next();           //let is only to change condition of current state.
            	//HashMap<String,Integer> hatemp = stack.pop();
               if (hatemp.containsKey(temp13))           //var is to set initial state.
               {
                  
                  if (scan.hasNextInt())              //program list size in parsedown.
                  {
                     int n = scan.nextInt();
                     System.out.println("Setting " + temp13 + " = " + n);
                     hatemp.put(temp13,n);
                  }
                  else if (scan.hasNext())
                  {
                     hatemp.put(temp13,parsedown(scan));
                  }
                  else
                     check2=false;
               }
               
               else if (partOflist(temp13) && ha2.containsKey(temp13.substring(0,1)))
               {
                  addTolist(temp13, scan);
               }
               else
                  check2 = false;
               stack.push(hatemp);
            }
            else if (temp2.equals("print"))
            {
               String hold= scan.next();
               if (partOflist(hold)==true)
               {
                  out.print(accessList(hold));
               }
               else
               {
                  if (hold.equals("("))
                     hold+=toStringboolean2(scan);             
                  Scanner scan1 = new Scanner(hold);
                  out.print(parsedown(scan1));
               }
            }
            else if (temp2.equals("return"))
            {
               String hold= scan.next();
               if (partOflist(hold)==true)
               {
                  returner = accessList(hold);
                  
               }
               else
               {
                  if (hold.equals("("))
                     hold+=toStringboolean2(scan);             
                  Scanner scan1 = new Scanner(hold);
                  stack.push(hatemp);
                  returner = parsedown(scan1);
               }
            	///////////////////////////////////////////////////
            }
            
            else if (temp2.equals("while"))
            {
               String condition = toStringboolean(scan);
               String block = toString(scan);
               System.out.println(block);
               Scanner scanplz = new Scanner(condition);
               //Scanner scanplz2 = new Scanner(block);
               int nash2 = parsedown(scanplz);
               while (nash2>0)
               {
                  Scanner scanplz4 = new Scanner(block);
                  parse(scanplz4);
                  Scanner scanplz3 = new Scanner(condition);
                  nash2=parsedown(scanplz3);
               }
            	
            }
            
            else if (temp2.equals("if"))
            {
               stack.push(hatemp);
               int nash = parsedown(scan);
               if (nash>0)
               {
                  if (scan.next().equals("{"))
                  {
                     parse(scan);
                  }
               }
               else
               {
                  temp2 = scan.next();
                  int counter=2;
                  do
                  {
                     if (temp2.equals("{"))
                     {
                        counter++;
                     }
                     else if (temp2.equals("}"))
                     {
                        counter--;
                     }    	
                     if (counter!=2)
                        temp2=scan.next();
                  } while (counter!=2);
               
                 /* while (!(temp2.equals("}")))
                  {
                     temp2=scan.next();
                  }*/
               	//temp2=scan.next();
               //						parse(scan);
               }
            }
            else if (temp2.equals("else"))
            {
               temp2=scan.next();
               if (temp2.equals("if"))
               {
                  int nash = parsedown(scan);
                  if (nash>0)
                  {
                     temp2=scan.next();
                     if (temp2.equals("{"))
                     {
                        parse(scan);
                     }
                  }
               
               }
               else if (temp2.equals("{"))
               {
                  parse(scan);
               }
            
            }
            
            else if (hatemp.containsKey(temp2) || ha2.containsKey(temp2))
            {
               String temp4 = scan.next();
            	
               if (scan.hasNextInt())
               {
                  hatemp.put(temp2,scan.nextInt());
               }
               else if (scan.hasNext())
               {
                  String list = scan.next();
                  if (list.substring(0,1).equals("<") && list.substring(list.length()-1,list.length()).equals(">"))
                  {
                     ArrayList<Integer> templist = toList(list);
                     ha2.put(temp2, templist);
                  }
               	
               }
            	
            }
            else if (!hatemp.containsKey(temp2))
            {
               check2=false;
            }
            else if (!temp2.equals("var") || !temp2.equals("print") || !temp2.equals("if") || !temp2.equals("else") || !temp2.equals("{")
            || !temp2.equals("}") || !temp2.equals("let") || !temp2.equals("while"))
            {
               check=false;
            }
         	
         
            if (check==false || check2==false)
            {
               if (check==false)
               {
                  throw new Exception("Syntax error: Token undefined" + " " + temp2);
                //  out.print("Syntax error: Token undefined");
               }
               else if (check2==false)
               {
                  throw new Exception("Syntax error: Undefined variable" + " " + temp2);
               // out.print("Syntax error: Undefined variable");
               }
               
            }
            stack.push(hatemp);
         }
      }
   	
      private static int accessList(String s) throws Exception  //list represents a vector.
      {
         System.out.println("Here: " + s.substring(0,1));
         ArrayList<Integer> list = ha2.get(s.substring(0,1));
         String returner = "";
         for (int x=2;x<s.length();x++)
         {
            returner+=s.substring(x,x+1)+" ";
         }
         Scanner sc = new Scanner(returner);
         int index = parsedown(sc);
         
         if(list == null) {
            System.out.println("yep");
         }
      	
         return list.get(index);
      }
      private static void addTolist(String s, Scanner sc2) throws Exception
      {
         ArrayList<Integer> list;
         if (ha2.get(s.substring(0,1))==null)
         {
            ha2.put(s.substring(0,1), new ArrayList<Integer>());
         }
                     
         list = ha2.get(s.substring(0,1));
      	
         String returner = "";
         for (int x=2;x<s.length();x++)
         {
            returner+=s.substring(x,x+1)+" ";
         }
         Scanner sc = new Scanner(returner);
         int index = parsedown(sc);
      
         if (index>=list.size() && index<(list.size()+1))//my bubble sort code is wrong.
         {
            list.add(parsedown(sc2));
         }
         else if (index<list.size() && index>=0)
         {
            System.out.println("yes");
            list.set(index,parsedown(sc2));
         }
         else
            throw new Exception("GOKU OUT OF BOUNDS!!!");
      }
   	
      private static int listSize(String s)
      {
         if (s.substring(0,1).equals("|") && s.substring(s.length()-1,s.length()).equals("|"))
         {
            ArrayList<Integer> list = ha2.get(s.substring(1,2));
            return list.size();
         }
         else
            return -100;
      }
      private static boolean partOflist(String s)
      {
         boolean returner = false;
         for (int x=0;x<s.length();x++)
         {
            if (s.substring(x,x+1).equals("_"))
            {
               returner = true;
            }
         }
         return returner;
      }
      private static ArrayList<Integer> toList(String s)
      {
         ArrayList<Integer> returner = new ArrayList<Integer>();
         for (int x=1; x<s.length();x=x+2)
         {
            returner.add(Integer.parseInt(s.substring(x,x+1)));
         }
         return returner;
      }
      private static String toString(Scanner s5)//on the if part.
      {
         String returner ="";
         int counter=1;
         while (counter!=0)
         {
            returner+=a+" ";
         	
            if (a.equals("{"))
            {
               counter++;
            }
            else if (a.equals("}"))
            {
               counter--;
            }    	
         	
            if(counter != 0)
               a = s5.next();
          
         }
      	
         return returner;
       
      }
   	
      private static String toStringboolean2(Scanner s5)
      {
         String returner= " ";
         int counter=1;
         while (counter!=0)
         {
            a = s5.next();
            
            if (a.equals(")"))
            {
               counter--;
            }
            if (a.equals("("))
            {
               counter++;
            }
            returner+=a+" ";
         }
         
         return returner;
      
      }
      private static String toStringboolean(Scanner s5)
      {
         String returner ="";
         a = s5.next();
         int counter=1;
         int counter2 = 1;
         //int counter3 = 0;
         while (counter!=0)
         {
            returner+=a+" ";
            
            if (a.equals(")"))
            {
               counter--;
            }
            if (a.equals("("))
            {
               //counter2=1;
               if (counter2!=1)
                  counter++;
               counter2=100000000;
            }
            a = s5.next();
         }
         a = s5.next();
         return returner;
      }
   	
      private static boolean listSize2(String s)
      {
         boolean returner = false;
         if (s.substring(0,1).equals("|") && s.substring(s.length()-1,s.length()).equals("|"))
         {
            returner = true;
         }
         return returner;
      }
   	
      public static int parsedown(Scanner scan) throws Exception
      {
         if (scan.hasNextInt())
         {
            return scan.nextInt();
         }
         String temp = scan.next();
         if (!temp.equals("(") && !temp.equals(")"))
         {
           // int returner = 0;
            if (temp.substring(0,1).equals("!"))
            {
               String returner = temp.substring(1,temp.length());
               if (ha.containsKey(returner))
               {
                  if (ha.get(returner)>0)
                     return 0;
                  else
                     return (int)(Math.random()*10+1);//return returner;
               
               }
            }
            else if (listSize2(temp)==true)
            {
               return listSize(temp);
            }
            else if (partOflist(temp)==true)
            {
               return accessList(temp);
            }
            Iterator itr = stack.iterator();
            while (itr.hasNext())
            {
               HashMap<String,Integer> hatemp = (HashMap<String,Integer>)itr.next();
               if (hatemp.containsKey(temp))
               {
                  return hatemp.get(temp);
               }
            }
            if (ha3.containsKey(temp))//ha3 contains FunctionDef
            {
               FunctionDef method = ha3.get(temp);
               String arguments = method.getArgs();
               String[] arg = arguments.split(",");
               HashMap<String, Integer> ha10 = new HashMap<String,Integer>();
               scan.next();
               for (int z = 0;z<arg.length;z++)
               {
                  int paran = scan.nextInt();
                  ha10.put(arg[z],paran);
               }
               scan.next();
               stack.push(ha10);
               Scanner sc100 = new Scanner(method.getBody());
               parse(sc100);
               stack.pop();
               return returner;//make all calls to daata structure holding variables into stack.
            }
            else
               throw new Exception("unknown variable" + " " + temp);
         	
                                  
         }
        
         if (!(temp.equals("("))){
               //out.print("Syntax error");
            throw new Exception("Unexpected no parantheses" + " " + temp);}
         	
         int a = parsedown(scan);
         temp = scan.next();
         if ((!(temp.equals("+"))) && (!(temp.equals("*"))) && (!(temp.equals("-")))
            && (!(temp.equals("/")) && !(temp.equals(">")) && !(temp.equals("<")) && !(temp.equals(">="))
            && !(temp.equals("<=")) && !(temp.equals("&&")) && !(temp.equals("||")) && !(temp.equals("==")))){
               //out.print("Syntax error");
            throw new Exception("UNexpected no operators" + " " +temp);
         }
         String operand = temp;
         	 
         int b = parsedown(scan);
         temp = scan.next();
         if (!(temp.equals(")"))){
               //out.print("Syntax error");
            throw new Exception("Unexpected no parantheses" + " " + temp);}
         int pass=0;
         if (operand.equals("+"))
            pass=a+b;	
         else if (operand.equals("-"))
            pass=a-b;
         else if (operand.equals("*"))
            pass= a*b;
         else if (operand.equals("/"))
            pass= a/b;
         else if (operand.equals(">")){
            if (a>b){
               pass=(int)Math.random()*10+1;}}
         else if (operand.equals("<")){
            if (a<b)
               pass=(int)Math.random()*10+1;}
         else if (operand.equals(">=")){
            if (a>=b)
               pass=(int)Math.random()*10+1;}
         else if (operand.equals("<=")){
            if (a<=b)
               pass=(int)Math.random()*10+1;}
         else if (operand.equals("&&")){
            if (a>0 && b>0)
               pass=(int)Math.random()*10+1;}
         else if (operand.equals("||")){
            if (a>0 || b>0)
               pass=(int)Math.random()*10+1;}
         else if (operand.equals("==")){
            if (a==b)
               pass=(int)Math.random()*10+1;}
         else
            pass=0;
         return pass;
         
      }
      public static int pascal(int n, int k)
      {
         int product = n;
         int product3 = product-1;
         int used = 1;
         int divisor = k;
         int product5 = divisor-1;
         while (used!=k)
         {
            product*=product3;
            divisor*=product5;
            product3--;
            product5--;
            used++;
         }
         return product/divisor;
      
      }
   }