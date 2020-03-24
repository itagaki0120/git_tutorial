package 第二十九回課題;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class SampleServeled
 */
@WebServlet("/Kadai3")
public class Kadai3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kadai3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//文字コードの設定
				response.setContentType("text/html; charset=UTF-8");

				//ブラウザに表示されるHTMLの中身を書いていく
				PrintWriter printWriter = response.getWriter();
				Random rand = new Random();
				String[] array={"大吉","中吉","吉","凶"};
				String array2[]={"内定がもらえるかも！","面接官から好印象を持たれるかも！","面接は気合いれて！","無い内定かも..."};
				String array3[]={"エスタロンモカ","コーヒー","エナジードリンク","ブレスケア"};
				int R=rand.nextInt(4);
			    printWriter.println("<html>");
			    printWriter.println("<head>");
			    printWriter.println("<title>Kadai3</title>");
			    printWriter.println("</head>");
			    printWriter.println("<body>");
			    printWriter.println("<h1>今日の運勢は");
			    printWriter.println(array[R]);
			    printWriter.println("<br>");
			    printWriter.println(array2[R]);
			    printWriter.println("<br>");
			    printWriter.println("ラッキーアイテムは");
			    printWriter.println(array3[R]);
			    printWriter.println("</h1>");
			    printWriter.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
