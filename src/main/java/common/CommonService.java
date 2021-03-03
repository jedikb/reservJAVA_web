package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommonService {


	//파일다운로드
	public File fileDownLoad(String board_file, String board_filepath,
			HttpSession session, HttpServletResponse response) {
		//다운로드할 파일 객체를 생성
		File file = new File( "D:\\Dropbox\\TeamProject\\files"
				+ "/" + board_filepath );
		//content type 지정을 위한 파일의 마임타입
		String mime = session.getServletContext().getMimeType(board_file);
		response.setContentType(mime);

		try {
			board_file = URLEncoder.encode(board_file, "utf-8")
						.replaceAll("\\+", "%20");				//다운 받는 파일 이름에 문제가 있을 경우 처리
															//각각 상황에 맞춰 추가해 주면 된다.
			response.setHeader("content-disposition"
					, "attachment; filename=" + board_file);
			
			ServletOutputStream out = response.getOutputStream();
			FileCopyUtils.copy(new FileInputStream(file), out);
			out.flush();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return file;
	}
	
	public String fileUpload(HttpSession session, MultipartFile file, String category) {
		//서버의 물리적 위치
		String resources = "D:\\Dropbox\\TeamProject\\files";
		//String resources = session.getServletContext().getRealPath("resources");
		//   ... //study_Spring/ ... //iot/resourecs
		//   /upload/notice/2021/02/03/abc.txt
		String upload = resources + "/upload";
		String folder = upload + "/" + category + "/" 
					+ new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		File f = new File(folder);
		if(!f.exists()) f.mkdirs();  //폴더가 존재하지 않는다면.. 5개 폴더 // 폴더를 만들고
		
		String uuid = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();   //랜덤 이름을 만든다음
		
		try {
			file.transferTo(new File(folder, uuid));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return folder.substring(resources.length()+1) + "/" + uuid;
	}
	
	
	
	// 외부 API 사용 요청
	public String requestAPI(StringBuffer url) {
		String result = url.toString();
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(result).openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
									
			BufferedReader reader;
			if(conn.getResponseCode()>=200 && conn.getResponseCode()<=300) {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			} else {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
			}
			url = new StringBuffer();
			while((result = reader.readLine()) != null) {
				url.append(result);
			}
			reader.close();
			conn.disconnect();
			result = url.toString();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public String requestAPI(StringBuffer url, String property) {
		String result = url.toString();
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(result).openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Authorization", property);
									
			BufferedReader reader;
			if(conn.getResponseCode()>=200 && conn.getResponseCode()<=300) {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			} else {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
			}
			url = new StringBuffer();
			while((result = reader.readLine()) != null) {
				url.append(result);
			}
			reader.close();
			conn.disconnect();
			result = url.toString();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	
	
	
}
