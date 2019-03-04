%
% Flags de interpretação
%
:- set_prolog_flag( discontiguous_warnings, off).
:- set_prolog_flag( single_var_warnings, off).
:- set_prolog_flag( unknown, fail).

%
% Definições iniciais
%

:- op( 900, xfy , '::' ).
:- dynamic utente/4.
:- dynamic cuidadoprestado/4.
:- dynamic atomedico/4.

%
% Base de Conhecimento
%

utente(1,'sergio', 23, 'Rua das Trevas').

cuidadoprestado(1, 'Consulta Rotina', 'Hospital Privado', 'Braga').
atomedico('20-12-2016', 1, 1, 23.94).

cuidadoprestado(2, 'Cirurgia', 'Hospital dos Covoes', 'Coimbra').
atomedico('02-2-2017', 1, 2, 150.80).

utente(2, 'matias', 60, 'Rua dos Rudes').

cuidadoprestado(45, 'Cirurgia', 'Hospital da Luz', 'Lisboa').
cuidadoprestado(45, 'Consulta', 'Hospital da Luz', 'Lisboa').
cuidadoprestado(45, 'Consulta', 'Hospital Geral de Santo Antonio', 'Porto').
atomedico('10-09-2015', 2, 45, 253.50).

cuidadoprestado(78, 'Cirurgia', 'Hospital da Luz', 'Lisboa').
atomedico('10-09-2015', 2, 78, 253.50).

utente(3, 'nuno', 3560, 'Rua dos Presuntos').

cuidadoprestado(567, 'Cirurgia', 'Hospital Lusiadas', 'Porto').
atomedico('10-09-2015', 3, 567, 153.50).

cuidadoprestado(985, 'Consulta', 'Hospital Geral de Santo Antonio', 'Porto').
atomedico('10-09-2015', 3, 985, 33.54).

%
% Predicados envolvidos na
% Evolução e Regressão
%
evolucao( P ) :-
  insercao(P),
  findall(Inv,+P::Inv,LInv),
  testa(LInv).

regressao( P ) :-
  remocao(P),
  findall(Inv,-P::Inv,LInv),
  testa(LInv).

insercao( P ) :-
  assert(P).

insercao( P ) :-
  retract(P),
  !,
  fail.

remocao( P ) :-
  retract(P).

remocao(P) :-
  assert(P),
  !,
  fail.

testa([]).
testa([H | T]) :-
  H,
  testa(T).

%
% Predicados auxiliares
%
pertence(X,[X|Y]).
pertence(X,[Y|L]) :-
  pertence(X,L).

nao(Q) :-
  Q,
  !,
  fail.

nao(Q).

acumular([],0).
acumular([H | T],N):-
  acumular(T,S),
  N is S+H.

listaUtentes([],I,L):-L=I.
listaUtentes([H|T],I,L):-
  findall([H,Nome,Idade,Morada],utente(H,Nome,Idade,Morada),Y),
  append(Y,I,Z),
  listaUtentes(T,Z,L).

iterarAtosMedicosUtentes([],I,L):-L=I.
iterarAtosMedicosUtentes([H|T],I,L) :-
  findall(IDUtente,atomedico(Data,IDUtente,H,Preco),Y),
  listaUtentes(Y,[],U),
  append(U,I,Z),
  iterarAtosMedicosUtentes(T,Z,L).

iterarAtosMedicosPreco([],I,L):-L=I.
iterarAtosMedicosPreco([H|T],I,L) :-
  findall(Preco,atomedico(Data,IDUtente,H,Preco),Y),
  append(Y,I,Z),
  iterarAtosMedicosPreco(T,Z,L).

iterarAtosMedicos([],I,L):-L=I.
iterarAtosMedicos([H|T],I,L) :-
  findall([Data,IDUtente,H,Preco],atomedico(Data,IDUtente,H,Preco),Y),
  append(Y,I,Z),
  iterarAtosMedicos(T,Z,L).

iterarCuidadosEstab([],I,L):-L=I.
iterarCuidadosEstab([H|T],I,L) :-
  findall(Estab,cuidadoprestado(H,Serv,Estab,Local),Y),
  append(Y,I,Z),
  iterarCuidadosEstab(T,Z,L).

mapInstituicaoValor([],I,L):-L=I.
mapInstituicaoValor([H|T],I,L):-
  custoTotalInstituicao(H,C),
  append([[C,H]],I,Z),
  mapInstituicaoValor(T,Z,L).

numElems([],0).
numElems([H|T],N):-
  numElems(T,S),
  N is S+1.

mapInstituicaoNumAtos([],I,L):-L=I.
mapInstituicaoNumAtos([H|T],I,L):-
  findall(IDServ,cuidadoprestado(IDServ,Serv,H,Local),X),
  sort(X,Y),
  numElems(Y,C),
  append([[C,H]],I,Z),
  mapInstituicaoNumAtos(T,Z,L).

first([],L):-L=[].
first([H|T],L):-L=H.

last([H],L):-L=H.
last([H|T],L):-last(T,L).

%
% Invariantes
%

%Apenas uma ocorrência de um ID único para utente
+utente(ID,Nome,Idade,Morada) :: (
  findall(ID,utente(ID,NomeB,IdadeB,MoradaB),S),
  length(S,N),
  N == 1).

%Apenas uma ocorrência de um ID único para ato médico
+cuidadoprestado(ID,Serv,Estab,Local) :: (
  findall(ID,cuidadoprestado(ID,ServB,EstabB,LocalB),S),
  length(S,N),
  N == 1).

%Ato médico tem IDs existentes na base de Conhecimento
+atomedico(Data, IDUtente, IDServ, Preco) :: (
  findall(IDUtente,utente(IDUtente,Nome,Idade,Morada),S1),
  findall(IDServ,cuidadoprestado(IDServ,Serv,Estab,Local),S2),
  pertence(IDUtente,S1),
  pertence(IDServ,S2)).

%Não remover utentes nem cuidadoprestado
%sem remover os atos médicos em que participam
-utente(ID,Nome,Idade,Morada) :: (
  findall(ID,atomedico(Data,ID,IDServ,Preco),S),
  length(S,N),
  N==0).

-cuidadoprestado(ID,Serv,Estab,Local) :: (
  findall(ID,atomedico(Data,IDUtente,ID,Preco),S),
  length(S,N),
  N==0).


%------------------------------ Funcionalidades - - - - - - - - - - -

%
% Registar utentes, cuidados prestados e atos médicos
%

registarutente( ID,Nome,Idade,Morada ) :-
  evolucao( utente( ID,Nome,Idade,Morada ) ).

registarcuidados( ID,Serv,Estab,Local ) :-
  evolucao( cuidadoprestado( ID,Serv,Estab,Local ) ).

registaratos( Data, IDUtente, IDServ, Preco ) :-
  evolucao( atomedico( Data, IDUtente, IDServ, Preco ) ).


%
% Identificar utentes por critérios de seleção
%

listaUtentesNome(Nome,L):-
  findall([IDUtente,Nome,Idade,Morada],utente(IDUtente,Nome,Idade,Morada),L).

listaUtentesIdade(Idade,L):-
  findall([IDUtente,Nome,Idade,Morada],utente(IDUtente,Nome,Idade,Morada),L).

listaUtentesMorada(Morada,L):-
  findall([IDUtente,Nome,Idade,Morada],utente(IDUtente,Nome,Idade,Morada),L).

%
% Identificar as instituições prestadoras de cuidados de saúde
%

listaInstituicoesCuidados(L):-
  findall(Instituicao,cuidadoprestado( IDServ,Desc,Instituicao,Cidade ),X),
  sort(X,L).


%
% Identificar os cuidados prestados por instituição/cidade
%

listaCuidadosPrestadosInstituicao(Instituicao,L):-
  findall([IDServ,Desc,Instituicao,Cidade],cuidadoprestado( IDServ,Desc,Instituicao,Cidade ),L).

listaCuidadosPrestadosCidade(Cidade,L):-
  findall([IDServ,Desc,Instituicao,Cidade],cuidadoprestado( IDServ,Desc,Instituicao,Cidade ),L).

%
% Identificar os utentes de uma instituição/serviço
%

listaUtentesInstituicao(Instituicao,L):-
  findall(IDServ,cuidadoprestado( IDServ,Desc,Instituicao,Cidade ),X),
  iterarAtosMedicosUtentes(X,[],Y),
  sort(Y,L).

listaUtentesServico(IDServ,L):-
  findall(IDUtente,atomedico(Data,IDUtente,IDServ,Preco),X),
  listaUtentes(X,[],L).

%
% Identificar os atos médicos realizados, por utente/instituição/serviço
%

listaAtosMedicosUtente(IDUtente,L) :-
  findall([Data,IDUtente,IDServ,Preco],atomedico(Data,IDUtente,IDServ,Preco),L).

listaAtosMedicosInstituicao(Instituicao,L) :-
  findall(IDServ,cuidadoprestado( IDServ,Desc,Instituicao,Cidade ),X),
  iterarAtosMedicos(X,[],L).

listaAtosMedicosServico(IDServ,L) :-
  findall([Data,IDUtente,IDServ,Preco],atomedico(Data,IDUtente,IDServ,Preco),L).


%
% Determinar todas os serviços a que um utente já recorreu
%


listaServicosUtente(IDUtente,L) :-
  findall(IDServ,atomedico(Data,IDUtente,IDServ,Preco),L).

%
% Determinar todas as instituições a que um utente já recorreu
%

listaInstituicoesUtente(IDUtente,L) :-
  findall(IDServ,atomedico(Data,IDUtente,IDServ,Preco),X),
  iterarCuidadosEstab(X,[],Y),
  sort(Y,L).

%
% Calcular o custo total dos atos médicos por utente/serviço/instituição/data
%

custoTotalUtente( IDUtente,N ) :-
  findall( Custo,atomedico( Data,IDUtente,IDServico,Custo ),S ),
  acumular( S,N ).

custoTotalServico( IDServico,N ) :-
  findall( Custo,atomedico( Data,IDUtente,IDServico,Custo ),S ),
  acumular( S,N ).

custoTotalInstituicao( Instituicao,N ) :-
  findall( IDServico,cuidadoprestado( IDServico,Desc,Instituicao,Cidade ),X ),
  sort(X,P),
  iterarAtosMedicosPreco(P,[],S),
  acumular( S,N ).

custoTotalData( Data,N ) :-
  findall( Custo,atomedico( Data,IDUtente,IDServico,Custo ),S ),
  acumular( S,N ).


%
% Remover utentes, cuidados prestados e atos médicos
%

removerutente( ID,Nome,Idade,Morada ) :-
  regressao( utente( ID,Nome,Idade,Morada ) ).

removercuidados( ID,Serv,Estab,Local ) :-
  regressao( cuidadoprestado( ID,Serv,Estab,Local ) ).

removeratos( Data, IDUtente, IDServ, Preco ) :-
  regressao( atomedico( Data, IDUtente, IDServ, Preco ) ).


%------------------------------ Funcionalidades Extra- - - - - - - - - - -

%
% Instituicao que gerou maior/menor valor monetario total
%

instituicaoMaiorValor(L):-
  findall(Instituicao,cuidadoprestado( IDServico,Desc,Instituicao,Cidade ),X),
  mapInstituicaoValor(X,[],Y),
  sort(Y,Z),
  last(Z,A),
  last(A,L).

instituicaoMenorValor(L):-
  findall(Instituicao,cuidadoprestado( IDServico,Desc,Instituicao,Cidade ),X),
  mapInstituicaoValor(X,[],Y),
  sort(Y,Z),
  first(Z,A),
  last(A,L).

  %
  % Instituicao com maior/menor numero de atos medicos realizados
  %

instituicaoMaisAtos(L):-
  findall(Instituicao,cuidadoprestado( IDServico,Desc,Instituicao,Cidade ),X),
  mapInstituicaoNumAtos(X,[],Y),
  sort(Y,Z),
  last(Z,A),
  last(A,L).

instituicaoMenosAtos(L):-
  findall(Instituicao,cuidadoprestado( IDServico,Desc,Instituicao,Cidade ),X),
  mapInstituicaoNumAtos(X,[],Y),
  sort(Y,Z),
  first(Z,A),
  last(A,L).
