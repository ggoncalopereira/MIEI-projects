CFLAGS=-Wall -Wextra -ansi -pedantic -O2

jogo: interpretador.o parte1.o parte2.o parte3.o
	$(CC) $(CFLAGS) -o jogo interpretador.o parte1.o parte2.o parte3.o

interpretador.o: interpretador.c header.h
	$(CC) $(CFLAGS) -c interpretador.c	

parte1.o: parte1.c header.h
	$(CC) $(CFLAGS) -c parte1.c

parte2.o: parte2.c header.h 
	$(CC) $(CFLAGS) -c parte2.c	

parte3.o: parte3.c header.h
	$(CC) $(CFLAGS) -c parte3.c

clean:
		rm -f interpretador *.o