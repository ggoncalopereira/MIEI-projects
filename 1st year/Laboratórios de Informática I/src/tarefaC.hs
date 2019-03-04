{-|
Module      : Main
Description : Módulo Haskell para resolução da Tarefa C - Execução de Programas
Copyright   : Gonçalo Pereira <a74413@alunos.uminho.pt>

Este é um módulo definido para resolução dos tabuleiros propostos
na TarefaC, relativa ao jogo de tabuleiro "Sokoban", no âmbito
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
          putStr (outStr (tarefa3 (lines inp)))

{- | A função 'tarefa1' será responsável pela execução de todo o programa, com o intuito de calcular a solução. Este utilizará a função 'resolve', atribuindo-lhe dados como a lista de cardinais e a lista de caixas.

-}
tarefa3 :: [String] -> [String]
tarefa3 s = lines (resolve tabuleiro caixas paredes comando posicao)
            where tabuleiro = (lines (filtraTab (unlines s)))
                  coords = (filtraCoord (unlines s))
                  caixas = converteTudo (reverse (tail (reverse (tail (lines (coords))))))
                  paredes = checkCardList (reverse tabuleiro) (0,0)
                  comando = ((reverse s)!!0)!!0
                  posicao = converte (head (lines (coords)))

{- | A função 'resolve' calculará a próxima posição através da averiguação do comando fornecido, assim como a existência de caixas e/ou paredes nos arredores da posição inicial.

-}
resolve :: [String] -> [(Int, Int)] -> [(Int, Int)] -> Char -> (Int, Int) -> String
resolve tabuleiro caixas paredes comando (x,y) | (comando == 'U') && (not (elem (x,(y+1)) caixas)) = (show x) ++ " " ++ (show (y+1))
                                               | (comando == 'R') && (not (elem ((x+1),y) caixas)) = (show (x+1)) ++ " " ++ (show y)
                                               | (comando == 'L') && (not (elem ((x-1),y) caixas)) = (show (x-1)) ++ " " ++ (show y)
                                               | (comando == 'D') && (not (elem (x,(y-1)) caixas)) = (show x) ++ " " ++ (show (y-1))
                                               | (comando == 'U') && (elem (x,(y+1)) caixas) && (not (elem (x,(y+2)) paredes)) && (not (elem (x,(y+2)) caixas)) = (show x) ++ " " ++ (show (y+1))
                                               | (comando == 'R') && (elem ((x+1),y) caixas) && (not (elem ((x+2),y) paredes)) && (not (elem ((x+2),y) caixas)) = (show (x+1)) ++ " " ++ (show y)
                                               | (comando == 'L') && (elem ((x-1),y) caixas) && (not (elem ((x-2),y) paredes)) && (not (elem ((x-2),y) caixas)) = (show (x-1)) ++ " " ++ (show y)
                                               | (comando == 'D') && (elem (x,(y-1)) caixas) && (not (elem (x,(y-2)) paredes)) && (not (elem (x,(y-2)) caixas)) = (show x) ++ " " ++ (show (y-1))
                                               | otherwise = (show x) ++ " " ++ (show y)

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