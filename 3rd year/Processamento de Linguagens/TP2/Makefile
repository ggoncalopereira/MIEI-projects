n=trabalho2
F=`pkg-config --cflags glib-2.0`
L=`pkg-config --libs glib-2.0`

$n:$n.fl
	flex $n.fl
	cc -o $n $F lex.yy.c $L
