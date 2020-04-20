

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SWE432Assignment8")
public class SWE432Assignment8 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 static enum Data {FNAME, LNAME, LOCATION, SCORE};
	 static String RESOURCE_FILE = "entries.txt";
	 static final String VALUE_SEPARATOR = ";";
	 
	 static String OperationAdd = "Add";
	
    public SWE432Assignment8() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		printPage(out);
	}


	//prints the output and persisted data -----------------------------------------------------------------------------------------
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		   //String firstName = request.getParameter("fName");
		   String firstName = request.getParameter(Data.FNAME.name());
		   String lastName = request.getParameter(Data.LNAME.name());
		   String location = request.getParameter(Data.LOCATION.name());
		   
		   int comf = Integer.valueOf(request.getParameter("comfort"));
		   int sati = Integer.valueOf(request.getParameter("satisfaction"));
		   int backsup = Integer.valueOf(request.getParameter("backSupport"));
		   int mat = Integer.valueOf(request.getParameter("material"));
		   int cush = Integer.valueOf(request.getParameter("cushion"));
		   int ans = (comf + sati + backsup + mat + cush)/5;
		   
		   //checks to see if all values are valid
		   boolean errors = checkValues(firstName, lastName, location, comf, sati, backsup, mat, cush, ans);
		 
		   String grade ="";
		   
		   if(ans >= 90) {
			   grade = "A";
		   }else if(ans >= 80 && ans < 90) {
			   grade = "B";
		   }else if(ans >= 70 && ans < 80) {
			   grade = "C";
		   }else if(ans >= 60 && ans < 70) {
			   grade = "D";
		   }else {
			   grade = "F";
		   }
		   
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 
		 if(!errors) {
		 
			 PrintWriter entriesPrintWriter = new PrintWriter(new FileWriter(RESOURCE_FILE, true), true);
		     entriesPrintWriter.println(firstName + VALUE_SEPARATOR + lastName + VALUE_SEPARATOR + location + VALUE_SEPARATOR + grade);
		     
		     entriesPrintWriter.close();
		     
		     //printOutput(out,ans, firstName, lastName, grade, location);
		     printResponse(out, RESOURCE_FILE);
			 
		 }
	}
	
	private void printResponse(PrintWriter out, String resourcePath) {
		//out.println("<body onLoad=\"setFocus()\">");
//	    out.println("<p>");
//	    out.println("A simple example that demonstrates how to persist data to a file");
//	    out.println("</p>");
//	    out.println("");
//	    out.println(" <table>");
		
		

	    try {
	        out.println("  <tr>");
	        out.println("   <th>First Name</th>");
	        out.println("   <th>Last Name</th>");
	        out.println("   <th>Location</th>");
	        out.println("  </tr>");
	        File file = new File(resourcePath);
	        if(!file.exists()){
	          out.println("  <tr>");
	          out.println("   <td>No entries persisted yet.</td>");
	          out.println("  </tr>");
	          return;
	        }

	        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
	        String line;
	        while ((line = bufferedReader.readLine()) != null) {
	          String []  entry= line.split(VALUE_SEPARATOR);
	          int num = 1;
	          out.println("  <p>");
	          for(String s : entry) {
	        	  if(num == 1) {
	        		  out.print(s);
	        	  }else if(num == 2) {
	        		  out.print(" "+s );
	        	  }else if(num == 3) {
	        		  out.print(" rated the chair located in " + s);
	        	  }else if(num == 4) {
	        		  out.print(" : " + s);
	        	  }
	        	  else {
	        		  num = 0;
	        	  }
	        	  num++;
	          }
	          out.println("</p>");
	          //out.println("  <tr>");
	          
	          //for(String value: entry){
	            //  out.println("   <td>"+value+"</td>");
	          //}
	        
//	          int num = 1;
//	          for(int i = 0; i < entry.length; i ++) {
//	        	  if(num + i % 4 == 0  && num + i % 2 != 0) { //grade
//	        		  out.print(" a rating of "+entry[i]+" ");
//	        	  }else if(num + i % 3 == 0) { //location
//	        		  out.print(" gave the chair located in "+entry[i]+" ");
//	        	  }else if(num + i % 4 == 0  && num + i % 2 == 0) {
//	        		  out.print(" "+entry[i]+" ");
//	        	  }else {
//	        		  out.print(" "+entry[i]+" ");
//	        	  }
//	        	  num++;
//	          }
//	          out.println("  </p>");
	          //out.println("  </tr>");
	        }
	        bufferedReader.close();
	      } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	     out.println(" </table>");
	     out.println("");
	     out.println("</body>");
	}
//	private void printOutput(PrintWriter out, int ans, String fName, String lName, String grade, String location) {
//		out.println("<html>");
//		out.println("<style>");
//		out.println("html,body{height: 100%; width: 100%; margin-top:0;background: linear-gradient(-45deg, #00ccff, #ffffff)}");
//		out.println("</style>");
//		out.println("<h1>");
//		out.println("Hello " + fName + " " + lName);
//		out.println("</h1>");
//		out.println("<p>");
//		out.println("Your chair located in "+ location +" received a grade of: "+ ans + " " + grade +".");
//		out.println("</p>");
//		out.println("<br>");
//		out.println("<p>");
//		out.println("Collaboration Summary:");
//		out.println("\nEdwin: worked on the css and format of the website and parsing of radio buttons\n" + 
//				"Will: worked on the text area apearing and form actions\n");
//		out.println("</p>");
//		out.println("</html>");
//	}
	
	public boolean checkValues(String firstName, String lastName, String location, int comf, int sati, int backsup, int mat, int cush, int ans) {
		
		return false;
	}
	
	private void printPage (PrintWriter out) {
		   out.println("<html>");

	
		 out.println("<style>");
		 out.println("html,body{height: 100%; width: 100%; margin-top:0;}");
		 out.println(".ta {margin-bottom:5%;}");
		 out.println("form{text-align:center; margin-left:auto;margin-right:auto;width:75%;}");
		 out.println("input{text-align:center;margin-bottom:5%;margin-right:7px;margin-left:15px;}");
		 out.println("section{background: linear-gradient(-45deg, #00ccff, #ffffff)}");
		 out.println("h3{text-align:center;}");
		 out.println(".btn{  margin-left: 30%;\n" + 
		 		"  margin-right: 30%;\n" + 
		 		"  margin-bottom: 5%;\n" + 
		 		"  width: 40%;\n" + 
		 		"  font-size: 15px;\n" + 
		 		"  border: 0;\n" + 
		 		"  background: #743ad5;\n" + 
		 		"  color: #fff;\n" + 
		 		"  padding: 12px 50px;\n" + 
		 		"  border-radius: 20px;\n" + 
		 		"  cursor: pointer;\n" + 
		 		"  transition: 0.5s;}");
		 out.println("h1 {\n" + 
		 		"  text-transform: uppercase;\n" + 
		 		"  font-size: 60px;\n" + 
		 		"  text-align: center;\n" + 
		 		"  letter-spacing: 14px;\n" + 
		 		"}");
		 out.println(".title {\n" + 
		 		"  border-style: solid;\n" + 
		 		"  margin-left: 10%;\n" + 
		 		"  margin-right: 10%;\n" + 
		 		"\n" + 
		 		"  border-width: 5px;\n" + 
		 		"}");
		 out.println("</style>");
		
		out.println("<section>");
		out.println("<div class=\"title\">");
		out.println("<h1>Rate your chair</h1>");
		out.println("<h3>by Edwin O'Meara, Will Dubuque</h3>");
		out.println("</div>");
		out.println("<br>");

		out.println("<form method=\"post\">");
		out.println("<label for=\"fname\">First name:</label>");
		//out.println("<input type=\"text\" id=\"fName\" name=\"fName\">");
		 out.println("   <td><input type=\"text\" name=\""+Data.FNAME.name()
	      +"\"></td>");
		out.println("<label for=\"lname\">Last name:</label>");
		//out.println("<input type=\"text\" id=\"lName\" name=\"lName\">");
		out.println("   <td><input type=\"text\" name=\""+Data.LNAME.name()
	      +"\"></td>");
		out.println("<label for=\"userLocation\">Location:</label>");
		//out.println("<input type=\"text\" id=\"userLocation\" name=\"userLocation\">");
		 out.println("   <td><input type=\"text\" name=\""+Data.LOCATION.name()
	      +"\"></td>");
		out.println("<div>");
		out.println("<h3>Comfort Rating</h3>");
		out.println("<label><input type=\"radio\" name=\"comfort\" id=\"c4\" onclick=\"cshowReason()\" value=100>Great</label>");
		out.println("<label><input type=\"radio\" name=\"comfort\" id=\"c3\" onclick=\"cshowReason()\" value=75>Good</label>");
		out.println("<label><input type=\"radio\" name=\"comfort\" id=\"c2\" onclick=\"cshowReason()\" value=50>Okay</label>");
		out.println("<label><input type=\"radio\" name=\"comfort\" id=\"c1\" onclick=\"cshowReason()\" value=25>Bad</label>");

		out.println("<h3>Back Support Rating</h3>");
		out.println("<label><input type=\"radio\" name=\"backSupport\" id=\"b4\" onclick=\"bshowReason()\" value=100>Great </label>");
		out.println("<label><input type=\"radio\" name=\"backSupport\" id=\"b3\" onclick=\"bshowReason()\" value=75>Good </label>");
		out.println("<label><input type=\"radio\" name=\"backSupport\" id=\"b2\" onclick=\"bshowReason()\" value=50>Okay </label>");
		out.println("<label><input type=\"radio\" name=\"backSupport\" id=\"b1\" onclick=\"bshowReason()\" value=25>Bad</label>");


		out.println("<h3>Cushion Rating</h3>");
		out.println("<label><input type=\"radio\" name=\"cushion\" id=\"cus4\" onclick=\"cusshowReason()\" value=100>Great</label>");
		out.println("<label><input type=\"radio\" name=\"cushion\" id=\"cus3\" onclick=\"cusshowReason()\" value=75>Good</label>");
		out.println("<label><input type=\"radio\" name=\"cushion\" id=\"cus2\" onclick=\"cusshowReason()\" value=50>Okay</label>");
		out.println("<label><input type=\"radio\" name=\"cushion\" id=\"cus1\" onclick=\"cusshowReason()\" value=25>Bad</label>");
	

		out.println("<h3>Material Rating</h3>");
		out.println("<label><input type=\"radio\" name=\"material\" id=\"m4\" onclick=\"mshowReason()\" value=100>Great</label>");
		out.println("<label><input type=\"radio\" name=\"material\" id=\"m3\" onclick=\"mshowReason()\" value=75>Good</label>");
		out.println("<label><input type=\"radio\" name=\"material\" id=\"m2\" onclick=\"mshowReason()\" value=50>Okay</label>");
		out.println("<label><input type=\"radio\" name=\"material\" id=\"m1\" onclick=\"mshowReason()\" value=25>Bad</label>");


		out.println("<h3>Overall Satisfaction</h3>");
		out.println("<label><input type=\"radio\" name=\"satisfaction\" id=\"o4\" onclick=\"oshowReason()\" value=100>Great</label>");
		out.println("<label><input type=\"radio\" name=\"satisfaction\" id=\"o3\" onclick=\"oshowReason()\" value=75>Good</label>");
		out.println("<label><input type=\"radio\" name=\"satisfaction\" id=\"o2\" onclick=\"oshowReason()\" value=50>Okay</label>");
		out.println("<label><input type=\"radio\" name=\"satisfaction\" id=\"o1\" onclick=\"oshowReason()\" value=25>Bad</label>");
		out.println("</div>");
		out.println("<input class=\"btn\" type=\"submit\" value=\"Submit\" name=\"rateTheChair\"></button>");

		out.println("</form>");

		out.println("</section>");

		out.println("</html>");
		
	}

}
