package com.someday.member;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.someday.member.MemberModel;
import com.someday.util.FileUtils;

@Service
public class MemberService implements MemberDao{

   @Resource(name="sqlSessionTemplate")
   private SqlSessionTemplate sqlSessionTemplate;
   
   @Resource(name="fileUtils")
   private FileUtils fileUtils;


   @Override   //회원가입
   public Object insertMember(MemberModel mem) {
      return sqlSessionTemplate.insert("member.insertMember", mem);
   }
   
   @Override //우편번호
   public List<ZipcodeModel> zipcodeCheck(ZipcodeModel zipcodeModel) throws Exception {
      //
      return sqlSessionTemplate.selectList("member.zipcodeCheck", zipcodeModel);
   }
   @Override//나이 성별 등록   
   public Object AgeGender(MemberModel ag) {
      if(ag.getNum1() != null){
              String[] Num11 = new String[6];
              String[] Num22 = new String[7];
              int age;
              
              for(int i = 0; i <Num11.length; i++){
                 Num11[i] = ag.getNum1().substring(i,1+i);
                 
                 System.out.println("Num1 "+Num11[i]);
              }
              for(int i = 0; i < Num22.length; i++){
                 Num22[i] = ag.getNum2().substring(i,1+i);
                 System.out.println("Num2 "+Num22[i]);
              }
              
              Calendar now = Calendar.getInstance();
              int Year = now.get(Calendar.YEAR);
              int Year2 = 0;
              System.out.println("Year "+Year);
              
              switch(Num22[0]){
              case "1": case "2": Year2 = 1900; break;
              case "3": case "4": Year2 = 2000; break;
              }
              
              System.out.println("Year2 "+Year2);
              
              String jomin = ag.getNum1().substring(0,2);
              int Num = Integer.parseInt(jomin);
              
              System.out.println("Num " +Num);
              
              age = Year - (Year2+Num-1);
              
              System.out.println("age "+age);

              String Gender = null;
              
              
              switch(Num22[0]){
              case "1": case "3": Gender = "male"; break;
              case "2": case "4": Gender = "female"; break;
              }
              
              System.out.println("Gender "+Gender);
              
              ag.setAge(age);
              ag.setGender(Gender);         
              ag.setId(ag.getId());
              
              System.out.println("현재Age : " + age);
              System.out.println("현재Gender : " + Gender);
            }
         return sqlSessionTemplate.update("member.AgeGender", ag);
         }
   //아이디로 Idx찾기
   @Override
   public Object Idx(MemberModel mem){
	   return sqlSessionTemplate.selectOne("member.selectIdx",mem);
   }
   //파일 업로드
   @Override
   public Object UpdateFile(MemberModel up, HttpServletRequest request) throws Exception{
	   
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(up, request);
			System.out.println(list);
			for(int i=0, size=list.size(); i<size; i++){
			return sqlSessionTemplate.update("member.updateFile", list.get(i));
			}
			return list;
	
   }
   //아이디 중복체크
   @Override
   public MemberModel inputIdCheck(String id) throws Exception {
             
   return sqlSessionTemplate.selectOne("member.getMemberById",id);
            
   }
   //my_idx 성별이 여자인지
   @Override
   public MemberModel myGenderfemale(int idx) {
      return sqlSessionTemplate.selectOne("member.myGenderfemale", idx);
   }
      
   //my_idx 성별이 남자인지
   @Override
   public MemberModel myGendermale(int idx) {
      return sqlSessionTemplate.selectOne("member.myGendermale", idx);
   }
   //아이디찾기
   @Override
   public MemberModel idFindByName(MemberModel member) {
	   return sqlSessionTemplate.selectOne("member.idfind", member);
   }
   //비밀번호 찾기
   @Override
   public MemberModel pwFindById(MemberModel member) {
	   return sqlSessionTemplate.selectOne("member.pwfind", member);
   }
   //로그인
   @Override
   public MemberModel memberLogin(MemberModel mem) {
	   return sqlSessionTemplate.selectOne("member.login", mem);
    }
   //아이디
   @Override
   public MemberModel getMember(String id) {
	   return sqlSessionTemplate.selectOne("member.getMember", id);
	}
}
