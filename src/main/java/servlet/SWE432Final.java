import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SWE432Final")
public class SWE432Final extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 static enum Data {INPUT1};
	 
	 static String i1 = "";
	 static int numOfOperators = 0;
	
	 static int userDecision = 1;
	 static String OperationAdd = "Add";
	 
	 static String errorString = "";
	 
	 static boolean errors = false;
	
    public SWE432Final() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		printPage(out);
	}

	private void printPage (PrintWriter out) {
		 out.println("<html>");
		 out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">");
		 out.println("<style>");
		 out.println("html,body{height: 100%; width: 100%; margin-top:0;}");
		 out.println(".ta {margin-bottom:5%;}");
		 out.println("form{text-align:center; margin-left:auto;margin-right:auto;width:75%;}");
		 out.println("input{text-align:center;margin-bottom:5%;margin-right:7px;margin-left:15px;}");
		 out.println("section{background: linear-gradient(-45deg, #00ccff, #ffffff)} ");
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
		 out.println("</style>");
		
		out.println("<section>");

		out.println("<div class=\"jumbotron jumbotron-fluid\">");
		out.println(" <div class=\"container\">");
		out.println("<h1 class=\"display-4\">SWE432 Final Exam</h1>");
		out.println("<p class=\"lead\">Edwin O'Meara 5/19/2020</p>");
		out.println("</div>");
		out.println("</div>");
		
		
		out.println("<br>");
		

		out.println("<form method=\"post\">");
		
		out.println("<h3>Please enter in your Variables/logical Operators(OR/AND), all separated by spaces here.</h3>");
		out.println("<p>Only up to 2 logical operators permitted. OR, ||, |, and, &, &&</p>");
		out.println("<p>eg. A && B or C </p>");
	
		out.println("   <td><input type=\"text\" name=\""+Data.INPUT1.name()
	      +"\"></td>");
		
		
		
		out.println("<p>Please choose a way to display the truth table</p>");
		out.println("<label><input type=\"radio\" name=\"decision\" id=\"cus3\" value=1>TRUE/FALSE</label>");
		out.println("<label><input type=\"radio\" name=\"decision\" id=\"cus2\" value=2>T/F</label>");
		out.println("<label><input type=\"radio\" name=\"decision\" id=\"cus1\" value=3>1/0</label>");

		out.println("<br>");
		out.println("<input class=\"btn\" type=\"submit\" value=\"Submit\" name=\"rateTheChair\"></button>");
		
		out.println("</form>");

		out.println("</section>");

		out.println("</html>");
		
	}
	
	//prints the output and persisted data -----------------------------------------------------------------------------------------
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		   i1 = request.getParameter(Data.INPUT1.name());
		  
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
			 userDecision = Integer.valueOf(request.getParameter("decision"));
			
		   
		   printResponse(out); 

	
	}
	
	
	
	private void printResponse(PrintWriter out) {

	    	 out.println("<html>");
	    	 out.println("<head>");
	    	 out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">");
			 out.println("<style>");
			 out.println("html,body{height: 100%; width: 100%; margin-top:0;}");
			 out.println(".ta {margin-bottom:5%;}");
			 out.println("body{background: linear-gradient(-45deg, #00ccff, #ffffff);}");
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
			 out.println("th, td {\r\n" + 
			 		"  text-align: left;\r\n" + 
			 		"  padding: 2px;\r\n" + 
			 		"}");
			 out.println("\r\n" + 
			 		"tr:nth-child(even) {background-color: #00ccff;}\r\n" + 
			 		"tr:nth-child(odd) {background-color: #00cccc;}\r\n" + 
			 		"tr:hover {background-color: #f5f5f5;\r\n}"
			 		+ "table, th, td {\r\n" + 
			 		"  border: 1px solid black;\r\n" + 
			 		"}");
			 out.println("table {\r\n" + 
			 		"  border-collapse: collapse;\r\n" + 
			 		"  width: 50%;\r\n" + 
			 		"}");
			 
			 out.println("</style>");
	    	out.println("</head>");
	    	out.println("<body>");
	
	    	out.println("");
	    	
	    	String [] arguments = configureString(i1);
	    	String printInput = "";
	    	
	    	for(int i = 0; i < arguments.length; i ++) {
	    		printInput += arguments[i] + " ";
	    	}
	    	out.println("<p>Your input was "+printInput+"</p>" );
	    	
	    	//array of correct arguments
	    	int [] truthArray = calculateTruthValues(arguments);
	    	
	    	//converted array of strings
	    	String [] convertedTruth = new String[truthArray.length];
	    	
	    	String trueInput = "";
	    	String falseInput = "";
	    	
	    	//gets the decision the user made for their desired display, T/F 1/0 TRUE/FALSE
	    	if(userDecision == 1) {
	    		trueInput = "TRUE";
	    		falseInput = "FALSE";
	    	}else if(userDecision == 2) {
	    		trueInput = "T";
	    		falseInput = "F";
	    	}else if(userDecision ==3) {
	    		trueInput = "1";
	    		falseInput = "0";
	    	}
	    	
	    	
	    	for(int x = 0; x < truthArray.length; x ++) {
	    		
	    		if(truthArray[x] == 1) {
	    			convertedTruth[x] = trueInput;
	    		}else if(truthArray[x] == 2){
	    			break;
	    		}else {
	    			convertedTruth[x] = falseInput;
	    		}
	    	}
	    
	    		if(numOfOperators == 1) {
	    			out.println("<p>" + falseInput+" "+ arguments[1]+" "+falseInput + " = "+convertedTruth[3]+"</p>");
	    			out.println("<p>"+ falseInput+" "+ arguments[1]+" "+trueInput+" = "+convertedTruth[2]+"</p>");
	    			out.println("<p>" +trueInput+ " "+ arguments[1]+" "+falseInput + " = "+convertedTruth[1]+"</p>");
	    			out.println("<p>"+trueInput+ " "+ arguments[1]+" "+trueInput+" = "+convertedTruth[0] +"</p>");
	    		}else {
	    			out.println("<p>"+ falseInput+" "+ arguments[1]+" "+falseInput + " "+ arguments[3]+" "+falseInput + " = "+convertedTruth[0]+"</p>");
	    			out.println("<p>"+ falseInput+" "+ arguments[1]+" "+falseInput + " "+ arguments[3]+" "+trueInput+" = "+convertedTruth[1]+"</p>");
	    			out.println("<p>"+ falseInput+" "+ arguments[1]+" "+trueInput+" "+ arguments[3]+" "+falseInput + " = "+convertedTruth[2]+"</p>");
	    			out.println("<p>"+ falseInput+" "+ arguments[1]+" "+trueInput+" "+ arguments[3]+" "+trueInput+" = "+convertedTruth[3]+"</p>");
	    			out.println("<p>"+trueInput+ " "+ arguments[1]+" "+falseInput + " "+ arguments[3]+" "+falseInput + " = "+convertedTruth[4]+"</p>");
	    			out.println("<p>"+trueInput+ " "+ arguments[1]+" "+falseInput +  " "+ arguments[3]+" "+trueInput+" = "+convertedTruth[5]+"</p>");
	    			out.println("<p>"+trueInput+ " "+ arguments[1]+" "+trueInput+" "+ arguments[3]+"  "+falseInput + " = "+convertedTruth[6]+"</p>");
	    			out.println("<p>"+trueInput+ " "+ arguments[1]+" "+trueInput+ " "+ arguments[3]+" "+trueInput+" = "+convertedTruth[7]+"</p>");
	    		}
	    		
	    
	
	     out.println("</body>");
	     out.println("</html>");
	  
	}


	
	public String[] configureString(String input) {
		
		String s = input.replaceAll("\\s", "~");
		char[] sArr = s.toCharArray();
		int arrLength = 0;
		for(int x = 0; x < sArr.length; x++) {
			if(sArr[x] == '~') {
				arrLength++;
			}
		}
		
		String [] ans = new String [arrLength + 1];
		String d = "";
		
		int count = 0;
		
		for(int i = 0; i < sArr.length; i++) {
			if(sArr[i] == '~') {
				ans[count] = d;
				count++;
				d = "";
			}
			else {
			d += sArr[i] + "";
			
			if(i + 1 == sArr.length) {
				ans[count] = d;
			}
		}
		}
		return ans;
	}
	
	//1 = true, 0 = false
	//This is really really bad code. Sorry but I could not figure out the correct algorithm.
	//I tried multiple times through code and on paper but could not get the correct output
	public int [] calculateTruthValues(String [] sArr) {
		//String [] ans = new String [8];
		int [] numsArr = new int[8];
		Arrays.fill(numsArr, 2);
		
		//first logical argument (or == 1, 1, 1, 0) (and == 1, 0, 0, 0)
		if(1 < sArr.length) {
			numOfOperators = 1;
		if(sArr[1].toUpperCase().equals("OR")|| sArr[1].toUpperCase().equals("||")|| sArr[1].toUpperCase().equals("|")) {
			numsArr[0] = 1; 
			numsArr[1] = 1;
			numsArr[2] = 1;
			numsArr[3] = 0;
			if(3 < sArr.length) {
				numOfOperators = 2;
				if(sArr[3].toUpperCase().equals("OR")|| sArr[3].toUpperCase().equals("||")|| sArr[3].toUpperCase().equals("|")) {
					numsArr[0] = 0; 
					numsArr[1] = 1;
					numsArr[2] = 1;
					numsArr[3] = 1;
					numsArr[4] = 1;
					numsArr[5] = 1;
					numsArr[6] = 1;
					numsArr[7] = 1;
				}else if(sArr[3].toUpperCase().equals("AND")|| sArr[3].toUpperCase().equals("&&")|| sArr[3].toUpperCase().equals("&")) {
					numsArr[0] = 0; 
					numsArr[1] = 0;
					numsArr[2] = 0;
					numsArr[3] = 1;
					numsArr[4] = 1;
					numsArr[5] = 1;
					numsArr[6] = 1;
					numsArr[7] = 1;
				}
			}
		}else if(sArr[1].toUpperCase().equals("AND")|| sArr[1].toUpperCase().equals("&&")|| sArr[1].toUpperCase().equals("&")) {
			numOfOperators = 1;
			numsArr[0] = 1;
			numsArr[1] = 0;
			numsArr[2] = 0;
			numsArr[3] = 0;
			if(3 < sArr.length) {
				numOfOperators = 2;
				if(sArr[3].toUpperCase().equals("OR") || sArr[3].toUpperCase().equals("||")|| sArr[3].toUpperCase().equals("|")) {
					numsArr[0] = 0;
					numsArr[1] = 1;
					numsArr[2] = 0;
					numsArr[3] = 1;
					numsArr[4] = 0;
					numsArr[5] = 1;
					numsArr[6] = 1;
					numsArr[7] = 1;
				}else if(sArr[3].toUpperCase().equals("AND") || sArr[3].toUpperCase().equals("&&")|| sArr[3].toUpperCase().equals("&")) {
					numsArr[0] = 0;
					numsArr[1] = 0;
					numsArr[2] = 0;
					numsArr[3] = 0;
					numsArr[4] = 0;
					numsArr[5] = 0;
					numsArr[6] = 0;
					numsArr[7] = 1;
				}
			}
			}
		}
		
		return numsArr;
	}
	
	


}
