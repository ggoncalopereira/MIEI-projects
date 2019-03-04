/**
 *
 * @author asus
 */

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalQueries;

import static java.lang.System.in;
import static java.time.temporal.TemporalQueries.chronology;
import static java.time.temporal.TemporalQueries.localDate;
import static java.time.temporal.TemporalQueries.localTime;
import static java.time.temporal.TemporalQueries.offset;
import static java.time.temporal.TemporalQueries.precision;
import static java.time.temporal.TemporalQueries.zone;
import static java.time.temporal.TemporalQueries.zoneId;
import static java.time.temporal.ChronoUnit.NANOS;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import java.time.*;
import static java.lang.System.out;
import java.util.Random;
import java.util.stream.*;
import java.util.stream.IntStream;
import java.util.Collections;
import java.util.Collection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.summingLong;
import static java.util.stream.Collectors.summarizingDouble;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.counting;
import static java.util.Comparator.comparing;
import java.util.*;
import java.util.DoubleSummaryStatistics;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import java.lang.System.*;
import java.nio.charset.StandardCharsets;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.AbstractMap.SimpleEntry;
import java.util.function.*;


public class Trans_Caixa_Exs {

    public static void memoryUsage() {
        final int mByte = 1024*1024;
        // Parâmetros de RunTime
        Runtime runtime = Runtime.getRuntime();
        out.println("== Valores de Utilização da HEAP [MB] ==");
        out.println("Memória Máxima RT:" + runtime.maxMemory()/mByte);
        out.println("Total Memory:" + runtime.totalMemory()/mByte);
        out.println("Memória Livre:" + runtime.freeMemory()/mByte);
        out.println("Memoria Usada:" + (runtime.totalMemory() - runtime.freeMemory())/mByte);
    }

    public static TransCaixa strToTransCaixa(String linha) {
        //
        double preco = 0.0;
        int ano = 0; int mes = 0; int dia = 0;
        int hora = 0; int min = 0; int seg = 0;
        String codTrans, codCaixa;
        // split()
        String[] campos = linha.split("/");
        codTrans = campos[0].trim();
        codCaixa = campos[1].trim();
        try {
            preco = Double.parseDouble(campos[2]);
        }
        catch(InputMismatchException | NumberFormatException e) { return null; }
        String[] diaMesAnoHMS = campos[3].split("T");
        String[] diaMesAno = diaMesAnoHMS[0].split(":");
        String[] horasMin = diaMesAnoHMS[1].split(":");
        try {
            dia = Integer.parseInt(diaMesAno[0]);
            mes = Integer.parseInt(diaMesAno[1]);
            ano = Integer.parseInt(diaMesAno[2]);
            hora = Integer.parseInt(horasMin[0]);
            min = Integer.parseInt(horasMin[1]);
        }
        catch(InputMismatchException | NumberFormatException e) { return null; }
        return TransCaixa.of(codTrans, codCaixa, preco, LocalDateTime.of(ano, mes, dia, hora, min, 0));
    }

    public static List<TransCaixa> setup(String nomeFich) {
        List<TransCaixa> ltc = new ArrayList<>();
        try (Stream<String> sTrans = Files.lines(Paths.get(nomeFich))) {
            ltc = sTrans.map(Trans_Caixa_Exs::strToTransCaixa).collect(toList());
        }
        catch(IOException exc) { out.println(exc.getMessage()); }
        return ltc;
    }

    public static List<TransCaixa> setup1(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { System.out.println(exc.getMessage()); }
        // List<String> -> List<TransCaixa>
        List<TransCaixa> lTrans = new ArrayList<>();
        lines.forEach(line -> lTrans.add(strToTransCaixa(line)));
        return lTrans;
    }

    public static <R> SimpleEntry<Double,R> testeBoxGen(Supplier<? extends R> supplier) {
        Crono.start();
        R resultado = supplier.get();
        Double tempo = Crono.stop();
        return new SimpleEntry<Double,R>(tempo, resultado);
    }

    public static <R> SimpleEntry<Double,R> testeBoxGenW(Supplier<? extends R> supplier) {
        // warmup
        for(int i = 1 ; i <= 5; i++) supplier.get();
        System.gc();
        Crono.start();
        R resultado = supplier.get();
        Double tempo = Crono.stop();
        return new SimpleEntry<Double,R>(tempo, resultado);
    }

    public static void main(String[] args) {
        // HÁ QUE MUDAR A STRING DA LINHA A SEGUIR PARA TER O PATH DO FICHEIRO
        String nomeFich = "C:\\Users\\matia\\OneDrive\\Documentos\\GitHub\\PSDJ-streams\\transcaixafiles\\transCaixa1M.txt";
        List<TransCaixa> ltc = new ArrayList<>();

        // LE O FICHEIRO DE TRANSACÇOES PARA List<TransCaixa> sem Streams
        Crono.start();
        ltc = setup1(nomeFich);
        out.println("Setup com List<String>: " + Crono.stop()*1000 + " ms");
        out.println("Transacções lidas Lists: " + ltc.size());
        ltc.clear();

        // LE O FICHEIRO DE TRANSACÇOES PARA List<TransCaixa> com Streams
        Crono.start();
        ltc = setup(nomeFich);
        out.println("Setup com Streams: " + Crono.stop()*1000 + " ms");
        out.println("Transacções lidas (Streams): " + ltc.size());
        memoryUsage();

        // EXERCICIOS

        //------------------------Exercício 1-----------------------------------
        int ltcSize = ltc.size();
        double[] altA = new double[ltcSize];

        int i1A = 0;
        for(TransCaixa transAux : ltc){
            altA[i1A] = (transAux.getValor());
            i1A ++;
        }

        DoubleStream dStream1 = ltc.stream().mapToDouble(TransCaixa :: getValor);

        Stream<Double> stream1 = ltc.stream().map(TransCaixa :: getValor);

        Crono.start();
        double sum = 0;
        for(i1A = 0; i1A < ltcSize; i1A++){
            sum += altA[i1A];
        }
        out.println("tempo real resultado da sum com for simples: "+ Crono.stop());
        final double sum1 = sum;
        Supplier resSum1 = () -> sum1;
        //out.println("resultado da sum com for simples: " + testeBoxGen(resSum1).getKey());

        Crono.start();
        double sum2 = dStream1.sum();
        out.println("tempo real resultado da sum com doubleStream: "+ Crono.stop());
        Supplier resSum2 = () -> sum2;
        //out.println("resultado da sum com doubleStream: " + testeBoxGen(resSum2).getKey());

        Crono.start();
        double sum3 = stream1.mapToDouble(Double::doubleValue).sum();
        out.println("tempo real resultado da sum com stream: "+ Crono.stop());
        Supplier resSum3 = () -> sum3;
        //out.println("resultado da sum com stream: " + testeBoxGen(resSum3).getKey());

        DoubleStream dStream12 = ltc.stream().mapToDouble(TransCaixa :: getValor);

        Stream<Double> stream12 = ltc.stream().map(TransCaixa :: getValor);

        Crono.start();
        double sum4 = dStream12.parallel().sum();
        out.println("tempo real resultado da sum com double stream parallel: "+ Crono.stop());
        Supplier resSum4 = () -> sum4;
        //out.println("resultado da sum com double stream parallel: " + testeBoxGen(resSum4).getKey());

        Crono.start();
        double sum5 = stream12.mapToDouble(Double::doubleValue).parallel().sum();
        out.println("tempo real resultado da sum com stream parallel: "+ Crono.stop());
        Supplier resSum5 = () -> sum5;
        //out.println("resultado da sum com stream parallel: " + testeBoxGen(resSum5).getKey());

        //DoubleStream alt1B = new DoubleStream(); doesn't work this way
        //Stream<Double> alt1C = new Stream<Double>(); doesn't work this way
        //------------------------Exercício 2-----------------------------------

        Comparator<TransCaixa> transPorData =
                (TransCaixa tc1, TransCaixa tc2) -> { LocalDateTime ldt1 = tc1.getData();
                    LocalDateTime ldt2 = tc2.getData();
                    if(ldt1.equals(ldt2)) return 0;
                    else if(ldt1.isBefore(ldt2)) return -1;
                    else return 1 ;
                };

        Crono.start();
        List<TransCaixa> ltcSorted = ltc.stream()
                                             .sorted(transPorData)
                                             .collect(toList());
        out.println("tempo real resultado do sort com stream: "+ Crono.stop());
        Supplier ltcSorted1 = () -> ltcSorted;
        //out.println("resultado do sort com stream: " + testeBoxGen(ltcSorted1).getKey());

        Crono.start();
        List<TransCaixa> ltcParallelSorted = ltc.parallelStream()
                                                .sorted(transPorData)
                                                .collect(toList());
        out.println("tempo real resultado do sort com stream parallel: "+ Crono.stop());
        Supplier ltcParallelSorted1 = () -> ltcParallelSorted;
        //out.println("resultado do sort com stream parallel: " + testeBoxGen(ltcParallelSorted1).getKey());

        Crono.start();
        Supplier<SortedSet<TransCaixa>> supplyTreeSetTcx = () -> new TreeSet<>(transPorData);
        SortedSet<TransCaixa> ltcSorted2 = ltc.stream()
                                              .collect(toCollection(supplyTreeSetTcx));
        out.println("tempo real resultado do sort de treeSet com stream: "+ Crono.stop());
        Supplier ltcTreeSorted1 = () -> ltcSorted2;
        //out.println("resultado do sort de treeSet com stream: " + testeBoxGen(ltcTreeSorted1).getKey());

        Crono.start();
        Supplier<SortedSet<TransCaixa>> supplyTreeSetTcx1 = () -> new TreeSet<>(transPorData);
        SortedSet<TransCaixa> ltcParallelSorted2 = ltc.stream().parallel()
                                                      .collect(toCollection(supplyTreeSetTcx1));
        out.println("tempo real resultado do sort de treeSet com stream parallel: "+ Crono.stop());
        Supplier ltcTreeParallelSorted2 = () -> ltcParallelSorted2;
        //out.println("resultado do sort de treeSet com stream parallel: " + testeBoxGen(ltcTreeParallelSorted2).getKey());

        //------------------------Exercício 3-----------------------------------
        IntStream intStream;
        int[] intArray;
        List<Integer> intList;

        int rand = 1_000 + new Random ().nextInt (8_000);
        intArray = new int[rand];
        intList = new ArrayList <> (rand);
        /*Populate*/
        for (int i = 0; i < rand; i++) {
            int randNum = new Random ().nextInt (9_999);
            intArray[i] = randNum;
            intList.add (randNum);
        }
        System.out.println ("Populate End");

        /*-----------------------------------*/
        int nSize = intArray.length;
        for (int i = 0; i < nSize; i++) {
            for (int j = i + 1; j < nSize; j++){
                if(intArray[i] == intArray[j]){
                    intArray[j] = intArray[nSize - 1];
                    nSize--;
                    j--;
                }
            }
        }
        int[] arrayWithoutDuplicates = Arrays.copyOf(intArray, nSize);
        System.out.println (arrayWithoutDuplicates.length);

        List<Integer> noDupsList = new ArrayList <> ();
        intList.forEach ((i) -> {
            if(!noDupsList.contains (i))
                noDupsList.add (i);
        });
        System.out.println (noDupsList.size ());

        intStream = IntStream.of (intArray).distinct ();
        System.out.println (intStream.count ());
        intStream.close ();

        //------------------------Exercício 4-----------------------------------
        int finaLen;
        int[] divs1;
        for (int i = 1; i <= 8; i*=2) {
            finaLen = 1_000_000*i;
            divs1 = new int[finaLen];
            Arrays.fill (divs1, 100);

            IntStream sequential1 = Arrays.stream (divs1).sequential ();
            System.out.println ("\nCom -> " + finaLen + " <- Registos\n");

            System.out.println ("Sequential Stream");
            Supplier sSa = () -> {
                sequential1.forEach ((a) -> divBy.apply (a, 25));
                return null;
            };

            System.out.println ("Sequential Stream usando a BiFuntion: Tempo->" + testeBoxGen (sSa).getKey ());

            IntStream sequential2 = Arrays.stream (divs1).sequential ();
            Supplier sSb = () -> {
                sequential2.forEach ((a) -> division (a, 25));
                return null;
            };
            System.out.println ("Sequential Stream usando o Metodo: Tempo->" + testeBoxGen (sSb).getKey ());

            IntStream sequential3 = Arrays.stream (divs1).sequential ();
            Supplier sSc = () -> {
                sequential3.forEach ((a) -> divLamb.div (a, 25));
                return null;
            };
            System.out.println ("Sequential Stream usando a expressão lambda explícita: Tempo->" + testeBoxGen (sSc).getKey ());

            System.out.println ("Parallel Stream");
            IntStream parallel = Arrays.stream (divs1).parallel ();
            Supplier sSaa = () -> {
                parallel.forEach ((a) -> divBy.apply (a, 25));
                return null;
            };
            System.out.println ("Parallel Stream usando a BiFuntion: Tempo->" + testeBoxGen (sSaa).getKey ());

            IntStream parallel1 = Arrays.stream (divs1).parallel ();
            Supplier sSbb = () -> {
                parallel1.forEach ((a) -> division (a, 25));
                return null;
            };
            System.out.println ("Parallel Stream usando o Metodo: Tempo->" + testeBoxGen (sSbb).getKey ());

            IntStream parallel2 = Arrays.stream (divs1).parallel ();
            Supplier sScc = () -> {
                parallel2.forEach ((a) -> divLamb.div (a, 25));
                return null;
            };
            System.out.println ("Parallel Stream usando a expressão lambda explícita: Tempo->" + testeBoxGen (sScc).getKey ());
        }


        //------------------------Exercício 5-----------------------------------

        Crono.start();
        List<TransCaixa> ltcSorted5 = ltc.stream()
                                         .sorted(transPorData)
                                         .collect(toList());
        out.println("tempo real resultado sort antes de collect: "+ Crono.stop());

        Crono.start();
        List<TransCaixa> ltcSorted52 = ltc.subList(0,ltcSize);
        ltcSorted52.sort(transPorData);
        out.println("tempo real resultado sort depois de collect: "+ Crono.stop());

        Crono.start();
        Supplier<SortedSet<TransCaixa>> supplyTreeSetTcx5 = () -> new TreeSet<>(transPorData);
        SortedSet<TransCaixa> ltcSorted53 = ltc.stream()
                .collect(toCollection(supplyTreeSetTcx));
        out.println("tempo real resultado do sort de treeSet com stream: "+ Crono.stop());

        //------------------------Exercício 6-----------------------------------

        Crono.start();
        Map<Month, Map<Integer, Map<Integer, List<TransCaixa>>>> mapaPorMDH =
                ltc.stream()
                        .collect(groupingBy(t -> t.getData().getMonth(),
                                groupingBy(t -> t.getData().getDayOfMonth(),
                                        groupingBy(t -> t.getData().getHour()))));



        //------------------------Exercício 7-----------------------------------

        //------------------------Exercício 8-----------------------------------
        //JAVA 7
        Iterator<TransCaixa> it = ltc.iterator();
        TransCaixa elemento8A = it.next();
        TransCaixa maximo8A = elemento8A;
        while(it.hasNext()){
            elemento8A = it.next();
            if(elemento8A.getData().getHour() >= 16 && elemento8A.getData().getHour() <= 20){
                if(elemento8A.getValor() > maximo8A.getValor()) maximo8A = elemento8A;
            }
        }
        out.println("Exercício 8 - JAVA 7: Valor de " + maximo8A.getValor());


        //JAVA 8
        List<TransCaixa> alt8B = ltc.stream()
                                    .filter(a -> (a.getData().getHour() > 16 & a.getData().getHour() < 20))
                                    .sorted(Comparator.comparing(TransCaixa::getValor).reversed())
                                   .collect(Collectors.toList());

        TransCaixa maximo8B = alt8B.get(0);

        out.println("Exercício 8 - JAVA 8: Valor de " + maximo8B.getValor());

        //------------------------Exercício 9-----------------------------------

        //------------------------Exercício 10----------------------------------

        //------------------------Exercício 11----------------------------------

        //------------------------Exercício 12----------------------------------


    }

    static int division(int a, int b){
        if(b == 0) b = 1;
        return a / b;
    }

    static BiFunction<Integer,Integer,Integer> divBy = (integer, integer2) -> {
        if(integer2 == 0) integer2 = 1;
        return integer/integer2;
    };

    static a divLamb = (a, b) -> {
        if(b == 0) b = 1;
        return a/b;
    };

}

@FunctionalInterface
interface a {
    int div(int a, int b);
}

