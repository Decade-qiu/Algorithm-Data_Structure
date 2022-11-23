#include<stdio.h>
int main()
{
	int adjust(int t);
	int a[4]={0},n,t,count=0;
	scanf("%d",&n);
	for(t=1;count<n;t++)
	{
		if(adjust(t)==0)//不跳过 
			count++;
		else//跳过的话
		{
			int b=t%4;
			a[b]++;
		 } 
	 } 
	printf("%d\n%d\n%d\n%d",a[1],a[2],a[3],a[0]);
	return 0;
}
int adjust(int t)
{
	int a,m=0;
	if(t%7==0)//是不是7的倍数 
		m=1;
	else
	{
		while(t>=1)//是否含有数字7 
		{
			a=t%10;
			if(a==7)
			{
				m=1;
				break;
			}
			t=t/10;
		}
	 } 
	return m;
}
