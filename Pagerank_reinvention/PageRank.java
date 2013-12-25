   import java.sql.*;
   import java.util.*;
	import java.math.*;
   public class PageRank
   {
      BigDecimal d; //scalar
      BigDecimal[][] a;  //M
      BigDecimal EPSILON = new BigDecimal(.00001);
      int m;
   
      public PageRank(BigDecimal[][] a1, BigDecimal e1)
      {
         a = a1;
         d = e1;
			//e = e2;
      }
      public BigDecimal[] getRanks()                                       
      {
		 BigDecimal delta = new BigDecimal(1000000);
		 BigDecimal[] returner = new BigDecimal[a[0].length];
		 for (int x=0;x<returner.length;x++)
		 {
		  returner[x]= new BigDecimal(1.0/a[0].length);
		 }
		 BigDecimal[] temp= returner;       //what happened to temp's values?
		 BigDecimal[] source = new BigDecimal[a[0].length];
		 for (int y=0;y<source.length;y++)
		 {
		  source[y] = (BigDecimal.ONE.subtract(d)).divide((new BigDecimal(a[0].length)),100,RoundingMode.HALF_UP);
       }
		 while (delta.compareTo(EPSILON)>0)
		 {
		  returner=vectorAdd(vectorMultiply(a,scalarMultiply(d,temp)), source);
		  delta = manhattanDistance(vectorSubtract(returner,temp));
		  temp = returner;
		 }
		  //System.out.println(delta);
		 
		 //System.out.println(count);
		 return returner;
      }
   
      public BigDecimal[] vectorMultiply(BigDecimal[][] b, BigDecimal[] v)
      {
         BigDecimal[] returner = new BigDecimal[b.length];
         BigDecimal element = new BigDecimal(0);
         for (int x =0; x<b.length;x++)
         {
            for (int y=0;y<returner.length;y++)
            {
				  if (b[x][y]==null)
				  {
				   b[x][y]=BigDecimal.ZERO;
				  }
              element= element.add(b[x][y].multiply( v[y]));
            }
            returner[x] = element;
				element=BigDecimal.ZERO;
         }
			return returner;
      }
		public BigDecimal[] vectorAdd(BigDecimal[] v1, BigDecimal[] v2)
		{
		 BigDecimal[] returner = new BigDecimal[v1.length];
		 for (int x=0;x<v1.length;x++)
		 {
		  returner[x] = v1[x].add(v2[x]);
		 }
		 return returner;
		}
		
		public BigDecimal[] scalarMultiply(BigDecimal s, BigDecimal[] v)
		{
		 BigDecimal[] returner = new BigDecimal[v.length];
		 for (int x = 0;x<v.length;x++)
		 {
		  returner[x] = s.multiply(v[x]);
		 }
		 return returner;
		}
		
		public BigDecimal[] vectorSubtract(BigDecimal[] v1, BigDecimal[] v2)
		{
		BigDecimal[] returner = new BigDecimal[v1.length];
		 for (int x=0;x<v1.length;x++)
		 {
		  returner[x] = v1[x].subtract( v2[x]);
		 }
		 return returner;

		}
		public BigDecimal manhattanDistance(BigDecimal[] v)
		{
		 BigDecimal returner = new BigDecimal(0);
		 for (int x = 0;x<v.length;x++)
		 {
		 returner = returner.add(v[x].abs());
		 }
		 return returner;
		}
   }