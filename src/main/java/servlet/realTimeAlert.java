import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/realTimeAlert")
public class realTimeAlert extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static String Domain  = "realtimealert.";
	static String Path    = "herokuapp.com/";
	static String Servlet = "realTimeAlert";
	static String rslt = "";
	private static int SERVER_ID;
	private static int CPU_UTILIZATION;
	private static int MEMORY_UTILIZATION;
	private static int DISK_UTILIZATION;

	static String checkForAlert = "Enter";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   response.setContentType("text/html");
		   PrintWriter out = response.getWriter();
		   PrintBody(out);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		   String operation = request.getParameter("Operation");
		   String userInput = request.getParameter("INPUT");

		   /*
		    * 1. Check to see if the user input is correctly formatted.
		    * 2. Check to see if an alert is needed.  
		    */
		   
		   if (operation.equals(checkForAlert))
		   {
		     if(validateInput(userInput)) {
		    	 parseInputAndSetAlert(userInput);
		     }
		   }

		   response.setContentType("text/html");
		   PrintWriter out = response.getWriter();
		   PrintBody(out, userInput,rslt);
	}

private void PrintBody (PrintWriter out, String userInput, String rslt)
{
   out.println("<html>");
   out.println("<body>");
   out.println("<p>");
   out.println("Enter in the SERVER_ID, CPU_UTILIZATION, MEMORY_UTILIZATION, DISK_UTILIZATION in order and separated by commas.");
   out.println("</p>");
   out.println("<p>");
   out.println("Example: 1234,89,69,65");
   out.println("</p>");
   out.print  ("<form method=\"post\"");
   out.println(" action=\"https://" + Domain + Path + Servlet + "\">");
   out.println("");
   out.println(" <table>");
   out.println("  <tr>");
   out.println("   <td>Input:");
   out.println("   <td><input type=\"text\" name=\"INPUT\" value=\"" + userInput + "\" size=30>");
   out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td>Result:");
   out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rslt + "\" size=100>");
   out.println("  </tr>");
   out.println(" </table>");
   out.println(" <br>");
   out.println(" <br>");
   out.println(" <input type=\"submit\" value=\"" + checkForAlert + "\" name=\"Operation\">");
   out.println("</form>");
   out.println("");
   out.println("</body>");
   out.println("</html>");
}

private void PrintBody (PrintWriter out)
{
   PrintBody(out,"","");
}

public static void parseInputAndSetAlert(String input) {
	
	boolean isAlert = false;
	String [] inputArray = input.split(",");
	
	setSERVER_ID(Integer.parseInt(inputArray[0]));
	setCPU_UTILIZATION(Integer.parseInt(inputArray[1]));
	setMEMORY_UTILIZATION(Integer.parseInt(inputArray[2]));
	setDISK_UTILIZATION(Integer.parseInt(inputArray[3]));
	
	String alert = "Alert," + SERVER_ID;
	
	if(CPU_UTILIZATION > 85) {
		isAlert = true;
		alert += ",CPU_UTILIZATION VIOLATED";
	}
	if(MEMORY_UTILIZATION > 75) {
		isAlert = true;
		alert += ",MEMORY_UTILIZATION VIOLATED";
	}
	if(DISK_UTILIZATION > 60) {
		isAlert = true;
		alert += ",DISK_UTILIZATION VIOLATED";
	}
	
	//if there is no alert then print no alert else print out the alert created
	if(!isAlert) {
		rslt = "No Alert," + SERVER_ID;
	}else {
		rslt = alert;
	}
}

//Check if the user placed in the correct input: no spaces, special characters
public static boolean validateInput(String input) {
	
	//makes sure 4 comma separated variables are added
	String [] inputArray = input.split(",");
	if(inputArray.length != 4) {
		rslt = "Input arguments missing or too many were added. Make sure to include 4 arguments separated by commas.";
		return false;
	}
	
	//makes sure no special/alphabetical characters and spaces are added
	char[] inputCharArr = input.toCharArray();
	for(char c : inputCharArr) {
		if(c > '9' || c < '0' && c != ',') {
			rslt = "Input entered incorrectly, make sure you do not include alphabetical/special characters or spaces.";
			return false;
		}
	}
	return true;
}

//Getters and Setters
public static int getSERVER_ID() {
	return SERVER_ID;
}

public static void setSERVER_ID(int sERVER_ID) {
	SERVER_ID = sERVER_ID;
}

public static int getCPU_UTILIZATION() {
	return CPU_UTILIZATION;
}

public static void setCPU_UTILIZATION(int cPU_UTILIZATION) {
	CPU_UTILIZATION = cPU_UTILIZATION;
}

public static int getMEMORY_UTILIZATION() {
	return MEMORY_UTILIZATION;
}

public static void setMEMORY_UTILIZATION(int mEMORY_UTILIZATION) {
	MEMORY_UTILIZATION = mEMORY_UTILIZATION;
}

public static int getDISK_UTILIZATION() {
	return DISK_UTILIZATION;
}

public static void setDISK_UTILIZATION(int dISK_UTILIZATION) {
	DISK_UTILIZATION = dISK_UTILIZATION;
}

}

