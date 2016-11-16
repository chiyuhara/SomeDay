package com.someday.today;

public interface TodayDao {

	// ���� ȸ���� ���� ���� ���� ã��
	TodayModel female_today(int idx);

	// ���� ȸ���� ���� ���� ���� ã��
	TodayModel male_today(int idx);

	// ���� ������ �����߿� ����,�Ǵ� ���ڰ� �ߺ��Ǵ� ȸ���� �ִ��� äũ
	TodayModel meeting_femalecheck(int idx);

	// ���� ������ �����߿� ����,�Ǵ� ���ڰ� �ߺ��Ǵ� ȸ���� �ִ��� äũ
	TodayModel meeting_malecheck(int idx);

	// ���� ����
	int create_meeting(TodayModel meeting);

	// ���ڰ� ���ƿ� ����
	int female_like(int idx);

	// ���ڰ� ���ƿ� ����
	int male_like(int idx);

	// ���ڰ� ���ڿ��� ������ ����
	int female_score(TodayModel todayModel);

	// ���ڰ� ���ڿ��� ������ ����
	int male_score(TodayModel todayModel);
	
	// ���ڰ� ���ڿ��� �޽����� ����
	int female_message(TodayModel todayModel);
	
	// ���ڰ� ���ڿ��� �޽����� ����
	int male_message(TodayModel todayModel);
}
