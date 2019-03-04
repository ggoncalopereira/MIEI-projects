{-|
Module      : Main
Description : Módulo Haskell para resolução da Tarefa 1
Copyright   : Gonçalo Pereira <a74413@alunos.uminho.pt>

Este é um módulo definido para resolução dos tabuleiros propostos
na Tarefa1, relativa ao jogo de tabuleiro "Sokoban", no âmbito
da disciplina de Laboratórios de Informática I. Esta página
servirá de documentação do código para tal tarefa.

-}

module Main where
  
import Data.Char
import qualified Data.Text as T

{- | A função outStr é uma função pré-definida nas aulas para efectuar um "unlines" de forma mais eficaz.

-}

outStr :: [String] -> String
outStr [] = "\n"
outStr t = unlines (map (T.unpack . T.stripEnd . T.pack) t)

main = do inp <- getContents
          putStr (outStr (tarefa1 (lines inp)))

{- | A função 'tarefa1' será responsável por receber o input sob forma de lista de strings, no qual estarão incluídos o tabuleiro, posição e coordenadas de caixas.

-}
tarefa1 :: [String] -> [String]
tarefa1 s = (verificaTudo (unlines s))

{- | A função 'verificaTudo' é o "cérebro" de todo este programa. Esta função receberá o mesmo tabuleiro que na função tarefa1, apenas com a diferença de estar sob forma de string, e, através de múltiplas operações via funções auxiliares, verificará todos os casos consoante a validade dos mesmos.

-}
verificaTudo ::  String -> [String]
verificaTudo s = if (dimensoes == (-1)) then if (caracteres == (-1)) then if (posicao == (-1)) then if (pontocaixa == (-1)) then if (repetidos == (-1)) then if (fora == (-1)) then ["OK"]
                                                                                                                                                             else [show (1 + (length (lines (filtraTab s))))]
                                                                                                                            else [show ( 1 + (length (lines (filtraTab s))) + repetidos )]
                                                                                                    else [show (1 + pontocaixa + (length (lines (filtraTab s))))]
                                                                          else [show ((length (lines (filtraTab s))) + 1)]
                                             else [show caracteres]
                 else [show dimensoes]

               where dimensoes  = (validaDimensao (lines (filtraTab s)) 1)
                     caracteres = checkTab (lines (filtraTab s)) (1,1)
                     posicao    = validaPosicao (reverse (lines (filtraTab s))) (lines (filtraCoord s))
                     pontocaixa = compareBoxDots (tail (lines (filtraCoord s))) (checkDotList (reverse (lines (filtraTab s))) (0,0))
                     repetidos  = noRepetitions (reverse (tail (lines (filtraCoord s)))) (length (reverse (tail (lines (filtraCoord s)))))
                     fora       = outOfBounds (lines (filtraTab s)) (converteTudo (tail (lines (filtraCoord s)))) 1

-- AUXILIARES --
--
--
--
--
{- | A função 'filtraTab' filtrará o tabuleiro no input inicial, e devolverá o mesmo input até ao ponto em que encontra um dígito.

-}
filtraTab :: String -> String
filtraTab x = fst (span (not.isDigit) x)

{- | A função 'filtraCoord' efectuará o inverso da função 'filtraTab'; esta devolverá o input restante que teria sido filtrado em 'filtraTab'.

-}
filtraCoord :: String -> String
filtraCoord x = snd (span (not.isDigit) x)

{- | A função 'converte' servirá para posterior conversão da posição (o primeiro elemento de 'filtraCoord') para um par de inteiros, com respectivas coordenadas no eixo do x e no eixo do y.

>>>converte "2 3"
(2,3)

-}
converte :: String -> (Int, Int)
converte s = let [sx, sy] = words s
                 sf = "(" ++ sx ++ "," ++ sy ++ ")"
             in read sf :: (Int, Int)

{- | A função 'converteTudo' efectuará o mesmo que a função 'converte', excepto que para listas de strings, de tal modo que devolverá uma lista de pares de inteiros.

-}
converteTudo :: [String] -> [(Int, Int)]
converteTudo [] = []
converteTudo (h:s) = (converte h) : (converteTudo s)

{- | A função 'checkChar' receberá um tabuleiro e um par de coordenadas, assim como um caracter, para verificar a existência de esse caracter no tabuleiro.

>>>checkChar ["abcd"] (0,0) 'a'
True

-}
checkChar :: [String] -> (Int, Int) -> Char -> Bool
checkChar tab (x,y) c = let ponto = ((tab!!y)!!x)
                        in if (ponto == c) then True
                           else False

-- DIMENSÕES --
--
--
--
--
{- | A função 'validaDimensao' apenas verificará se as dimensões do tabuleiro são válidas, a partir da comparação do tamanho de cada uma das linhas. Caso não seja, devolverá o número da linha cujo comprimento diferente da anterior.

-}
validaDimensao :: [String] -> Int -> Int
validaDimensao [x] num = (-1)
validaDimensao (x:y:xs) num | (length x == length y) = validaDimensao (x:xs) (num+1)
                            | otherwise              = num+1

--POSIÇÃO BONECO --
--
--
--
--
{- | A função 'validaPosicao' averiguará se a posição recebida está dentro das dimensões estipuladas do tabuleiro, assim como analisará se esta não se encontra em cima de uma parede (#).

-}
validaPosicao :: [String] -> [String] -> Int
validaPosicao x y | ((posicao == ' ') || (posicao == '.')) = (-1)
                  | otherwise                              = 0
                    where ponto   = converte (head y) 
                          coordx  = fst ponto
                          coordy  = snd ponto
                          posicao = ((x!!coordx)!!coordy)

--VALIDAR CARACTERES--
--
--
--
--
{- | A função 'checkSpot' funcionará em conjunto com uma outra função, de modo a confirmar se o tabuleiro é apenas composto por cardinais, pontos e espaços. Caso não seja, devolve False.

-}
checkSpot :: [String] -> (Int, Int) -> Bool
checkSpot tab (x,y) = let ponto = ((tab!!y)!!x)
                      in if (ponto == ' ' || ponto == '#' || ponto == '.') then True
                         else False
{- | Através da função 'checkSpot', a função 'checkTab' partirá através do valor obtido pela função 'checkSpot' para averiguar se o caracter em que se encontra é um caracter válido. Caso este não seja, a função devolverá a linha em que essa condição não se verifique

-}
checkTab :: [String] -> (Int, Int) -> Int
checkTab tab (x,y) | (x == sizex) && (y == sizey) && (checkSpot tab ((x-1), (y-1))) = (-1)
                   | (x == sizex) && (y < sizey) && (checkSpot tab ((x-1), (y-1)))  = checkTab tab (x, y+1)
                   | (x < sizex) && (y == sizey) && (checkSpot tab ((x-1), (y-1)))  = checkTab tab (x+1, 1)
                   | (x < sizex) && (y < sizey) && (checkSpot tab ((x-1), (y-1)))   = checkTab tab (x, y+1)
                   | otherwise                                                      = y
                   where sizex = length (head tab)
                         sizey = length tab

--VALIDAR POSIÇÃO CAIXAS--
--
--
--
--
{- | A função 'checkCardList' efectuará uma simples operação: a compilação sob forma de lista de todos os cardinais existentes no tabuleiro, para uma posterior comparação.

-}
checkCardList :: [String] -> (Int, Int) -> [(Int,Int)]
checkCardList tab (x,y) | ((x == sizex) && (y == sizey) && isAbox) = [(x,y)]
                        | ((x == sizex) && (y < sizey) && isAbox)  = [(x,y)] ++ checkCardList tab (x, y+1)
                        | ((x < sizex) && (y == sizey) && isAbox)  = [(x,y)] ++ checkCardList tab (x+1, 0)
                        | ((x < sizex) && (y < sizey) && isAbox)   = [(x,y)] ++ checkCardList tab (x, y+1)
                        | (x == sizex) && (y < sizey)              = checkCardList tab (x, y+1)
                        | (x < sizex) && (y == sizey)              = checkCardList tab (x+1, 0)
                        | (x < sizex) && (y < sizey)               = checkCardList tab (x, y+1)
                        | otherwise                                = []
                        where sizex = (length (head tab) - 1)
                              sizey = (length tab) - 1
                              isAbox = (checkChar tab (x, y) '#')


--VALIDAR POSIÇÃO CAIXAS VS PONTOS--
--
--
--
--
{- | A função 'checkDotList' funcionará do mesmo modo que a função 'checkCardList', com a diferença de compilar uma lista de todos os pontos existentes no tabuleiro.}

-}
checkDotList :: [String] -> (Int, Int) -> [(Int,Int)]
checkDotList tab (x,y) | ((x == sizex) && (y == sizey) && isAbox) = [(x,y)]
                       | ((x == sizex) && (y < sizey) && isAbox)  = [(x,y)] ++ checkDotList tab (x, y+1)
                       | ((x < sizex) && (y == sizey) && isAbox)  = [(x,y)] ++ checkDotList tab (x+1, 0)
                       | ((x < sizex) && (y < sizey) && isAbox)   = [(x,y)] ++ checkDotList tab (x, y+1)
                       | (x == sizex) && (y < sizey)              = checkDotList tab (x, y+1)
                       | (x < sizex) && (y == sizey)              = checkDotList tab (x+1, 0)
                       | (x < sizex) && (y < sizey)               = checkDotList tab (x, y+1)
                       | otherwise                                = []
                       where sizex = ((length (head tab)) - 1)
                             sizey = (length tab) -1
                             isAbox = (checkChar tab (x, y) '.')
{- | A função 'compareBoxDots' servirá para comparação entre o número de caixas e o número de pontos. Como, para que um tabuleiro seja válido, o número de caixas terá de ser igual ao número de pontos existentes, esta função, através da lista compilada pela função 'checkDotList', comparará o número de caixas com o número de pontos.

-}
compareBoxDots :: [String] -> [(Int, Int)] -> Int
compareBoxDots boxpos dotpos | (length boxpos == length dotpos) = (-1)
                             | (length boxpos > length dotpos) = (length dotpos) + 1
                             | (length boxpos < length dotpos) = (length boxpos) + 1

--VALIDAR CAIXAS DIFERENTES--
--
--
--
--
{- | A função 'noRepetitions' verificará se não há uma repetição de caixas, isto é, não poderão estar duas caixas na mesma posição. Caso existam duas caixas sob essa condição, será devolvida a linha da caixa que se está a tentar sobrepor.
-}
noRepetitions :: [String] -> Int -> Int
noRepetitions [] _ = (-1)
noRepetitions (box:boxes) count | (elem box boxes) = count
                                | otherwise        = noRepetitions boxes (count-1)

--VALIDAR POSIÇÃO CAIXAS--
--
--
--
--

{- | A função 'outOfBounds' apenas comparará a posição das caixas com as dimensões do tabuleiro, isto é, as caixas não podem estar fora das dimensões do tabuleiro. Devolve a linha em que tal não se verifique.
-}

outOfBounds :: [String] -> [(Int, Int)] -> Int -> Int
outOfBounds tab [] count = count
outOfBounds tab (box:boxes) count | ((fst box) >= ((length (head (tab))-1))) && ((snd box) <= ((length tab)-1)) = outOfBounds tab boxes (count + 1)
                                  | otherwise                                                                   = count 