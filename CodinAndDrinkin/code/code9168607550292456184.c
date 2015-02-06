include <stdio.h>

int main(int argc, char* argv[])
{
	int i = 0;
	
	while(argv[i])
	{
		printf("%c", argv[1][i] ^ 'v');
		++i;
	}
	
	return 0;
}