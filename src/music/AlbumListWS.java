package music;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SongListWS
 */
@WebServlet("/AlbumListWS")
public class AlbumListWS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumListWS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
	    AlbumManager lm = new AlbumManager();
		String title = "";
		String searchType = "";
		
		if(request.getParameter("title") != null && request.getParameter("searchType") != null){
			title = request.getParameter("albumTitle");
			searchType = request.getParameter("searchType");
		}
		// If albumTitle and searchType are blank, than return everything.
		response.getWriter().print(lm.getAlbumList(title, searchType));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
