include <stdio.h>

int main(int argc, char* argv[])
{
	int i = 0;
	
	char* szoveg = argv[1];
	while(szoveg[i])
	{
		printf("%c", szoveg[i] ^ 'v');
		++i;
	}
	
	return 0;
}