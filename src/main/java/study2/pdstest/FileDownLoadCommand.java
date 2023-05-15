package study2.pdstest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class FileDownLoadCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/pdstest/");
		
		String fName = request.getParameter("file") == null ? "" : request.getParameter("file");
		
		File file = new File(realPath + fName);
		
		/* 프로토콜 형식에 맞도록 헤더에 정보 제공 */
		// mimeType : 파일형식 (텍스트파일? 바이너리파일? 등) -> 파일 전송시 자바에서 2진 바이너리 형식으로 전송처리 
		String mimeType = request.getServletContext().getMimeType(file.toString());
		if (mimeType == null) {
			response.setContentType("application/octet-stream"); // 2진 바이너리형식
		}
		
		// 사용하는 브라우저에 대한 정보 : IE에서의 인코딩 방식은 'euc-kr'로, 나머지는 'utf-8'로 전송처리
		String downLoadName = "";
		if (request.getHeader("user-agent").indexOf("MSIE") == -1) {
			// getBytes() 문자 -> 배열
			downLoadName = new String(fName.getBytes("UTF-8"), "8859_1");
		} else {
			downLoadName = new String(fName.getBytes("UTF-8"), "8859_1");
		}
		
		// 헤더 정보를 첨부하여 클라이언트에 전송 준비
		response.setHeader("Content-Disposition", "attachent;filename="+downLoadName);
		
		// Java에 의해 실제 파일을 다운/업로드 처리 (FileInputStream / FileOutputStream / ServletOutputStream)
		FileInputStream fis = new FileInputStream(file);
		ServletOutputStream sos = response.getOutputStream();
		
		// 전송할 객체를 생성한 후, 실제로 파일을 객체에 Byte단위로 담아서 처리한다.
		byte[] b = new byte[2048];
		int data = 0;
		
		while ((data = fis.read(b, 0, b.length)) != -1) {
			sos.write(b, 0, data);
		}
		sos.flush();
		// 다운로드 완료 -> 사용된 객체 모두 반납
		
		sos.close();
		fis.close();
		
	}

}
