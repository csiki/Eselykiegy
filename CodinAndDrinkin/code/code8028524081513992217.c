#include<stdio.h>

int main(int argnum, char* args[])
{
	int i;

	for (i=0; i<100; i+=2)
		printf("%d\n", i);
	printf("100");
	return 0;
}