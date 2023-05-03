package guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuestDeleteCommand implements GuestInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx") == null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		GuestDAO dao = new GuestDAO();
		
		int res = dao.setGuestDelete(idx);
		
		if (res == 1) {
			request.setAttribute("msg", "삭제 완료");
		} else {
			request.setAttribute("msg", "삭제 실패");
		}
		request.setAttribute("url", request.getContextPath() + "/GuestList.gu");
	}

}
