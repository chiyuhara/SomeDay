
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>



<div id="container">
	<div class="contents1">
		
		<form action="memberPwFind" method="post" name="memberPwFindForm" onsubmit="return memberFindChk()" >
			<fieldset>
			 <h3><center><img src="/pet/resources/images/SkinImg/h3_find_pw.GIF" alt="비번 찾기"/></center></h3>
				
				<div class="findId">
					<table>
						
						<colgroup>
							<col style="width:120px;" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">이름</th>
								<td>
									<input type="text" class="txt w220" name="name" />
								</td>
							</tr>
							<tr>
								<th scope="row">아이디</th>
								<td>
									<input type="text" class="txt w220" name="id" />
								</td>
							</tr>
							<tr>
								<th scope="row">주민번호</th>
								<td>
									<input type="text" class="txt w220" name="num1" /> - <input type="text" class="txt w220" name="num2" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btn_type_04">
					
					<span class="btn btnC_04 btnF_02">
						<input type="submit" value="찾기" />
					</span>
					<a href="javascript:this.close();">닫기</a>		
				</div>
			</fieldset>
		</form>
	</div>
</div>