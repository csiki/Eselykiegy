int main(int argc, char* argv[])
{
	int x = atoi(argv[1]);
	int y = atoi(argv[2]);

	int result = (double)x / ((double)y/100) * 10;
	printf("%d", result);
	return 0;
}