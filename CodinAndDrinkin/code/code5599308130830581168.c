#include<stdio.h>

int main(int argnum, char* args[])
{
	int a, b;
	
	a = atoi(args[1]);
	b = atoi(args[2]);
	
	printf("%d", a-b);
	
	return 0;
}