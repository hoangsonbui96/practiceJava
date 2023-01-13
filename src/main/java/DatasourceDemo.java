

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.text.AbstractDocument.Content;

/**
 * Servlet implementation class DatasourceDemo
 */
public class DatasourceDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DataSource ds;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatasourceDemo() {
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
		Connection conn = null;
		PrintWriter out = response.getWriter();
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO: handle exception
			//out.print("smth wrong");
			e.printStackTrace();
			return;
		}
		
		//use connection		
		out.print("Connected Database");
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
