CFLAGS=-O0 -Wall -ansi -pedantic -I/usr/include/glib-2.0 -I/usr/lib/glib-2.0/include 

SRCS = $(wildcard *.c)

objects:= $(patsubst %.c,%.o,$(SRCS))

arv : $(objects)
	$(CC) $(CFLAGS) -o LI3exe $(objects)  -L/usr/lib/ -lm -lglib-2.0

cleanobj: 
	rm -f $(objects)


clean: cleanobj
	rm -f LI3exe

allclean: clean cleanobj
	rm -f *_vá* Facturacao.txt

.PHONY: cleanobj clean allclean

