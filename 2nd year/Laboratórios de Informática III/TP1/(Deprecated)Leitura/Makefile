CFLAGS=-O0 -Wall -ansi -pedantic

SRCS = $(wildcard *.c)

objects:= $(patsubst %.c,%.o,$(SRCS))

all : $(objects)
	$(CC) $(CFLAGS) -o LI3exe $(objects)


cleanobj: 
	rm -f $(objects)


clean: cleanobj
	rm -f LI3exe

allclean: clean cleanobj
	rm -f *_va* Facturacao.txt

.PHONY: cleanobj clean allclean

