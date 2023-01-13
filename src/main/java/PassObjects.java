

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.User;

/**
 * Servlet implementation class PassObjects
 */
public class PassObjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassObjects() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		User user1 = new User("Bob", 1);
		User user2 = new User("Mike", 2);
		User user3 = new User("Sue", 3);
		
		request.setAttribute("user1", user1);
		
		HttpSession session = request.getSession();
		session.setAttribute("user2", user2);
		session.setAttribute("user3", user3);
		
		//ServletContext context = getServletContext();
		//context.setAttribute("user3", user3);
		
		Map<String, String> map = new HashMap<>();
		map.put("fruit", "apple");
		request.setAttribute("map1", map);
		
		request.setAttribute("link", "<a href='something.com'>Click Here</a>");
		
		List<User> list1 = new ArrayList<User>();
		
		list1.add(new User("Nam", 1));
		list1.add(new User("Long", 2));
		list1.add(new User("Chien", 3));
		
		session.setAttribute("list1", list1);
		
		request.getRequestDispatcher("/recieveObject.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
