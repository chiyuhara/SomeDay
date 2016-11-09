package com.someday.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.someday.member.MemberModel;
import com.someday.notice.NoticeModel;

@Component("fileUtils")	//Component 어노테이션을 이용하여 이 객체의 관리를 스프링이 담당하도록한다
public class FileUtils {
	
	private static final String filePath = "/src/main/webapp/resources/Upload";//파일이 저장될 위치
	
	public List<Map<String,Object>> parseInsertFileInfo(int index, HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
    	Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
    	
    	MultipartFile multipartFile = null;
    	String File_orgname = null;
    	String originalFileExtension = null;
    	String File_savname = null;
    	
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();//클라이언트에서 전송된 파일 정보를 담아서 반환
        Map<String, Object> listMap = null; 
        
       /* String boardIdx = String.valueOf(map.getIdx());*/
        
        File file = new File(filePath); //저장할 폴더가없으면 폴더를 생성
        if(file.exists() == false){
        	file.mkdirs();
        }
        
        while(iterator.hasNext()){
        	multipartFile = multipartHttpServletRequest.getFile(iterator.next());
        	if(multipartFile.isEmpty() == false){
        		
        		File_orgname = multipartFile.getOriginalFilename();	//파일 원본이름
        		
        		
        		originalFileExtension = File_orgname.substring(File_orgname.lastIndexOf(".")); // 확장자를 뽑아옴
        		File_savname = "File_" + index + originalFileExtension; //File_ + idx + 확장자
        		
        		file = new File(filePath + File_savname);
        		multipartFile.transferTo(file);//지정된 경로에 파일을 생성
        		
        		listMap = new HashMap<String,Object>();
        		listMap.put("idx", index);
        		listMap.put("File_orgname", File_orgname);
        		listMap.put("File_savname", File_savname);
        		list.add(listMap);
        	}	//위에서 만든 정보를 list에 추가해주는 부분
        }
		return list;
	}
	
	public List<Map<String,Object>> parseInsertFileInfo1(NoticeModel noticeModel, HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
    	Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
    	
    	MultipartFile multipartFile = null;
    	String File_orgname = null;
    	String originalFileExtension = null;
    	String File_savname = null;
    	
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();//클라이언트에서 전송된 파일 정보를 담아서 반환
        Map<String, Object> listMap = null; 
        
        String boardIdx = String.valueOf(noticeModel.getIdx());
        
        File file = new File(filePath); //저장할 폴더가없으면 폴더를 생성
        if(file.exists() == false){
        	file.mkdirs();
        }
        
        while(iterator.hasNext()){
        	multipartFile = multipartHttpServletRequest.getFile(iterator.next());
        	if(multipartFile.isEmpty() == false){
        		
        		File_orgname = multipartFile.getOriginalFilename();	//파일 원본이름
        		
        		
        		originalFileExtension = File_orgname.substring(File_orgname.lastIndexOf(".")); // 확장자를 뽑아옴
        		File_savname = "File_" + boardIdx + originalFileExtension; //File_ + idx + 확장자
        		
        		file = new File(filePath + File_savname);
        		multipartFile.transferTo(file);//지정된 경로에 파일을 생성
        		
        		listMap = new HashMap<String,Object>();
        		listMap.put("idx", noticeModel.getIdx());
        		listMap.put("File_orgname", File_orgname);
        		listMap.put("File_savname", File_savname);
        		list.add(listMap);
        	}	//위에서 만든 정보를 list에 추가해주는 부분
        }
		return list;
	}
}
