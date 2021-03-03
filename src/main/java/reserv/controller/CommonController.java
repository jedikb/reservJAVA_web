package reserv.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class CommonController {
	
	//summernote 파일 업로드
	@PostMapping(value="/uploadSummernoteImageFile")
//	@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap<String, Object> uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
    														@RequestParam("ca") String ca) {
		HashMap<String, Object> jsonObject = new HashMap<String, Object>();
//		JSONObject jsonObject = new JSONObject();
		String fileRoot="";
		if(ca.contains("no")) {
			fileRoot = "D:\\Dropbox\\TeamProject\\img\\summernote_img\\notice\\";	//저장될 파일 경로
		} else if (ca.contains("tip")){
			fileRoot = "D:\\Dropbox\\TeamProject\\img\\summernote_img\\tip\\";	//저장될 파일 경로
		} else if (ca.contains("qu")){
			fileRoot = "D:\\Dropbox\\TeamProject\\img\\summernote_img\\Question\\";	//저장될 파일 경로
		}
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        // 랜덤 UUID+확장자로 저장될 savedFileName
        String savedFileName = UUID.randomUUID() + extension;	
        
        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            if(ca.contains("no")) {
            	jsonObject.put("url", "summernoteNotice/"+savedFileName);
            } else if (ca.contains("tip")) {
            	jsonObject.put("url", "summernoteTip/"+savedFileName);
            } else if (ca.contains("qu")) {
            	jsonObject.put("url", "summernoteQuestion/"+savedFileName);
            }
            jsonObject.put("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	// 실패시 저장된 파일 삭제
            jsonObject.put("responseCode", "error");
            e.printStackTrace();
        }
        return jsonObject;
    }
}
