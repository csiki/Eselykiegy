#include <iostream>

int main(int argc, char* argv[])
{
	int i,j,rang,szam, ossz;
	ossz = 0;
	for (i=0; i<10; ++i)
	{
		if (kiszed(argv[i]) != 0) {
		rang = 0;
		szam = atoi(argv[i+1]);
		for (j=i+1; j<10; ++j)
		{
			if (kiszed(argv[i]) == kiszed(argv[j])) {
				rang++;
				argv[j] = "0";
			}
		}
		
		if (rang == 0 && kiszed(argv[i]) == 1)
			ossz++;
		else
			ossz += rang;
		}
	}
	printf("%d", ossz);
}

int kiszed(char* szamc)
{
	return atoi(szamc+1);
}