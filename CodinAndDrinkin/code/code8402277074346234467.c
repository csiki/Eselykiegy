#include <stdio.h>

int main(int argc, char* argv[])
{
	int i;
	char* szoveg;
	szoveg = argv[1];
	i=0;
	while(szoveg[i])
	{
		printf("%c", szoveg[i] ^ 'v');
		++i;
	}
	
	return 0;
}