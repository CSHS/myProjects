   import java.sql.*;
   import java.util.*;
   import java.math.*;
   import org.jsoup.nodes.Document;
   import org.jsoup.Jsoup;
   public class Soup {
   
    // method main(): ALWAYS the APPLICATION entry point
      public static void main (String[] args) //throws Exception
      {
  
         Connection conn = null;
       
         try
         {
            String userName = "root";
            String url = "jdbc:mysql://localhost/tjsearch";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url,userName, "");
            Statement s = conn.createStatement ();
            Statement s2 = conn.createStatement();
            int count=0;
            int count2=0;
         	//System.out.println("yep");
           // double[][] a = new double[count][count];
            ResultSet set = s.executeQuery("SELECT id, url FROM pages");
            while (set.next())      //ptsa is highest ranked?
            { 
				//get all the keywords by webpage title, and store into database table.
				   try{
               Document doc = Jsoup.connect(set.getString("url")).get();
               String temp = doc.title();
               s2.executeUpdate("UPDATE pages SET title="+"'"+temp+"'"+"WHERE id="+set.getInt("id"));
					}
					catch (Exception f)
					{
					}
            	
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
                  System.out.println("derr");
               }
                  catch (Exception e){
                     e.printStackTrace();
                  }
            }
         }
      	
         /*float[][] a = {{0,0,.33f,0,1},{.25f,0,.33f,0,0},{.25f,1,0,.5f,0},{.25f,0,.33f,0,0},{.25f,0,0,.5f,0}};
       
         //float[] e = {0.1f,0.1f,0.1f};
         float[] e2 = {4,5,6};
         float e = .85f;
       //tech lab project: store the elements of array into separate varialbe and then call the methods (pointers may work differently in c++).
         
         //long startTime = System.nanoTime();
         PageRank rank = new PageRank(a,e);
      	float[] r = rank.getRanks();
      	for (int x=0;x<r.length;x++)
      	{
      	 System.out.println(r[x]);
      	}
         //long endTime = System.nanoTime();
      
         //long duration = endTime - startTime;
      	//System.out.println(duration);
         //matrixPrint(rank.vectorMultiply(a,e));
      	//matrixPrint(rank.vectorAdd(e,e2));
      	//matrixPrint(rank.scalarMultiply(s,e));
      	//matrixPrint(rank.vectorSubtract(e,e2));
      	//System.out.print(rank.manhattanDistance(e2));
        //public static void main(String[] args) throws Exception {*/
               /* String crawlStorageFolder = "C:\Users\David\Desktop\computer science\java";
                int numberOfCrawlers = 7;
      
                CrawlConfig config = new CrawlConfig();
                config.setCrawlStorageFolder(crawlStorageFolder);
      
                /*
                 * Instantiate the controller for this crawl.
                 */
               // PageFetcher pageFetcher = new PageFetcher(config);
                //RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
                //RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
                //CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
      
                /*
                 * For each crawl, you need to add some seed urls. These are the first
                 * URLs that are fetched and then the crawler starts following links
                 * which are found in these pages
                 */
                //controller.addSeed("http://www.ics.uci.edu/~welling/");
                //controller.addSeed("http://www.ics.uci.edu/~lopes/");
                //controller.addSeed("http://www.ics.uci.edu/");
      
                /*
                 * Start the crawl. This is a blocking operation, meaning that your code
                 * will reach the line after this only when crawling is finished.
                 */
                //controller.start(BasicCrawler.class, numberOfCrawlers);  */  
      }
   
      
   	
      public static void matrixPrint(float[] v)
      {
         for (int x=0;x<v.length;x++)
         {
            System.out.print(v[x]+" ");
         }
      }
   }