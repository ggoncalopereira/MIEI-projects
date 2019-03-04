{-|
Module      : Main
Description : Módulo Haskell para resolução da Tarefa E - Área de uma Picture 
Copyright   : Gonçalo Pereira <a74413@alunos.uminho.pt>

Este é um módulo definido para resolução dos tabuleiros propostos
na TarefaE, relativa ao jogo de tabuleiro "Sokoban", no âmbito
da disciplina de Laboratórios de Informática I. Esta página
servirá de documentação do código para tal tarefa.

-} 
module Main where

import Graphics.Gloss
import Graphics.Gloss.Data.Picture
import qualified Data.Text as T
import GlossExtras

main = do inp <- getContents
          let (x,y) = tarefa5 (readPicture inp)
          putStrLn (show (round x) ++ " " ++ show (round y))
{- | A função 'tarefa5' será responsável pela execução de todo o programa, com o intuito de calcular a solução. Esta receberá o input sob forma de Picture e devolvê-lo-á através de um par de (Float, Float).

-}
tarefa5 :: Picture -> (Float, Float)
tarefa5 x = ((abs a)+(abs c),(abs b)+(abs d))
            where (a,b,c,d) = envolvingFigure x

{- | A função 'envolvingFigure' estará encarregue de efectuar todo o processamento de figuras. Como tal, esta receberá e analisará os casos, um a um, consoante o tipo de Picture que recebe, para, seguidamente,
lhe atribuir um valor sob forma de (Float, Float, Float, Float), que representará as quatro coordenadas respectivas às posições (Cima, Direita, Baixo, Esquerda). No caso de este receber uma Picture que altere
a estrutura de uma outra Picture, tal como Translate, Rotate ou Scale, a função recorrerá a outras funções auxiliares para o cálculo de tais coordenadas.

-}
envolvingFigure :: Picture -> (Float, Float, Float, Float)
envolvingFigure (Pictures x)= finalEnvolvingPicture (map envolvingFigure x) (0.0, 0.0, 0.0, 0.0)
envolvingFigure (Blank) = (a, a, a, a)
                          where a = 0 :: Float
envolvingFigure (Polygon x) = (a, b, c, d) --cima direita baixo esquerda
                          where a = maximum (map (\(px,_) -> px) x)
                                b = maximum (map (\(_,pw) -> pw) x)
                                c = minimum (map (\(px,_) -> px) x)
                                d = minimum (map (\(_,pw) -> pw) x)
envolvingFigure (Line x) = (a, b, c, d) --cima direita baixo esquerda
                          where a = maximum (map (\(px,_) -> px) x)
                                b = maximum (map (\(_,pw) -> pw) x)
                                c = minimum (map (\(px,_) -> px) x)
                                d = minimum (map (\(_,pw) -> pw) x)
envolvingFigure (Circle x)  = (x,x,(-x),(-x))
envolvingFigure (Bitmap x y _ _) = ((b/2), (a/2), -(b/2), -(a/2))
                            where a = fromIntegral x
                                  b = fromIntegral y
envolvingFigure (Color _ _) = (a, a, a, a)
                    where a = 0 :: Float
envolvingFigure (Translate x y pic) = translatePicture (envolvingFigure pic) x y
envolvingFigure (Rotate x pic) = rotatePicture (envolvingFigure pic) x
envolvingFigure (Scale x y pic) = scalePicture (envolvingFigure pic) x y

{- | A função 'rotatePicture' efectuará uma rotação, através da multiplicação da coordenada pelo seno e/ou cosseno de tal coordenada, via cálculos trigonométricos.
Esta fará uso da função 'degreesToRadians' para converter o 'x' que recebe, em graus, para radianos.

-}
rotatePicture :: (Float, Float, Float, Float) -> Float -> (Float, Float, Float, Float)
rotatePicture (a,b,c,d) x = ((a+a*y),(b+b*z),(c+c*y),(d+d*z))
                          where y = sin (degreesToRadians x)
                                z = cos (degreesToRadians x)

{- | A função 'degreesToRadians' converte um valor em graus para radianos.

-}
degreesToRadians :: Float -> Float
degreesToRadians x = (x/180*pi)  

{- | A função 'translatePicture' assemelha-se à função 'rotatePicture', excepto que para o caso em que a Picture recebida é a de Translate. Como tal, esta somará
os valores atribuídos em Float -> Float às coordenadas recebidas.

-}
translatePicture :: (Float, Float, Float, Float) -> Float -> Float -> (Float, Float, Float, Float)
translatePicture (a,b,c,d) x y = (a+y,b+x,c+y,d+x)

{- | A função 'scalePicture', tal como as funções 'translatePicture' e 'rotatePicture', funcionará para os casos em que a Picture recebida é a de Scale. Esta, por sua vez,
efectuará uma multiplicação de factores, através do 'x' e 'y' recebidos, pelas coordenadas ao rectângulo a que lhe corresponde.

-}
scalePicture :: (Float, Float, Float, Float) -> Float -> Float -> (Float, Float, Float, Float)
scalePicture (a,b,c,d) x y = (a*y,b*x,c*y,d*x)

{- | A função 'finalEnvolvingPicture' calculará as coordenadas da figura envolvente final, isto é, após terem sido desenhados todos os rectângulos que envolvem os diferentes
tipos de Picture, esta função calculará os respectivos 'x' e 'y' máximos e mínimos para, de seguida, criar um outro rectângulo com tais coordenadas. Estas serão utilizadas
no cerne da função 'tarefa5'.
-}
finalEnvolvingPicture :: [(Float, Float, Float, Float)] -> (Float, Float, Float, Float) -> (Float, Float, Float, Float)
finalEnvolvingPicture [] (a,b,c,d) = (a,b,c,d) 
finalEnvolvingPicture ((a,b,c,d):resto) (w,x,y,z) | (aa>ww) && (bb>xx) && (cc>yy) && (dd>zz) = finalEnvolvingPicture resto (a,b,c,d)
                                                  | (aa>ww) && (bb>xx) && (cc>yy) && (dd<zz) = finalEnvolvingPicture resto (a,b,c,z)
                                                  | (aa>ww) && (bb>xx) && (cc<yy) && (dd>zz) = finalEnvolvingPicture resto (a,b,y,d)
                                                  | (aa>ww) && (bb>xx) && (cc<yy) && (dd<zz) = finalEnvolvingPicture resto (a,b,y,z)
                                                  | (aa>ww) && (bb<xx) && (cc>yy) && (dd>zz) = finalEnvolvingPicture resto (a,x,c,d)
                                                  | (aa>ww) && (bb<xx) && (cc>yy) && (dd<zz) = finalEnvolvingPicture resto (a,x,c,z)
                                                  | (aa>ww) && (bb<xx) && (cc<yy) && (dd>zz) = finalEnvolvingPicture resto (a,x,y,d)
                                                  | (aa>ww) && (bb<xx) && (cc<yy) && (dd<zz) = finalEnvolvingPicture resto (a,x,y,z)
                                                  | (aa<ww) && (bb>xx) && (cc>yy) && (dd>zz) = finalEnvolvingPicture resto (w,b,c,d)
                                                  | (aa<ww) && (bb>xx) && (cc>yy) && (dd<zz) = finalEnvolvingPicture resto (w,b,c,z)
                                                  | (aa<ww) && (bb>xx) && (cc<yy) && (dd>zz) = finalEnvolvingPicture resto (w,b,y,d)
                                                  | (aa<ww) && (bb>xx) && (cc<yy) && (dd<zz) = finalEnvolvingPicture resto (w,b,y,z)
                                                  | (aa<ww) && (bb<xx) && (cc>yy) && (dd>zz) = finalEnvolvingPicture resto (w,x,c,d)
                                                  | (aa<ww) && (bb<xx) && (cc>yy) && (dd<zz) = finalEnvolvingPicture resto (w,x,c,z)
                                                  | (aa<ww) && (bb<xx) && (cc<yy) && (dd>zz) = finalEnvolvingPicture resto (w,x,y,d)
                                                  | (aa<ww) && (bb<xx) && (cc<yy) && (dd<zz) = finalEnvolvingPicture resto (w,x,y,z)
                                                  where aa = (abs a)
                                                        bb = (abs b)
                                                        cc = (abs c)
                                                        dd = (abs d)
                                                        ww = (abs w)
                                                        xx = (abs x)
                                                        yy = (abs y)
                                                        zz = (abs z)