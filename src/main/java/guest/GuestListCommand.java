package guest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuestListCommand implements GuestInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestDAO dao = new GuestDAO();
		
		// 1. 현재 페이지 번호 구하기
		int pag = request.getParameter("pag") == null ? 1 : Integer.parseInt(request.getParameter("pag"));
		
		// 2. 한 페이지 분량 결정
		int pageSize = request.getParameter("pageSize") == null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		
		// 3. 총 레코드 건수 구하기
		int totRecCnt = dao.getTotRecCnt();
		
		// 4. 총 페이지 건수 구하기
		int totPage = (totRecCnt % pageSize) == 0 ? (totRecCnt / pageSize) : (totRecCnt / pageSize) + 1;
		
		// 5. 현재 페이지의 시작 인덱스 번호 구하기
		int startIndexNo = (pag - 1) * pageSize;
		
		// 6. 현재 화면에 표시할 시작 번호 구하기 (삭제할 때)
		int curScrStartNo = totRecCnt - startIndexNo;
		
		
		// 블록 페이징 처리 / 블록 시작 번호 0부터 처리
		// 1. 블록 크기 결정
		int blockSize = 3;
		
		// 2. 현재 페이지의 블록 번호 구하기 (1페이지 : 0블록 / 2페이지 : 0블록 / 4 ~ 6 페이지 1블록)
		int curBlock = (pag - 1) / blockSize;
		
		// 3. 마지막 블록
		int lastBlock = (totPage - 1) / blockSize;
		
				
		// 지정된 페이지의 자료를 요청한 한 페이지의 분량만 가져옴
		ArrayList<GuestVO> vos = dao.getGuestList(startIndexNo, pageSize);
		
		request.setAttribute("vos", vos);
		request.setAttribute("pag", pag);
		request.setAttribute("totPage", totPage);
		request.setAttribute("curScrStartNo", curScrStartNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
		
	}
}
