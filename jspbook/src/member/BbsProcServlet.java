package member;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/BbsProcServlet")
public class BbsProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BbsProcServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsDAO bDao = null;
		BbsDTO bDto = null;
		BbsMember bMem = null;
		RequestDispatcher rd = null;
		int id = 0;
		int contentId = 0;
		int contentMemId = 0;
		String password = null;
		String name = null;
		String birthday = null;
		String address = null;
		String message = null;
		String url = null;
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
//		System.out.println(action);
		//로그인 정보관리 HttpSession객체 생성 : 사용자의 상태정보를 서버에서 관리함
		HttpSession session = request.getSession();
		
		switch(action) {
		case "contentView":
			//클릭된 글의 id를 가져온다(members의 id와 다름)
			//request로 받은 parameter는 변수에 저장하여 사용하여야 한다.
//			System.out.println(request.getParameter("contentId"));
			if(request.getParameter("contentId").endsWith(";")) {
				contentId = (request.getParameter("contentId").charAt(0)) - '0';
			} else {
				contentId = Integer.parseInt(request.getParameter("contentId"));
			}
			bDao = new BbsDAO();
			bMem = bDao.detailsearch(contentId);
			request.setAttribute("selectedContent", bMem);
			rd = request.getRequestDispatcher("bbs_contents.jsp");
			rd.forward(request, response);
			break;
			
		
		case "update":		// 수정 버튼 클릭 시
			bDao = new BbsDAO();
			//해당 글의 id로 memberId확인
			contentId = Integer.parseInt(request.getParameter("contentId"));
			contentMemId = bDao.selectContentById(contentId).getMemberId();
			if (contentMemId != (Integer)session.getAttribute("memberId")) {
				message = "id = " + contentId + " 에 대한 수정 권한이 없습니다.";
				rd = request.getRequestDispatcher("alertMsg.jsp");
				request.setAttribute("message", message);
				
				//controlView에서 파라미터를 얻을 때 숫자+;로 값이 얻어져 예외 발생.
				url = "BbsProcServlet?action=contentView&contentId="+contentId;
				request.setAttribute("url",url);
				
				//오류메세지 뜨지 않음 : Attribute설정 무시하고 바로 연결
//				response.sendRedirect(url);
				
				rd.forward(request, response);
				break;
			}
			bMem = bDao.detailsearch(contentId);
			request.setAttribute("selectedContent", bMem);
			rd = request.getRequestDispatcher("bbs_con_modi.jsp");
			rd.forward(request, response);
			break;
//			mDao = new MemberDAO();
//			member = mDao.selectMemberById(id);
//			mDao.close();
//			request.setAttribute("member", member);
//			rd = request.getRequestDispatcher("update.jsp");
//	        rd.forward(request, response);
//	        break;
//	        
//		case "delete":		// 삭제 버튼 클릭 시
//			if (!request.getParameter("id").equals("")) {
//				id = Integer.parseInt(request.getParameter("id"));
//			}
//			if (id != (Integer)session.getAttribute("memberId")) {
//				message = "id = " + id + " 에 대한 삭제 권한이 없습니다.";
//				url = "loginMain.jsp";
//				request.setAttribute("message", message);
//				request.setAttribute("url", url);
//				rd = request.getRequestDispatcher("alertMsg.jsp");
//				rd.forward(request, response);
//				break;
//			}
//			mDao = new MemberDAO();
//			mDao.deleteMember(id);
//			mDao.close();
//			//response.sendRedirect("loginMain.jsp");
//			message = "id = " + id + " 이/가 삭제되었습니다.";
//			url = "loginMain.jsp";
//			request.setAttribute("message", message);
//			request.setAttribute("url", url);
//			rd = request.getRequestDispatcher("alertMsg.jsp");
//			rd.forward(request, response);
//			break;
//			
//		case "login":		// login 할 때
//			if (!request.getParameter("id").equals("")) {
//				id = Integer.parseInt(request.getParameter("id"));
//			}
//			password = request.getParameter("password");
//			
//			mDao = new MemberDAO();
//			int result = mDao.verifyIdPassword(id, password);
//			String errorMessage = null;
//			switch (result) {
//			case MemberDAO.ID_PASSWORD_MATCH:
//				break;
//			case MemberDAO.ID_DOES_NOT_EXIST:
//				errorMessage = "ID가 없음"; break;
//			case MemberDAO.PASSWORD_IS_WRONG:
//				errorMessage = "패스워드가 틀렸음"; break;
//			case MemberDAO.DATABASE_ERROR:
//				errorMessage = "DB 오류";
//			}
//			
//			if (result == MemberDAO.ID_PASSWORD_MATCH) {
//				member = mDao.selectMemberById(id);
//				session.setAttribute("memberId", id);
//				session.setAttribute("memberName", member.getName());
//				response.sendRedirect("loginMain.jsp");
//			} else {
//				String uri = "login.jsp?error=" + URLEncoder.encode(errorMessage, "UTF-8");
//						//org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(errorMessage), request.getCharacterEncoding());
//				response.sendRedirect(uri); 
//			}
//			mDao.close();
//			break;
//		
//		case "logout":			// 로그아웃할 때
//			session.removeAttribute("memberId");
//			session.removeAttribute("memberName");
//			response.sendRedirect("login.jsp");
//			break;
//			
//		case "register":		// 회원 등록할 때
//			password = request.getParameter("password");
//			name = request.getParameter("name");
//			birthday = request.getParameter("birthday");
//			address = request.getParameter("address");
//			member = new MemberDTO(password, name, birthday, address);
//			System.out.println(member.toString());
//			
//			mDao = new MemberDAO();
//			mDao.insertMember(member);
//			member = mDao.recentId();
//			session.setAttribute("memberId", member.getId());
//			session.setAttribute("memberName", name);
//			
//			message = "귀하의 아이디는 " + member.getId() + " 입니다.";
//			url = "loginMain.jsp";
//			request.setAttribute("message", message);
//			request.setAttribute("url", url);
//			rd = request.getRequestDispatcher("alertMsg.jsp");
//			rd.forward(request, response);
//			mDao.close();
//			break;
//			
		case "execute":			// 게시글 수정후 저장
			//memberId검사 재실행 필요?
			//파라미터 값 얻은다음 : memberId는 세션에서 획득 (DAO의 update method실행 시 필요)
			//DTO에 저장
			//DAO의 update실행, DB에 업로드
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			name = request.getParameter("name");
			birthday = request.getParameter("birthday");
			address = request.getParameter("address");
			
			member = new MemberDTO(id, "*", name, birthday, address);
			System.out.println(member.toString());
			
			mDao = new MemberDAO();
			mDao.updateMember(member);
			mDao.close();
			
			message = "다음과 같이 수정하였습니다.\\n" + member.toString();
			request.setAttribute("message", message);
			request.setAttribute("url", "loginMain.jsp");
			rd = request.getRequestDispatcher("alertMsg.jsp");
	        rd.forward(request, response);
			//response.sendRedirect("loginMain.jsp");
			break;
			
		default:
		}
	}

}
