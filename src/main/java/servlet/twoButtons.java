package com.GMUSWE432;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class twoButtons
 */
@WebServlet("/twoButtons")
public class twoButtons extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Location of servlet.
	static String Domain  = "swe432-twobuttons.";
	static String Path    = "herokuapp.com/";
	static String Servlet = "twoButtons";
//	static String Domain  = "localhost:8080/";
//	static String Path    = "/SWE432/";
//	static String Servlet = "twoButtons";

	// Button labels
	static String OperationAdd = "Add";
	static String OperationSub = "Subtract";
	static String OperationMul = "Multiply";

	// Other strings.
	static String Style ="https://www.cs.gmu.edu/~offutt/classes/432/432-style.css";

	/** *****************************************************
	 *  Overrides HttpServlet's doPost().
	 *  Converts the values in the form, performs the operation
	 *  indicated by the submit button, and sends the results
	 *  back to the client.
	********************************************************* */
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public twoButtons() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//PrintWriter out = response.getWriter();
		 response.setContentType("text/html");
		   PrintWriter out = response.getWriter();
		   PrintHead(out);
		   PrintBody(out);
		   PrintTail(out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Float rslt   = new Float(0.0);
		   Float lhsVal = new Float(0.0);
		   Float rhsVal = new Float(0.0);
		   String operation = request.getParameter("Operation");
		   String lhsStr = request.getParameter("LHS");
		   String rhsStr = request.getParameter("RHS");
		   if ((lhsStr != null) && (lhsStr.length() > 0))
		      lhsVal = new Float(lhsStr);
		   if ((rhsStr != null) && (rhsStr.length() > 0))
		      rhsVal = new Float(rhsStr);

		   if (operation.equals(OperationAdd))
		   {
		      rslt = new Float(lhsVal.floatValue() + rhsVal.floatValue());
		
		   }
		   else if (operation.equals(OperationSub))
		   {
		      rslt = new Float(lhsVal.floatValue() - rhsVal.floatValue());
		
		   }
		   else {
			   rslt = new Float(lhsVal.floatValue() * rhsVal.floatValue());
		
		   }

		   response.setContentType("text/html");
		   PrintWriter out = response.getWriter();
		   PrintHead(out);
		   PrintBody(out, lhsStr, rhsStr, rslt.toString());
		   PrintTail(out);
	}
	private void PrintHead (PrintWriter out)
	{
	   out.println("<html>");
	   out.println("");

	   out.println("<head>");
	   out.println("<title>Two buttons example</title>");
	   out.println(" <link rel=\"stylesheet\" type=\"text/css\" href=\"" + Style + "\">");
	   out.println("</head>");
	   out.println("");
	} // End PrintHead

/** *****************************************************
 *  Prints the <BODY> of the HTML page with the form data
 *  values from the parameters.
********************************************************* */
private void PrintBody (PrintWriter out, String lhs, String rhs, String rslt)
{
   out.println("<body>");
   out.println("<p>");
   out.println("A simple example that demonstrates how to operate with");
   out.println("multiple submit buttons.");
   out.println("</p>");
   out.println("<br>");
   out.println("<p>");
   out.println("By Edwin O'Meara");
   out.println("</p>");	
	
   out.print  ("<form method=\"post\"");
   out.println(" action=\"https://" + Domain + Path + Servlet + "\">");
   out.println("");
   out.println(" <table>");
   out.println("  <tr>");
   out.println("   <td>First value:");
   out.println("   <td><input type=\"text\" name=\"LHS\" value=\"" + lhs + "\" size=5>");
   out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td>Second value:");
   out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rhs + "\" size=5>");
   out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td>Result:");
   out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rslt + "\" size=6>");
   out.println("  </tr>");
   out.println(" </table>");
   out.println(" <br>");
   out.println(" <br>");
   out.println(" <input type=\"submit\" value=\"" + OperationAdd + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + OperationSub + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + OperationMul + "\" name=\"Operation\">");
   out.println(" <input type=\"reset\" value=\"Reset\" name=\"reset\">");
   out.println("</form>");
   out.println("");
   out.println("</body>");
} // End PrintBody

/** *****************************************************
 *  Overloads PrintBody (out,lhs,rhs,rslt) to print a page
 *  with blanks in the form fields.
********************************************************* */
private void PrintBody (PrintWriter out)
{
   PrintBody(out, "", "", "");
}

/** *****************************************************
 *  Prints the bottom of the HTML page.
********************************************************* */
private void PrintTail (PrintWriter out)
{
   out.println("");
   out.println("</html>");
} // End PrintTail

}
