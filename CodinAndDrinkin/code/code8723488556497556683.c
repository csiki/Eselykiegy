int main(int argc, char* argv[])
{
	int i,j,szoszam,x,hossz;
	
	hossz = atoi(argv[2]);
	x= atio(argv[3]);
	szoszam = 0;
	for (i=0; i<x; ++i)
	{
		for (j=0; j<x; ++j)
		{
			if (i==j || i==0 || i==x-1 || j==0 || j==x-1 || x-j-1 == i)
			{
				printf("%c", argv[1][szoszam%hossz]);
				szoszam++;
			}
		}
		printf("\n");
	}
	
	return 0;
}