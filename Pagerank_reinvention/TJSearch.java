   import java.sql.*;
   import java.util.*;
   import java.math.*;
   import org.jsoup.nodes.Document;
   import org.jsoup.Jsoup;
   public class TJSearch {
   
      public static ArrayList<String> jet = new ArrayList<String>();
      public static ArrayList<String> jet2 = new ArrayList<String>();
      public static ArrayList<Double> jet3 = new ArrayList<Double>();
   
    // method main(): ALWAYS the APPLICATION entry point
      public static void main (String[] args) //throws Exception
      {
      //input a keyword
         System.out.print("Enter:");
         Scanner f = new Scanner(System.in);
         String g = f.nextLine().toLowerCase();
		 long startTime = System.currentTimeMillis();
         String[] split = g.split(" ");
      
      	
         String[] words = new String[((int)Math.pow(2,split.length))-1];
         int index=0;
         int iter = 0;
         Connection conn = null;
       
         try
         {
			//connect to mysql server
            String url = "jdbc:mysql://localhost/tjsearch";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url,USERNAME, PASSWORD); //use personal mysql username and password.
            Statement s = conn.createStatement ();
            Statement s2 = conn.createStatement();
            Statement s3 = conn.createStatement();
         
         //form the power set
            ArrayList<ArrayList<String>> myPowerSet = powerset(split);
            //String[][] sl = (String[][]) (myPowerSet.toArray());
            int j;
				//selection sort
            for (int a = 0; a<myPowerSet.size()-1;a++)
            {
             //String[] cifu3 = s[a];
               j  = a;
             
            /* test against elements after j to find the smallest */
               //Iterator it = myPowerSet.iterator();
               for (int i = j+1; i < myPowerSet.size();i++) {
                  if (myPowerSet.get(i).size() < myPowerSet.get(j).size()) {
                     j = i;
                  }
               }
            
            /* iMin is the index of the minimum element. Swap it with the current position */
               if ( j != a ) {
                  ArrayList<String> temp = myPowerSet.get(j);
                  myPowerSet.add(j,myPowerSet.get(a));
                  myPowerSet.remove(j+1);
                  myPowerSet.add(a,temp);
                  myPowerSet.remove(a+1);
               }
            }
         	/**process the results with PageRank*/
            for (ArrayList<String> cifu2 : myPowerSet) {
               if (cifu2.size()!=0)
               {
                  String cifu = "";
                  for (int x=0;x<cifu2.size()-1;x++)
                  {
                     cifu+=cifu2.get(x)+" ";
                  }
                  cifu+=cifu2.get(cifu2.size()-1);
               //System.out.println("yep");
               // double[][] a = new double[count][count];
                  ResultSet set = s.executeQuery("SELECT id, url, title FROM pages WHERE title LIKE '%" +cifu + "%'");
                 
               
                  while (set.next())      
                  {
                     ResultSet set2 = s2.executeQuery("SELECT pagerank FROM Pageranks WHERE page_id="+set.getInt("id"));
                     if (jet2.contains(set.getString("title")))
                     {
                        ArrayList<Double> temper = new ArrayList<Double>();
                        while (set2.next())
                        {
                           temper.add(set2.getDouble("pagerank"));
                        }
                        for (int q=0;q<jet2.size();q++)
                        {
                           if (jet2.get(q).equals(set.getString("title")))
                              temper.add(jet3.get(q));
                        }
                     
                        Double[] temp3 = (Double[]) (temper.toArray(new Double[temper.size()]));
                        Arrays.sort(temp3);
                        ResultSet set3 = s3.executeQuery("SELECT * FROM pages LEFT JOIN Pageranks on pages.id = Pageranks.page_id");
                        for (int q2=0;q2<temp3.length;q2++)
                        {
                           set3.beforeFirst();
                           while (set3.next())
                           {
                              String titular = set3.getString("title");
                              double titular2= set3.getDouble("pagerank");
                              String titular3= set3.getString("url");
                           	
                              if(titular == null) titular = "";
                           	
                              set.beforeFirst();
                              while(set.next())
                              {
                                 if ((titular.equals(set.getString("title"))) && (titular2==temp3[q2]))
                                 {
                                    
                                    jet.add(titular3);
                                    jet2.add(titular);
                                    jet3.add(titular2);
                                 } 
                              }
                           }
                        }
                     
                     }
                     else
                     {
                        while (set2.next())
                        {
                           jet.add(set.getString("url"));
                           jet2.add(set.getString("title"));
                           jet3.add(set2.getDouble("pagerank"));
                        }
                     }
                  
                  }
               	
               }
            //jet case insensitivity, play with multiple word queries, and print the results nicely.
            }
				for (int fin2=0;fin2<jet.size()-1;fin2++)
				{
				 String tempor = jet.get(fin2);
				 for (int fin3=fin2+1;fin3<jet.size();fin3++)
				 {
				  if (tempor.equals(jet.get(fin3)))
				  {
				   jet.set(fin3,"");
					jet2.set(fin3,"");
				  }
				  
				 }
				}
            for (int fin=0;fin<jet.size();fin++)
            {
               System.out.println(jet.get(fin) + " " + jet2.get(fin));
            }
         }
            catch (Exception e)
            {
               System.err.println("Cannot connect");
               e.printStackTrace();
            }
         finally
         {
            if (conn!=null)
            {
               try
               {
                  conn.close();
				  long endTime   = System.currentTimeMillis();
                  long totalTime = endTime - startTime;
                  System.out.println("done with processing time of: " + ((totalTime*.001)/60));
               }
                  catch (Exception e){
                     e.printStackTrace();
                  }
            }
         }
      	
      }
   
      
   	
      public static void matrixPrint(float[] v)
      {
         for (int x=0;x<v.length;x++)
         {
            System.out.print(v[x]+" ");
         }
      }
   	
      private static ArrayList<ArrayList<String>> powerset(String[] set) {
      
       //create the empty power set
         ArrayList<ArrayList<String>> power = new ArrayList<ArrayList<String>>();
      
       //get the number of elements in the set
         int elements = set.length;
      
       //the number of members of a power set is 2^n
         int powerElements = (int) Math.pow(2,elements);
      
       //run a binary counter for the number of power elements
         for (int i = 0; i < powerElements; i++) {
         
           //convert the binary number to a string containing n digits
            String binary = intToBinary(i, elements);
         
           //create a new set
            ArrayList<String> innerSet = new ArrayList<String>();
         
           //convert each digit in the current binary number to the corresponding element
            //in the given set
            for (int j = 0; j < binary.length(); j++) {
               if (binary.charAt(j) == '1')
                  innerSet.add(set[j]);
            }
         
           //add the new set to the power set
            power.add(innerSet);
         
         }
      
         return power;
      }
   
      private static String intToBinary(int binary, int digits) {
      
         String temp = Integer.toBinaryString(binary);
         int foundDigits = temp.length();
         String returner = temp;
         for (int i = foundDigits; i < digits; i++) {
            returner = "0" + returner;
         }
      
         return returner;
      } 
   
   }