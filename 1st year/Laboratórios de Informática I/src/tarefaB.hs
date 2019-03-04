{-|
Module      : Main
Description : Módulo Haskell para resolução da Tarefa B - Próxima Posição
Copyright   : Gonçalo Pereira <a74413@alunos.uminho.pt>

Este é um módulo definido para resolução dos tabuleiros propostos
na TarefaB, relativa ao jogo de tabuleiro "Sokoban", no âmbito
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
          putStr (outStr (tarefa2 (lines inp)))

{- | A função 'tarefa2' será responsável por receber o input sob forma de lista de strings, no qual estarão incluídos o tabuleiro, posição e coordenadas de caixas. Esta será também o "cérebro" de toda a operação, estando encarregue de controlar o resultado através do output recebido pelas funções auxiliares.

-}
tarefa2 :: [String] -> [String]
tarefa2 s = z
            where newtab = swapItAll (unlines s)
                  lin = length (head newtab)
                  col = length (newtab)
                  x   = checkCardList (reverse newtab) (0,0)
                  y   = isCard ((lin-1),(col-1)) x (0,0)
                  z   = swapCards newtab y

{- | A função 'swap', através do tabuleiro, posição e um caracter recebido, efectuará uma troca de caracteres.

>>>swap ["abcd"] (0,0) 'e'
["ebcd"]

-}
swap :: [String] -> (Int, Int) -> Char -> [String]
swap tab (x,y) c = let (antes,depois) = splitAt y (reverse tab)
                       l = head depois
                       (anteslin,depoislin) = splitAt x l
                       l' = anteslin ++ [c] ++ (tail depoislin)
                   in reverse (antes ++ [l'] ++ (tail depois))

{- | A função 'checkBox' será um mero nome diferente para a função 'elem', sendo que esta funcionará para comparação, na medida em que verificará se um elemento sob forma de par de coordenadas se encontra na lista de pontos. Esta função servirá para uma posterior comparação de 'H' com '.'.

-}
checkBox :: [String] -> (Int, Int) -> [(Int, Int)] -> Bool
checkBox tab (x,y) dots = (elem (x,y) dots)

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
converteTudo (x:y) = (converte x) : (converteTudo y) 

{- | A função 'filtraTab' filtrará o tabuleiro no input inicial, e devolverá o mesmo input até ao ponto em que encontra um dígito.

-}
filtraTab :: String -> String
filtraTab x = fst (span (not.isDigit) x)

{- | A função 'filtraCoord' efectuará o inverso da função 'filtraTab'; esta devolverá o input restante que teria sido filtrado em 'filtraTab'.

-}
filtraCoord :: String -> String
filtraCoord x = snd (span (not.isDigit) x)

{- | A função 'swapItAll' será uma função geral da função swap, onde, através de uma função auxiliar, passará todas as coordenadas de caixas para o tabuleiro.

-}
swapItAll :: String -> [String]
swapItAll tab = swapItAllAux tabuleiro coordenadas 1 pontos
                where tabuleiro   = (lines (filtraTab tab))
                      coordenadas = (converteTudo (lines (filtraCoord tab)))
                      pontos      = checkDotList (reverse tabuleiro) (0,0)

{- | A função 'swapItAllAux' será a função auxiliar de 'swapItAll', que efectuará a passagem de coordenadas para o tabuleiro.

-}
swapItAllAux :: [String] -> [(Int, Int)] -> Int -> [(Int, Int)] -> [String]
swapItAllAux tab [] _ _ = tab
swapItAllAux tab coord 1 dots = swapItAllAux (swap tab (head coord) 'o') (tail coord) 2 dots
swapItAllAux tab ((x,y):rest) z dots | (checkBox tab (x,y) dots) = swapItAllAux (swap tab (x,y) 'I') rest (z+1) dots
                                     | otherwise                 = swapItAllAux (swap tab (x,y) 'H') rest (z+1) dots

{- | A função 'checkChar' receberá um tabuleiro e um par de coordenadas, assim como um caracter, para verificar a existência de esse caracter no tabuleiro.

>>>checkChar ["abcd"] (0,0) 'a'
True

-}                  
checkChar :: [String] -> (Int, Int) -> Char -> Bool
checkChar tab (x,y) c = let ponto = ((tab!!y)!!x)
                        in if (ponto == c) then True
                           else False

{- | A função 'checkCardList' efectuará uma simples operação: a compilação sob forma de lista de todos os cardinais existentes no tabuleiro, para uma posterior comparação.

-}
checkCardList :: [String] -> (Int, Int) -> [(Int,Int)]
checkCardList tab (x,y) | ((x == sizex) && (y == sizey) && isAcard) = [(x,y)]
                        | ((x == sizex) && (y < sizey) && isAcard)  = [(x,y)] ++ checkCardList tab (x, y+1)
                        | ((x < sizex) && (y == sizey) && isAcard)  = [(x,y)] ++ checkCardList tab (x+1, 0)
                        | ((x < sizex) && (y < sizey) && isAcard)   = [(x,y)] ++ checkCardList tab (x, y+1)
                        | (x == sizex) && (y < sizey)              = checkCardList tab (x, y+1)
                        | (x < sizex) && (y == sizey)              = checkCardList tab (x+1, 0)
                        | (x < sizex) && (y < sizey)               = checkCardList tab (x, y+1)
                        | otherwise                                = []
                        where sizex = (length (head tab) - 1)
                              sizey = (length tab) - 1
                              isAcard = (checkChar tab (x, y) '#')

{- | A função 'checkDotList' funcionará do mesmo modo que a função 'checkCardList', com a diferença de compilar uma lista de todos os pontos existentes no tabuleiro.}

-}
checkDotList :: [String] -> (Int, Int) -> [(Int,Int)]
checkDotList tab (x,y) | ((x == sizex) && (y == sizey) && isAdot)  = [(x,y)]
                       | ((x == sizex) && (y < sizey) && isAdot)   = [(x,y)] ++ checkDotList tab (x, y+1)
                       | ((x < sizex) && (y == sizey) && isAdot)   = [(x,y)] ++ checkDotList tab (x+1, 0)
                       | ((x < sizex) && (y < sizey) && isAdot)    = [(x,y)] ++ checkDotList tab (x, y+1)
                       | (x == sizex) && (y < sizey)               = checkDotList tab (x, y+1)
                       | (x < sizex) && (y == sizey)               = checkDotList tab (x+1, 0)
                       | (x < sizex) && (y < sizey)                = checkDotList tab (x, y+1)
                       | otherwise                                 = []
                        where sizex = (length (head tab) - 1)
                              sizey = (length tab) - 1
                              isAdot = (checkChar tab (x, y) '.')

{- | A função 'isCard' verifica quais os cardinais redudantes, para posteriormente serem filtrados e convertidos em espaços.

-}
isCard :: (Int, Int) -> [(Int, Int)] -> (Int, Int) -> [(Int, Int)]
isCard (lin,col) p (x,y) | (x==0) && (y==0) && (right && downright && down) = [(x,y)] ++ isCard (lin, col) p (x, (y+1))
                         | (x==0) && (y < col) && (up && upright && right && downright && down) = [(x,y)] ++ isCard (lin, col) p (x, (y+1))
                         | (x==0) && (y == col) && (up && upright && right) = [(x,y)] ++ isCard (lin, col) p (x+1, 0)
                         | (x < lin) && (y==0) && (left && downleft && down && downright && right) = [(x,y)] ++ isCard (lin, col) p (x, y+1)
                         | (x < lin) && (y < col) && (up && down && left && right && upleft && upright && downleft && downright) = [(x,y)] ++ isCard (lin, col) p (x, y+1)
                         | (x < lin) && (y == col) && (up && upleft && upright && left && right) = [(x,y)] ++ isCard (lin, col) p (x+1, 0)
                         | (x == lin) && (y==0) && (left && downleft && down) = [(x,y)] ++ isCard (lin, col) p (x, y+1)
                         | (x == lin) && (y < col) && (left && upleft && up && downleft && down) = [(x,y)] ++ isCard (lin, col) p (x, y+1)
                         | (x == lin) && (y == col) && (left && upleft && up) = [(x,y)]
                         | (x==0) && (y==0) = isCard (lin, col) p (x, y+1)
                         | (x==0) && (y < col) = isCard (lin, col) p (x, y+1)
                         | (x==0) && (y == col) = isCard (lin, col) p (x+1, 0)
                         | (x < lin) && (y==0) = isCard (lin, col) p (x, y+1)
                         | (x < lin) && (y < col) = isCard (lin, col) p (x, y+1)
                         | (x < lin) && (y == col) = isCard (lin, col) p (x+1, 0)
                         | (x == lin) && (y==0) = isCard (lin, col) p (x, y+1)
                         | (x == lin) && (y < col) = isCard (lin, col) p (x, y+1)
                         | (x == lin) && (y == col) = []
                   where up = (elem (x,(y-1)) p)
                         down = (elem (x, (y+1)) p)
                         left = (elem ((x-1), y) p)
                         right = (elem ((x+1), y) p)
                         upleft = (elem ((x-1),(y-1)) p)
                         upright = (elem ((x+1),(y-1)) p)
                         downleft = (elem ((x-1),(y+1)) p)
                         downright = (elem ((x+1),(y+1)) p)

{- | A função 'swapCards' converterá todos os cardinais da função 'isCard' em espaços.

-}

swapCards :: [String] -> [(Int, Int)] -> [String]
swapCards tab [] = tab
swapCards tab ((x,y):red) = swapCards (swap tab (x,y) ' ') red