   public class FunctionDef
   {
      String name;
      String body;
      String args;
      public FunctionDef(String a, String b, String c)
      {
         name = a;
         body = c;
         args = b;
      }
      public String getName()
      {
         return name;
      }
      public String getBody()
      {
         return body;
      }
      public String getArgs()
      {
         return args;
      }
		public void changeName(String a)
		{
		 name = a;
		}
		public void changeBody(String b)
		{
		 body=b;
		}
		public void changeArgs(String c)
		{
		args=c;
		}
      public String toString()
      {
         String returner= "";
         returner+=(name+body+args);
         return returner;
      }
   }