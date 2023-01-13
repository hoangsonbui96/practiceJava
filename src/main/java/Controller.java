

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.spec.DSAGenParameterSpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.User;
import database.Account;


/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			InitialContext initContext = new InitialContext();
			Context env = (Context)initContext.lookup("java:comp/env");
			ds = (DataSource)env.lookup("jdbc/webshop");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			throw new ServletException();
		}	
	}
    
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			Class.forName("com.postgresql.jdbc.Driver");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		Connection conn = null;
		try {
			DriverManager.getConnection("jdbc:postgresql://localhost:5432/webshop","postgres","akb1564ltt");
		} catch (Exception e) {
			// TODO: handle exception
			out.print("Can't not connect sever");
			return;
		}
		out.print("Connected to Database");
			
		try {
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		PrintWriter out = response.getWriter();		
		String action = request.getParameter("action");
		Connection conn = null;
		
		if(action == null) {
			out.println("unrecognized action");
			return;
		}
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new ServletException();
		}
		
		if(action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			User user = new User(email, password);
			
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			
			Account account = new Account(conn);
			
			try {
				if(account.login(email, password)) {
					request.getRequestDispatcher("/loginsucess.jsp").forward(request, response);
					conn.close();
				}else {
					request.setAttribute("message", "email address or password is not recognized");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
					conn.close();
				}
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}							
		}
			
		if(action.equals("doregister")) {
			//check password trùng khớp
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String repeatePassword = request.getParameter("repeatpassword");
			
			if(!password.equals(repeatePassword)) {
				request.setAttribute("message", "password do not match");
				request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
			}
			
			//check validate input
			User user = new User(email, password);
			
			if(user.validate(email)) {
				request.setAttribute("message", "smth wrong");
				request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
			}
			
			//check exist email
			Account account = new Account(conn);
			
			try {
				if(account.exists(email)) {
					request.setAttribute("message", "email already exist");
					request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				account.create(email, password);
				request.setAttribute("email", email);
				request.getRequestDispatcher("/createsucess.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}	
	}

	
}
