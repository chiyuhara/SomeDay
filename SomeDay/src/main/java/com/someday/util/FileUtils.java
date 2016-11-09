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

@Component("fileUtils")	//Component ������̼��� �̿��Ͽ� �� ��ü�� ������ �������� ����ϵ����Ѵ�
public class FileUtils {
	
	private static final String filePath = "/src/main/webapp/resources/Upload";//������ ����� ��ġ
	
	public List<Map<String,Object>> parseInsertFileInfo(int index, HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
    	Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
    	
    	MultipartFile multipartFile = null;
    	String File_orgname = null;
    	String originalFileExtension = null;
    	String File_savname = null;
    	
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();//Ŭ���̾�Ʈ���� ���۵� ���� ������ ��Ƽ� ��ȯ
        Map<String, Object> listMap = null; 
        
       /* String boardIdx = String.valueOf(map.getIdx());*/
        
        File file = new File(filePath); //������ ������������ ������ ����
        if(file.exists() == false){
        	file.mkdirs();
        }
        
        while(iterator.hasNext()){
        	multipartFile = multipartHttpServletRequest.getFile(iterator.next());
        	if(multipartFile.isEmpty() == false){
        		
        		File_orgname = multipartFile.getOriginalFilename();	//���� �����̸�
        		
        		
        		originalFileExtension = File_orgname.substring(File_orgname.lastIndexOf(".")); // Ȯ���ڸ� �̾ƿ�
        		File_savname = "File_" + index + originalFileExtension; //File_ + idx + Ȯ����
        		
        		file = new File(filePath + File_savname);
        		multipartFile.transferTo(file);//������ ��ο� ������ ����
        		
        		listMap = new HashMap<String,Object>();
        		listMap.put("idx", index);
        		listMap.put("File_orgname", File_orgname);
        		listMap.put("File_savname", File_savname);
        		list.add(listMap);
        	}	//������ ���� ������ list�� �߰����ִ� �κ�
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
    	
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();//Ŭ���̾�Ʈ���� ���۵� ���� ������ ��Ƽ� ��ȯ
        Map<String, Object> listMap = null; 
        
        String boardIdx = String.valueOf(noticeModel.getIdx());
        
        File file = new File(filePath); //������ ������������ ������ ����
        if(file.exists() == false){
        	file.mkdirs();
        }
        
        while(iterator.hasNext()){
        	multipartFile = multipartHttpServletRequest.getFile(iterator.next());
        	if(multipartFile.isEmpty() == false){
        		
        		File_orgname = multipartFile.getOriginalFilename();	//���� �����̸�
        		
        		
        		originalFileExtension = File_orgname.substring(File_orgname.lastIndexOf(".")); // Ȯ���ڸ� �̾ƿ�
        		File_savname = "File_" + boardIdx + originalFileExtension; //File_ + idx + Ȯ����
        		
        		file = new File(filePath + File_savname);
        		multipartFile.transferTo(file);//������ ��ο� ������ ����
        		
        		listMap = new HashMap<String,Object>();
        		listMap.put("idx", noticeModel.getIdx());
        		listMap.put("File_orgname", File_orgname);
        		listMap.put("File_savname", File_savname);
        		list.add(listMap);
        	}	//������ ���� ������ list�� �߰����ִ� �κ�
        }
		return list;
	}
}
