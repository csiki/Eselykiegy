#include <stdio.h>

int main() {
	int i;
	printf("2,");
	for (i=3; i<100; ++i)
	{
		if (prime(i))
			printf(",%d",i);
	}
	
	return 0;
}

int prime(int szam)
{
	int i;
	for (i=2; i<szam; ++i)
		if (szam % i == 0)
			return 0;
	return 1;
}