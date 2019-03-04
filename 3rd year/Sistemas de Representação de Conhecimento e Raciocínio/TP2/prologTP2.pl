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
:- dynamic excecao/1.
:- dynamic nulo/1.

%
% Base de Conhecimento
%

utente(1,'sergio', 23, 'Rua das Trevas').

-cuidadoprestado(1,'Vacinação Gripe','Clínica de Santa Tecla', 'Braga').

excecao(cuidadoprestado(3,'Vacinação Tétano','USF Gualtar','Braga')).

cuidadoprestado(3,'Nascimento',desconhecido1,'Kiev').
excecao(cuidadoprestado(3,'Nascimento',P,'Kiev')) :-
  cuidadoprestado(3,'Nascimento',desconhecido1,'Kiev').
nulo(desconhecido1).


excecao(atomedico('12-04-1996',3,3,878.94)).
excecao(atomedico('13-04-1996',3,3,878.94)).
excecao(atomedico('14-04-1996',3,3,878.94)).
excecao(atomedico('15-04-1996',3,3,878.94)).
excecao(atomedico('16-04-1996',3,3,878.94)).
excecao(atomedico('17-04-1996',3,3,878.94)).
excecao(atomedico('18-04-1996',3,3,878.94)).
excecao(atomedico('19-04-1996',3,3,878.94)).

cuidadoprestado(1, 'Consulta Rotina', 'Hospital Privado', 'Braga').
atomedico('20-12-2016', 1, 1, 23.94).

excecao(atomedico('20-3-2017',2,63,238.95)).
excecao(atomedico('24-3-2017',2,63,238.95)).

cuidadoprestado(2, 'Cirurgia', 'Hospital dos Covoes', 'Coimbra').
atomedico('02-2-2017', 1, 2, 150.80).

utente(2, 'matias', 60, 'Rua dos Rudes').

cuidadoprestado(45, 'Cirurgia', 'Hospital da Luz', 'Lisboa').
cuidadoprestado(45, 'Consulta', 'Hospital da Luz', 'Lisboa').
cuidadoprestado(45, 'Consulta', 'Hospital Geral de Santo António', 'Porto').
atomedico('10-09-2015', 2, 45, 253.50).


cuidadoprestado(63,'Consulta Oftalmologia','Clínica de Santa Tecla','Braga').
atomedico(desconhecido2,3,87,23.95).
excecao(atomedico(P,3,87,23.95)) :-
  atomedico(desconhecido2,3,87,23.95).



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

% Evolução de exceções de utente



%Inserir um valor de utente impreciso requer que não haja conhecimento negativo igual
%ao que se quer inserir, não pode já existir este valor impreciso e não pode
%haver uma interdição a mexer com este ID. Se houver um valor incerto, a imprecisão
%remove-o.
evolucaoImpreciso(utente(ID,Nome,Idade,Morada)) :-
  nao(-utente(ID,Nome,Idade,Morada)),
  nao(excecao(utente(ID,Nome,Idade,Morada))),
  testaNulo(utente(ID,Nome,Idade,Morada)).

testaNulo(utente(ID,Nome,Idade,Morada)) :-
  utente(ID,NomeB,IdadeB,MoradaB),
  excecao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(nulo(NomeB)),
  nao(nulo(IdadeB),
  nao(nulo(MoradaB))),
  clause(excecao(utente(ID,NomeB,IdadeB,MoradaB)),B,R),
  retract(utente(ID,NomeB,IdadeB,MoradaB)),
  retract((excecao(utente(ID,NomeB,IdadeB,MoradaB)):-B)),
  assert(excecao(utente(ID,Nome,Idade,Morada))).

testaNulo(utente(ID,Nome,Idade,Morada)) :-
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  assert(excecao(utente(ID,Nome,Idade,Morada))).


%Inserir um valor de utente interdito necessita que não haja incerteza nem imprecisão
%nem um utente já existente.
evolucaoNulo(utente(ID,Nulo,Idade,Morada),Nulo) :-
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(excecao(utente(ID,NomeC,IdadeC,MoradaC))),
  assert((excecao(utente(ID,NomeD,IdadeD,MoradaD)):- utente(ID,Nulo,Idade,Morada))),
  assert(utente(ID,Nulo,Idade,Morada)),
  assert(nulo(Nulo)).

evolucaoNulo(utente(ID,Nome,Nulo,Morada),Nulo) :-
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(excecao(utente(ID,NomeC,IdadeC,MoradaC))),
  assert((excecao(utente(ID,NomeD,IdadeD,MoradaD)):- utente(ID,Nome,Nulo,Morada))),
  assert(utente(ID,Nome,Nulo,Morada)),
  assert(nulo(Nulo)).

evolucaoNulo(utente(ID,Nome,Idade,Nulo),Nulo) :-
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(excecao(utente(ID,NomeC,IdadeC,MoradaC))),
  assert((excecao(utente(ID,NomeD,IdadeD,MoradaD)):- utente(ID,Nome,Idade,Nulo))),
  assert(utente(ID,Nome,Idade,Nulo)),
  assert(nulo(Nulo)).

evolucaoNulo(utente(ID,Nulo,Nulo,Morada),Nulo) :-
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(excecao(utente(ID,NomeC,IdadeC,MoradaC))),
  assert((excecao(utente(ID,NomeD,IdadeD,MoradaD)):- utente(ID,Nulo,Nulo,Morada))),
  assert(utente(ID,Nulo,Nulo,Morada)),
  assert(nulo(Nulo)).

evolucaoNulo(utente(ID,Nulo,Idade,Nulo),Nulo) :-
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(excecao(utente(ID,NomeC,IdadeC,MoradaC))),
  assert((excecao(utente(ID,NomeD,IdadeD,MoradaD)):- utente(ID,Nulo,Idade,Nulo))),
  assert(utente(ID,Nulo,Idade,Nulo)),
  assert(nulo(Nulo)).

evolucaoNulo(utente(ID,Nome,Nulo,Nulo),Nulo) :-
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(excecao(utente(ID,NomeC,IdadeC,MoradaC))),
  assert((excecao(utente(ID,NomeD,IdadeD,MoradaD)):- utente(ID,Nome,Nulo,Nulo))),
  assert(utente(ID,Nome,Nulo,Nulo)),
  assert(nulo(Nulo)).


evolucaoNulo(utente(ID,Nulo,Nulo,Nulo),Nulo) :-
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(excecao(utente(ID,NomeC,IdadeC,MoradaC))),
  assert((excecao(utente(ID,NomeD,IdadeD,MoradaD)):- utente(ID,Nulo,Nulo,Nulo))),
  assert(utente(ID,Nulo,Nulo,Nulo)),
  assert(nulo(Nulo)).




%Evolução de incerto para utente.
%Não pode haver já um utente. Não pode haver já imprecisão para esse ID.
%Não pode haver nulos para esse ID, não pode existir um predicado incerto igual
%nem um predicado menos incerto do que o que se quer inserir.
evolucaoIncerto(utente(ID,Nulo,Idade,Morada),Nulo) :-
  nao(excecao(ID,NomeC,IdadeC,MoradaC)),
  nao(excecao(utente(ID,NomeC,IdadeC,MoradaC))),
  assert((excecao(utente(ID,NomeB,IdadeB,MoradaB)):- utente(ID,Nulo,Idade,Morada))),
  assert(utente(ID,Nulo,Idade,Morada)).

evolucaoIncerto(utente(ID,Nulo,Idade,Morada),Nulo) :-
  utente(ID,NomeB,IdadeB,MoradaB),
  excecao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(nulo(NomeB)),
  nao(nulo(IdadeB)),
  nao(nulo(MoradaB)),
  nao(excecao(utente(ID,NomeB,Idade,Morada))),
  clause(excecao(utente(ID,NomeB,IdadeB,MoradaB)),B,R),
  retract((excecao(utente(ID,NomeB,IdadeB,MoradaB)):-B)),
  retract(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeB,IdadeB,MoradaB)):- utente(ID,Nulo,Idade,Morada))),
  assert(utente(ID,Nulo,Idade,Morada)).

%Não há excecoes nem utentes, insere
evolucaoIncerto(utente(ID,Nome,Nulo,Morada),Nulo) :-
  nao(excecao(ID,NomeC,IdadeC,MoradaC)),
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeB,IdadeB,MoradaB)):- utente(ID,Nome,Nulo,Morada))),
  assert(utente(ID,Nome,Nulo,Morada)).

%Há utente e não interdita e é mais incerto, insere.
evolucaoIncerto(utente(ID,Nome,Nulo,Morada),Nulo) :-
  utente(ID,NomeB,IdadeB,MoradaB),
  excecao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(nulo(NomeB)),
  nao(nulo(IdadeB)),
  nao(nulo(MoradaB)),
  nao(excecao(ID,Nome,IdadeB,Morada)),
  clause(excecao(utente(ID,NomeB,IdadeB,MoradaB)),B,R),
  retract((excecao(utente(ID,NomeB,IdadeB,MoradaB)):-B)),
  retract(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeB,IdadeB,MoradaB)):- utente(ID,Nome,Nulo,Morada))),
  assert(utente(ID,Nome,Nulo,Morada)).

evolucaoIncerto(utente(ID,Nome,Idade,Nulo),Nulo) :-
  nao(excecao(ID,NomeC,IdadeC,MoradaC)),
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeB,IdadeB,MoradaB)):- utente(ID,Nome,Idade,Nulo))),
  assert(utente(ID,Nome,Idade,Nulo)).

evolucaoIncerto(utente(ID,Nome,Idade,Nulo),Nulo) :-
  utente(ID,NomeB,IdadeB,MoradaB),
  excecao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(nulo(NomeB)),
  nao(nulo(IdadeB)),
  nao(nulo(MoradaB)),
  nao(excecao(ID,Nome,Idade,MoradaB)),
  clause(excecao(utente(ID,NomeB,IdadeB,MoradaB)),B,R),
  retract((excecao(utente(ID,NomeB,IdadeB,MoradaB)):-B)),
  retract(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeB,IdadeB,MoradaB)):- utente(ID,Nome,Idade,Nulo))),
  assert(utente(ID,Nome,Idade,Nulo)).



evolucaoIncerto(utente(ID,Nulo,Idade,Nulo),Nulo) :-
  nao(excecao(ID,NomeC,IdadeC,MoradaC)),
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeB,IdadeB,MoradaB)):- utente(ID,Nulo,Idade,Nulo))),
  assert(utente(ID,Nulo,Idade,Nulo)).

evolucaoIncerto(utente(ID,Nulo,Idade,Nulo),Nulo) :-
  utente(ID,NomeB,IdadeB,MoradaB),
  excecao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(nulo(NomeB)),
  nao(nulo(IdadeB)),
  nao(nulo(MoradaB)),
  NomeB==IdadeB,
  IdadeB==MoradaB,
  clause(excecao(utente(ID,NomeB,IdadeB,MoradaB)),B,R),
  retract((excecao(utente(ID,NomeB,IdadeB,MoradaB)):-B)),
  retract(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeC,IdadeC,MoradaC)):- utente(ID,Nulo,Idade,Nulo))),
  assert(utente(ID,Nulo,Idade,Nulo)).



evolucaoIncerto(utente(ID,Nulo,Nulo,Morada),Nulo) :-
  nao(excecao(ID,NomeC,IdadeC,MoradaC)),
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeB,IdadeB,MoradaB)):- utente(ID,Nulo,Nulo,Morada))),
  assert(utente(ID,Nulo,Nulo,Morada)).

evolucaoIncerto(utente(ID,Nulo,Nulo,Morada),Nulo) :-
  utente(ID,NomeB,IdadeB,MoradaB),
  excecao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(nulo(NomeB)),
  nao(nulo(IdadeB)),
  nao(nulo(MoradaB)),
  NomeB==IdadeB,
  IdadeB==MoradaB,
  clause(excecao(utente(ID,NomeB,IdadeB,MoradaB)),B,R),
  retract((excecao(utente(ID,NomeB,IdadeB,MoradaB)):-B)),
  retract(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeC,IdadeC,MoradaC)):- utente(ID,Nulo,Nulo,Morada))),
  assert(utente(ID,Nulo,Nulo,Morada)).


evolucaoIncerto(utente(ID,Nome,Nulo,Nulo),Nulo) :-
  nao(excecao(ID,NomeC,IdadeC,MoradaC)),
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeB,IdadeB,MoradaB)):- utente(ID,Nome,Nulo,Nulo))),
  assert(utente(ID,Nome,Nulo,Nulo)).

evolucaoIncerto(utente(ID,Nome,Nulo,Nulo),Nulo) :-
  utente(ID,NomeB,IdadeB,MoradaB),
  excecao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(nulo(NomeB)),
  nao(nulo(IdadeB)),
  nao(nulo(MoradaB)),
  NomeB==IdadeB,
  IdadeB==MoradaB,
  clause(excecao(utente(ID,NomeB,IdadeB,MoradaB)),B,R),
  retract((excecao(utente(ID,NomeB,IdadeB,MoradaB)):-B)),
  retract(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeC,IdadeC,MoradaC)):- utente(ID,Nome,Nulo,Nulo))),
  assert(utente(ID,Nome,Nulo,Nulo)).

evolucaoIncerto(utente(ID,Nulo,Nulo,Nulo),Nulo) :-
  nao(excecao(ID,NomeC,IdadeC,MoradaC)),
  nao(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeB,IdadeB,MoradaB)):- utente(ID,Nulo,Nulo,Nulo))),
  assert(utente(ID,Nulo,Nulo,Nulo)).

evolucaoIncerto(utente(ID,Nulo,Nulo,Nulo),Nulo) :-
  utente(ID,NomeB,IdadeB,MoradaB),
  excecao(utente(ID,NomeB,IdadeB,MoradaB)),
  nao(nulo(NomeB)),
  nao(nulo(IdadeB)),
  nao(nulo(MoradaB)),
  nao(utente(ID,NomeB,NomeB,NomeB)),
  clause(excecao(utente(ID,NomeB,IdadeB,MoradaB)),B,R),
  retract((excecao(utente(ID,NomeB,IdadeB,MoradaB)):-B)),
  retract(utente(ID,NomeB,IdadeB,MoradaB)),
  assert((excecao(utente(ID,NomeC,IdadeC,MoradaC)):- utente(ID,Nulo,Nulo,Nulo))),
  assert(utente(ID,Nulo,Nulo,Nulo)).

%Regressão de exceções de utente
regressaoImpreciso(utente(ID,Nome,Idade,Morada)) :-
  excecao(utente(ID,Nome,Idade,Morada)),
  retract(excecao(utente(ID,Nome,Idade,Morada))).

regressaoInterdito(utente(ID,Nome,Idade,Morada)) :-
  utente(ID,Nome,Idade,Morada),
  excecao(utente(ID,Nome,Idade,Morada)),
  regressaonulo(utente(ID,Nome,Idade,Morada)),
  clause(excecao(utente(ID,Nome,Idade,Morada)),B,R),
  retract((excecao(utente(ID,Nome,Idade,Morada)):-B)),
  retract(utente(ID,Nome,Idade,Morada)).

regressaonulo(utente(ID,Nome,Idade,Morada)) :-
  nulo(Nome).

regressaonulo(utente(ID,Nome,Idade,Morada)) :-
  nulo(Idade).

regressaonulo(utente(ID,Nome,Idade,Morada)) :-
  nulo(Morada).

regressaoIncerteza(utente(ID,Nome,Idade,Morada)) :-
  utente(ID,Nome,Idade,Morada),
  excecao(utente(ID,Nome,Idade,Morada)),
  nao(nulo(Nome)),
  nao(nulo(Idade)),
  nao(nulo(Morada)),
  clause(excecao(utente(ID,Nome,Idade,Morada)),B,R),
  retract((excecao(utente(ID,Nome,Idade,Morada)):-B)),
  retract(utente(ID,Nome,Idade,Morada)).


%Evolução de exceções de cuidados prestados

%Impreciso no cuidado prestado implica inserção desde que não haja negação,
%repetição, interdição ou conhecimento igual mas certo.
%Se houver uma incerteza é irremovível porque há imensos cuidados prestados.
%Can't be sure what you're unsure of.
evolucaoImpreciso(cuidadoprestado(IDServ,Serv,Estab,Local)) :-
  nao(-cuidadoprestado(IDServ,Serv,Estab,Local)),
  nao(cuidadoprestado(IDServ,Serv,Estab,Local)),
  nao(excecao(cuidadoprestado(IDServ,Serv,Estab,Local))),
  testaNulo(cuidadoprestado(IDServ,Serv,Estab,Local)).

testaNulo(cuidadoprestado(IDServ,Serv,Estab,Local)) :-
  cuidadoprestado(IDServ,ServB,EstabB,LocalB),
  excecao(cuidadoprestado(IDServ,ServB,EstabB,LocalB)),
  nao(nulo(ServB)),
  nao(nulo(EstabB),
  nao(nulo(LocalB))),
  assert(excecao(cuidadoprestado(IDServ,Serv,Estab,Local))).

testaNulo(cuidadoprestado(IDServ,Serv,Estab,Local)) :-
  nao(cuidadoprestado(IDServ,ServB,EstabB,LocalB)),
  assert(excecao(cuidadoprestado(IDServ,Serv,Estab,Local))).


%Interdição no cuidado prestado implica não haver mais possibilidade de conhecer
%mais cuidados prestados para um ato médico a partir do ponto em que é inserido.
%Só não pode haver já uma interdição.
evolucaoNulo(cuidadoprestado(IDServ,Nome,Estab,Local)) :-
  findall(cuidadoprestado(IDServ,ServB,EstabB,LocalB),
  cuidadoprestado(IDServ,ServB,EstabB,LocalB),LCP),
  testaNulo(LCP),
  assert((excecao(cuidadoprestado(IDServC,ServC,EstabC,LocalC)):-
                  cuidadoprestado(IDServ,Nome,Estab,Local))),
  assert(cuidadoprestado(IDServ,Nulo,Estab,Local)),
  assert(nulo(Nulo)).

testaNulo([cuidadoprestado(IDServ,Serv,Estab,Local)|T]) :-
  excecao(cuidadoprestado(IDServ,Serv,Estab,Local)),
  nao(nulo(Serv)),
  nao(nulo(Estab)),
  nao(nulo(Local)),
  testaNulo(T).

testaNulo([cuidadoprestado(IDServ,Serv,Estab,Local)|T]) :-
  nao(excecao(cuidadoprestado(IDServ,Serv,Estab,Local))).


testaNulo([]).


%Incerteza só pode ser inserida se não houver uma interdição mas é sempre válido
%porque não há maneira de saber quando uma incerteza é concretizada e se não é simplesmente
%um novo cuidado prestado diferente.
%No entanto não consideramos possível refinar incerteza para um dado valor nulo,
%a incerteza é expressamente substituída.
evolucaoIncerto(cuidadoprestado(IDServ,Nulo,Estab,Local),Nulo) :-
  cuidadoprestado(IDServ,ServB,EstabB,LocalB),
  excecao(cuidadoprestado(IDServ,ServB,EstabB,LocalB)),
  nao(nulo(ServB)),
  nao(nulo(EstabB)),
  nao(nulo(LocalB)),
  incertocuidadoprestado(IDServ,Nulo),
  assert((excecao(cuidadoprestado(IDServ,Serv,Estab,Local)):-
                  cuidadoprestado(IDServ,Serv,Estab,Local))),
  assert(cuidadoprestado(IDServ,Serv,Estab,Local)).

incertocuidadoprestado(IDServ,Nulo) :-
  cuidadoprestado(IDServ,ServB,Nulo,LocalB),
  clause(cuidadoprestado(IDServ,ServB,Nulo,LocalB),B,R),
  retract((cuidadoprestado(IDServ,ServB,Nulo,LocalB):-B)),
  retract(cuidadoprestado(IDServ,ServB,Nulo,LocalB)).

incertocuidadoprestado(IDServ,Nulo) :-
  cuidadoprestado(IDServ,Nulo,EstabB,LocalB),
  clause(cuidadoprestado(IDServ,Nulo,EstabB,LocalB),B,R),
  retract((cuidadoprestado(IDServ,Nulo,EstabB,LocalB):-B)),
  retract(cuidadoprestado(IDServ,Nulo,EstabB,LocalB)).

incertocuidadoprestado(IDServ,Nulo) :-
  cuidadoprestado(IDServ,ServB,EstabB,Nulo),
  clause(cuidadoprestado(IDServ,ServB,EstabB,Nulo),B,R),
  retract((cuidadoprestado(IDServ,ServB,EstabB,Nulo):-B)),
  retract(cuidadoprestado(IDServ,ServB,EstabB,Nulo)).

incertocuidadoprestado(IDServ,Nulo) :-
  cuidadoprestado(IDServ,Nulo,EstabB,Nulo),
  clause(cuidadoprestado(IDServ,Nulo,EstabB,Nulo),B,R),
  retract((cuidadoprestado(IDServ,Nulo,EstabB,Nulo):-B)),
  retract(cuidadoprestado(IDServ,Nulo,EstabB,Nulo)).

incertocuidadoprestado(IDServ,Nulo) :-
  cuidadoprestado(IDServ,ServB,Nulo,Nulo),
  clause(cuidadoprestado(IDServ,ServB,Nulo,Nulo),B,R),
  retract((cuidadoprestado(IDServ,ServB,Nulo,Nulo):-B)),
  retract(cuidadoprestado(IDServ,ServB,Nulo,Nulo)).

incertocuidadoprestado(IDServ,Nulo) :-
  cuidadoprestado(IDServ,Nulo,Nulo,Nulo),
  clause(cuidadoprestado(IDServ,Nulo,Nulo,Nulo),B,R),
  retract((cuidadoprestado(IDServ,Nulo,Nulo,Nulo):-B)),
  retract(cuidadoprestado(IDServ,Nulo,Nulo,Nulo)).

incertocuidadoprestado(IDServ,Nulo) :-
  cuidadoprestado(IDServ,Nulo,Nulo,LocalB),
  clause(cuidadoprestado(IDServ,Nulo,Nulo,LocalB),B,R),
  retract((cuidadoprestado(IDServ,Nulo,Nulo,LocalB):-B)),
  retract(cuidadoprestado(IDServ,Nulo,Nulo,LocalB)).

incertocuidadoprestado(IDServ,Nulo).


%Regressão de exceções de cuidados prestados

regressaoImpreciso(cuidadoprestado(IDServ,Serv,Estab,Local)) :-
  excecao(cuidadoprestado(IDServ,Serv,Estab,Local)),
  retract(excecao(cuidadoprestado(IDServ,Serv,Estab,Local))).

regressaoInterdito(cuidadoprestado(IDServ,Serv,Estab,Local)) :-
  cuidadoprestado(IDServ,Serv,Estab,Local),
  excecao(cuidadoprestado(IDServ,Serv,Estab,Local)),
  regressaonulo(cuidadoprestado(IDServ,Serv,Estab,Local)),
  clause(excecao(utente(IDServ,Serv,Estab,Local)),B,R),
  retract((excecao(utente(IDServ,Serv,Estab,Local)):-B)),
  retract(utente(IDServ,Serv,Estab,Local)).

regressaonulo(cuidadoprestado(IDServ,Serv,Estab,Local)) :-
  nulo(Serv).

regressaonulo(cuidadoprestado(IDServ,Serv,Estab,Local)) :-
  nulo(Estab).

regressaonulo(cuidadoprestado(IDServ,Serv,Estab,Local)) :-
  nulo(Local).

regressaoIncerteza(cuidadoprestado(IDServ,Serv,Estab,Local)) :-
  cuidadoprestado(IDServ,Serv,Estab,Local),
  excecao(cuidadoprestado(IDServ,Serv,Estab,Local)),
  nao(nulo(Serv)),
  nao(nulo(Estab)),
  nao(nulo(Local)),
  clause(excecao(cuidadoprestado(IDServ,Serv,Estab,Local)),B,R),
  retract((excecao(cuidadoprestado(IDServ,Serv,Estab,Local)):-B)),
  retract(cuidadoprestado(IDServ,Serv,Estab,Local)).




%Evolução de exceções de atos médicos

evolucaoImpreciso(atomedico(Data,IDUtente,IDServ,Custo)) :-
  nao(-atomedico(Data,IDUtente,IDServ,Custo)),
  nao(excecao(atomedico(Data,IDUtente,IDServ,Custo))),
  testaNulo(atomedico(Data,IDUtente,IDServ,Custo)).

testaNulo(atomedico(Data,IDUtente,IDServ,Custo)) :-
  atomedico(DataB,IDUtente,IDServ,CustoB),
  excecao(atomedico(DataB,IDUtente,IDServ,CustoB)),
  nao(nulo(DataB)),
  nao(nulo(CustoB)),
  retract(atomedico(DataB,IDUtente,IDServ,CustoB)),
  clause(excecao(atomedico(DataB,IDUtente,IDServ,CustoB)),B,R),
  retract((excecao(atomedico(DataB,IDUtente,IDServ,CustoB)):-B)),
  assert(excecao(atomedico(Data,IDUtente,IDServ,Custo))).

testaNulo(atomedico(Data,IDUtente,IDServ,Custo)) :-
  nao(atomedico(DataB,IDUtente,IDServ,CustoB)),
  assert(excecao(atomedico(Data,IDUtente,IDServ,Custo))).

%----------------------------------------------------

evolucaoNulo(atomedico(Nulo,IDUtente,IDServ,Custo),Nulo) :-
  nao(atomedico(DataB,IDUtente,IDServ,CustoB)),
  nao(excecao(atomedico(DataC,IDUtente,IDServ,CustoC))),
  assert((excecao(atomedico(DataD,IDUtente,IDServ,CustoD)):-
                  atomedico(Nulo,IDUtente,IDServ,Custo))),
  assert(atomedico(Nulo,IDUtente,IDServ,Custo)),
  assert(nulo(Nulo)).

evolucaoNulo(atomedico(Data,IDUtente,IDServ,Nulo),Nulo) :-
  nao(atomedico(DataB,IDUtente,IDServ,CustoB)),
  nao(excecao(atomedico(DataC,IDUtente,IDServ,CustoC))),
  assert((excecao(atomedico(DataD,IDUtente,IDServ,CustoD)):-
                  atomedico(Data,IDUtente,IDServ,Nulo))),
  assert(atomedico(Data,IDUtente,IDServ,Nulo)),
  assert(nulo(Nulo)).

evolucaoNulo(atomedico(Nulo,IDUtente,IDServ,Nulo),Nulo) :-
  nao(atomedico(DataB,IDUtente,IDServ,CustoB)),
  nao(excecao(atomedico(DataC,IDUtente,IDServ,CustoC))),
  assert((excecao(atomedico(DataD,IDUtente,IDServ,CustoD)):-
                  atomedico(Nulo,IDUtente,IDServ,Nulo))),
  assert(atomedico(Nulo,IDUtente,IDServ,Nulo)),
  assert(nulo(Nulo)).

%---------------------------------------------------

evolucaoIncerto(atomedico(Nulo,IDUtente,IDServ,Custo),Nulo) :-
  nao(excecao(DataC,IDUtente,IDServ,CustoC)),
  nao(excecao(atomedico(DataC,IDUtente,IDServ,CustoC))),
  assert((excecao(atomedico(DataB,IDUtente,IDServ,CustoB)):-
                  atomedico(Nulo,IDUtente,IDServ,Custo))),
  assert(atomedico(Nulo,IDUtente,IDServ,Custo)).

evolucaoIncerto(atomedico(Nulo,IDUtente,IDServ,Custo),Nulo) :-
  atomedico(DataB,IDUtente,IDServ,CustoB),
  excecao(atomedico(DataB,IDUtente,IDServ,CustoB)),
  nao(nulo(DataB)),
  nao(nulo(CustoB)),
  nao(excecao(atomedico(DataB,IDUtente,IDServ,CustoB))),
  clause(excecao(atomedico(DataB,IDUtente,IDServ,CustoB)),B,R),
  retract((excecao(atomedico(DataB,IDUtente,IDServ,CustoB)):-B)),
  retract(atomedico(DataB,IDUtente,IDServ,CustoB)),
  assert((excecao(atomedico(DataB,IDUtente,IDServ,CustoB)):-
                  atomedico(Nulo,IDUtente,IDServ,CustoB))),
  assert(atomedico(Nulo,IDUtente,IDServ,Custo)).



evolucaoIncerto(atomedico(Data,IDUtente,IDServ,Nulo),Nulo) :-
  nao(excecao(DataC,IDUtente,IDServ,CustoC)),
  nao(excecao(atomedico(DataC,IDUtente,IDServ,CustoC))),
  assert((excecao(atomedico(DataB,IDUtente,IDServ,CustoB)):-
                  atomedico(Data,IDUtente,IDServ,Nulo))),
  assert(atomedico(Data,IDUtente,IDServ,Nulo)).

evolucaoIncerto(atomedico(Data,IDUtente,IDServ,Nulo),Nulo) :-
  atomedico(DataB,IDUtente,IDServ,CustoB),
  excecao(atomedico(DataB,IDUtente,IDServ,CustoB)),
  nao(nulo(DataB)),
  nao(nulo(CustoB)),
  nao(excecao(atomedico(DataB,IDUtente,IDServ,CustoB))),
  clause(excecao(atomedico(DataB,IDUtente,IDServ,CustoB)),B,R),
  retract((excecao(atomedico(DataB,IDUtente,IDServ,CustoB)):-B)),
  retract(atomedico(DataB,IDUtente,IDServ,CustoB)),
  assert((excecao(atomedico(DataB,IDUtente,IDServ,CustoB)):-
                  atomedico(Data,IDUtente,IDServ,Nulo))),
  assert(atomedico(Data,IDUtente,IDServ,Nulo)).



evolucaoIncerto(atomedico(Nulo,IDUtente,IDServ,Nulo),Nulo) :-
  nao(excecao(DataC,IDUtente,IDServ,CustoC)),
  nao(excecao(atomedico(DataC,IDUtente,IDServ,CustoC))),
  assert((excecao(atomedico(DataB,IDUtente,IDServ,CustoB)):-
                  atomedico(Nulo,IDUtente,IDServ,Nulo))),
  assert(atomedico(Nulo,IDUtente,IDServ,Nulo)).

evolucaoIncerto(atomedico(Nulo,IDUtente,IDServ,Nulo),Nulo) :-
  atomedico(DataB,IDUtente,IDServ,CustoB),
  excecao(atomedico(DataB,IDUtente,IDServ,CustoB)),
  nao(nulo(DataB)),
  nao(nulo(CustoB)),
  DataB==CustoB,
  clause(excecao(atomedico(DataB,IDUtente,IDServ,CustoB)),B,R),
  retract((excecao(atomedico(DataB,IDUtente,IDServ,CustoB)):-B)),
  retract(atomedico(DataB,IDUtente,IDServ,CustoB)),
  assert((excecao(atomedico(DataB,IDUtente,IDServ,CustoB)):-
                  atomedico(Nulo,IDUtente,IDServ,Nulo))),
  assert(atomedico(Nulo,IDUtente,IDServ,Nulo)).

  %------------------------------------------------------

%Regressão de exceções de cuidados prestados

regressaoImpreciso(atomedico(Data,IDUtente,IDServ,Custo)) :-
  excecao(atomedico(Data,IDUtente,IDServ,Custo)),
  retract(atomedico(Data,IDUtente,IDServ,Custo)).

regressaoInterdito(atomedico(Data,IDUtente,IDServ,Custo)) :-
  atomedico(Data,IDUtente,IDServ,Custo),
  excecao(atomedico(Data,IDUtente,IDServ,Custo)),
  regressaonulo(atomedico(Data,IDUtente,IDServ,Custo)),
  clause(excecao(atomedico(Data,IDUtente,IDServ,Custo)),B,R),
  retract((excecao(atomedico(Data,IDUtente,IDServ,Custo)):-B)),
  retract((atomedico(Data,IDUtente,IDServ,Custo)).

regressaonulo(atomedico(Data,IDUtente,IDServ,Custo)) :-
  nulo(Data).

regressaonulo(atomedico(Data,IDUtente,IDServ,Custo)) :-
  nulo(Custo).

regressaoIncerteza(atomedico(Data,IDUtente,IDServ,Custo)) :-
  atomedico(Data,IDUtente,IDServ,Custo),
  excecao(atomedico(Data,IDUtente,IDServ,Custo)),
  nao(nulo(Data)),
  nao(nulo(Custo)),
  clause(excecao(atomedico(Data,IDUtente,IDServ,Custo)),B,R),
  retract((excecao(atomedico(Data,IDUtente,IDServ,Custo)):-B)),
  retract(atomedico(Data,IDUtente,IDServ,Custo)).


%Evolucao de conhecimento certo positivo e negativo

evolucao(excecao(P)) :-
  fail.

evolucao( P ) :-
  findall(Inv,+P::Inv,LInv),
  testa(LInv),
  removedesconhecido(P),
  assert(P).

removedesconhecido(utente(ID,Nome,Idade,Morada)) :-
  utente(ID,NomeB,IdadeB,MoradaB),
  excecao(utente(ID,NomeB,IdadeB,MoradaB)),
  clause(excecao(utente(ID,NomeB,IdadeB,MoradaB)),B,R),
  retract((excecao(utente(ID,NomeB,IdadeB,MoradaB)):-B)),
  retract(utente(ID,NomeB,IdadeB,MoradaB)).

removedesconhecido(utente(ID,Nome,Idade,Morada)) :-
  findall(excecao(utente(ID,NomeB,IdadeB,MoradaB)),excecao(utente(ID,NomeB,IdadeB,MoradaB)),LE),
  removedesconhecido(LE).

removedesconhecido([excecao(utente(ID,Nome,Idade,Morada))|T]) :-
  clause(excecao(utente(ID,Nome,Idade,Morada)),B,R),
  retract((excecao(utente(ID,Nome,Idade,Morada)):-B)),
  removedesconhecido(T).

removedesconhecido(-utente(ID,Nome,Idade,Morada)) :-
  findall(excecao(utente(ID,Nome,Idade,Morada)),excecao(utente(ID,Nome,Idade,Morada)),LE),
  removedesconhecido(LE).

removedesconhecido(cuidadoprestado(ID,Serv,Estab,Local)) :-
  findall(excecao(cuidadoprestado(ID,Serv,Estab,Local)),
          excecao(cuidadoprestado(ID,Serv,Estab,Local)),
          LE),
  removedesconhecido(LE).

removedesconhecido(-cuidadoprestado(ID,Serv,Estab,Local)) :-
  findall(excecao(cuidadoprestado(ID,Serv,Estab,Local)),
          excecao(cuidadoprestado(ID,Serv,Estab,Local)),
          LE),
  removedesconhecido(LE).

removedesconhecido([excecao(cuidadoprestado(ID,Serv,Estab,Local))|T]) :-
  clause(excecao(cuidadoprestado(ID,Serv,Estab,Local)),B,R),
  retract((excecao(cuidadoprestado(ID,Serv,Estab,Local)):-B)).

removedesconhecido(atomedico(Data,IDUtente,IDServ,Preco)) :-
  atomedico(DataB,IDUtente,IDServ,PrecoB),
  excecao(atomedico(DataB,IDUtente,IDServ,PrecoB)),
  clause(excecao(atomedico(DataB,IDUtente,IDServ,PrecoB)),B,R),
  retract((excecao(atomedico(DataB,IDUtente,IDServ,PrecoB)):-B)),
  retract(atomedico(DataB,IDUtente,IDServ,PrecoB)).

removedesconhecido(atomedico(Data,IDUtente,IDServB,PrecoB)) :-
  findall(excecao(atomedico(DataB,IDUtente,IDServ,PrecoB)),
          excecao(atomedico(DataB,IDUtente,IDServ,PrecoB)),LE),
  removedesconhecido(LE).

removedesconhecido([excecao(atomedico(Data,IDUtente,IDServ,Preco))|T]) :-
  clause(excecao(atomedico(Data,IDUtente,IDServ,Preco)),B,R),
  retract((excecao(atomedico(Data,IDUtente,IDServ,Preco)):-B)),
  removedesconhecido(T).

removedesconhecido([]).


%Regressão de conhecimento certo e incerto.

regressao(excecao(P)) :-
  fail.

regressao( P ) :-
  findall(Inv,-P::Inv,LInv),
  testa(LInv),
  retract(P).

testa([]).
testa([H | T]) :-
  H,
  testa(T).


%
% Predicados utéis
%
pertence(X,[X|Y]).
pertence(X,[Y|L]) :-
  pertence(X,L).

demo( Questao,verdadeiro ) :-
  Questao.
demo( Questao, falso ) :-
  -Questao.
demo( Questao,desconhecido ) :-
  nao( Questao ),
  nao( -Questao ).

demoCj([H],P) :-
  demo(H,P).

demoCj([H|L],verdadeiro) :-
  demo(H,verdadeiro),
  demoCj(L,verdadeiro).

demoCj([H|L],falso) :-
  demo(H,falso),
  demoCj(L,verdadeiro).

demoCj([H|L],falso) :-
  demo(H,falso),
  demoCj(L,falso).

demoCj([H|L],falso) :-
  demo(H,falso),
  demoCj(L,desconhecido).

demoCj([H|L],desconhecido) :-
  demo(H,verdadeiro),
  demoCj(L,desconhecido).

demoCj([H|L],desconhecido) :-
  demo(H,desconhecido),
  demoCj(L,desconhecido).

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
  length(S,0)
).

%Inserir conhecimento certo só se não existe conhecimento certo negativo
%igual, nem repetição, nem um nulo para esse ID.
+utente(ID,Nome,Idade,Morada) :: (
  nao(-utente(ID,Nome,Idade,Morada)),
  nao(utente(ID,Nome,Idade,Morada)),
  naonuloutente(ID)
).

naonuloutente(ID) :-
  utente(ID,NomeB,IdadeB,MoradaB),
  nao(nulo(NomeB)),
  nao(nulo(IdadeB)),
  nao(nulo(MoradaB)).

naonuloutente(ID) :-
  nao(utente(ID,NomeB,IdadeB,MoradaB)).

+(-utente(ID,Nome,Idade,Morada)) :: (
  nao(-utente(ID,Nome,Idade,Morada)),
  nao(utente(ID,Nome,Idade,Morada)),
  naonuloutente(ID)
).


%Apenas uma ocorrência de um ID de utente
%e de ID de serviço para cada ato médico.
%O mesmo cuidado prestado a um utente diferente tem de possuir
%um id único.
+atomedico(Data, IDUtente, IDServ, Preco) :: (
  findall(ID,atomedico(Data, ID, IDServ, Preco),S),
  length(S,0)
).

+atomedico(Data, IDUtente, IDServ, Preco) :: (
  findall(ID,atomedico(Data, IDUtente, IDS, Preco),S),
  length(S,0)
).

%Ato médico tem IDs existentes na base de Conhecimento
+atomedico(Data, IDUtente, IDServ, Preco) :: (
  findall(IDUtente,utente(IDUtente,Nome,Idade,Morada),S1),
  findall(IDServ,cuidadoprestado(IDServ,Serv,Estab,Local),S2),
  pertence(IDUtente,S1),
  pertence(IDServ,S2)
).

%Inserir conhecimento certo só se não existe conhecimento certo negativo
%igual, nem repetição, nem um nulo para esses dois IDs.
+atomedico(Data, IDUtente, IDServ, Preco) :: (
  nao(-atomedico(Data, IDUtente, IDServ, Preco)),
  nao(atomedico(Data, IDUtente, IDServ, Preco)),
  naonuloatomedico(IDUtente,IDServ)
).

naonuloatomedico(IDUtente,IDServ) :-
  atomedico(DataB, IDUtente, IDServ, PrecoB),
  nao(nulo(Data)),
  nao(nulo(Preco)).

naonuloatomedico(IDUtente,IDServ) :-
  nao(atomedico(DataB, IDUtente, IDServ, PrecoB)).

+(-atomedico(Data, IDUtente, IDServ, Preco)) :: (
  nao(-atomedico(Data, IDUtente, IDServ, Preco)),
  nao(atomedico(Data, IDUtente, IDServ, Preco)),
  naonuloutente(ID)
).

%Inserir cuidado prestado se não houver conhecimento certo negativo
%a contraria, não houver uma repetição nem houver uma interdição.
+cuidadoprestado(ID,Serv,Estab,Local) :: (
  nao(-cuidadoprestado(ID,Serv,Estab,Local)),
  nao(cuidadoprestado(ID,Serv,Estab,Local)),
  naonulocuidadoprestado(ID)
).

naonulocuidadoprestado(ID) :-
  cuidadoprestado(ID,ServB,EstabB,LocalB),
  nao(nulo(ServB)),
  nao(nulo(EstabB)),
  nao(nulo(LocalB)).

naonulocuidadoprestado(ID) :-
  nao(cuidadoprestado(ID,ServB,EstabB,LocalB)).

+(-cuidadoprestado(ID,Serv,Estab,Local)) :: (
  nao(-cuidadoprestado(ID,Serv,Estab,Local)),
  nao(cuidadoprestado(ID,Serv,Estab,Local)),
  naonulocuidadoprestado(ID)
).


%Não remover utentes nem cuidadoprestado
%sem remover os atos médicos em que participam
-utente(ID,Nome,Idade,Morada) :: (
  findall(ID,atomedico(Data,ID,IDServ,Preco),S),
  length(S,0)
).

-cuidadoprestado(ID,Serv,Estab,Local) :: (
  findall(ID,atomedico(Data,IDUtente,ID,Preco),S),
  length(S,0)
).


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
