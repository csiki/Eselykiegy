#include <stdio.h>

int main(int argc, char* argv[])
{
    int ks[20], i, j, sorszam;
    int csalok[10];

    for (i=0; i<10; ++i)
        csalok[i] = 0;

    for (i=0; i<argc; ++i)
        ks[i] = atoi(argv[i+1]);

    for (i=0; i<argc; ++i) {
        for (j=i+1; j<argc; ++j) {
            if (ks[i] == ks[j] && i/2 != j/2) {
                csalok[i/2] = 1;
                csalok[j/2] = 1;
            }
        }
    }

    for (i=0; i<10; ++i)
        if (csalok[i])
            printf("%d ", i);

	return 0;
}
