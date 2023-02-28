/*
Crie uma classe TicTacToe que permitirá escrever um programa completo para repoduzir o jogo da velha. 
A classe contém um array bidimensinal privado 3 por 3 de inteiros. O construtor deve inicializar a grade vazia com todos como zero.
Permita dois jogadores humanos. Para onde quer que o primeiro jogadore se mova, coloque um 1 no quadrado especificado; 
coloque um 2 no local para qual o segundo jogador se mover. Todo movimento deve ocorrer em um quadrado vazio.
Depois de cada jogada determine se o jogo foi ganho e se aconteceu um empate. Se você se sentir motivado,
modifique o seu programa de modo com que o computador faça o movimento para um dos jogadores. Além disso,
permita que o jogador especifique se quer ser o primeiro ou o segundo. Se você se sentir excepcionalmente motivado,
desenvolva um programa que jogue o Tic-Tac-Toe tridimensional em uma grade 4 por 4 por 4.
*/

import javax.swing.JOptionPane; //Importa as caixas de texto input e output
import java.util.Scanner; //Importa o Scanner para input
import java.util.Random; //Importa o randomizador
//import java.util.InputMismatchException; //Importa exceção

public class TicTacToe extends JOptionPane{
    Scanner scan = new Scanner(System.in); //Declara o scanner como scan
    Random randomize = new Random(); //Declara o randomizador como randomize
    
    private final char vet[][] = new char[3][3]; //O mapa do jogo da velha
    private int x; //Coordenada X do jogo
    private int y; //Coordenada Y do jogo
    
    public TicTacToe(){ //Coloca os "-" como padrão no mapa do jogo
        for(int x = 0; x < vet.length; x++){
            for(int y = 0; y < vet.length; y++){
                vet[x][y] = '-';
            }
        }
    }//Fim do construtor
    
    public void jogar(){ //Jogar com 2 jogadores
        escolherJogador(); //Escolha de jogador, 1 ou 2
        
        mapaDeJogo(); //Mostra o mapa vazio
        
        boolean continuarLoop; //Variável usada no loop de exceção
        
        for(int x = 0; x > -1;){//Laço X
            
            do{//Loop para verificar exceção
                try{
                    jogadaDoJogador1(); //Jogador 1 joga
                    continuarLoop = false;
                }
                catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                    JOptionPane.showMessageDialog(null, "Coloque um número possível dentro do jogo (0 - 2)", "Erro", JOptionPane.ERROR_MESSAGE);
                    continuarLoop = true;
//                    arrayIndexOutOfBoundsException.printStackTrace();
                }   
            }while(continuarLoop == true); //Fim do loop de exceção
            
            mapaDeJogo(); //Após isso o mapa é atualizado
            
            if(verificarSeGanhouOuPerdeu() == true) //Depois verifica se alguém ganhou
                fimDeJogo(); //Finaliza o jogo se for TRUE
                     
            do{//Loop para verificar exceção
                try{
                    jogadaDoJogador2(); //Jogador 2 joga
                    continuarLoop = false;
                }
                catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                    JOptionPane.showMessageDialog(null, "Coloque um número possível dentro do jogo (0 - 2)", "Erro", JOptionPane.ERROR_MESSAGE);
                    continuarLoop = true;
//                    arrayIndexOutOfBoundsException.printStackTrace();
                }
            }while(continuarLoop == true); //Fim do loop de exceção
            
            mapaDeJogo(); //Após isso o mapa é atualizado
            
            if(verificarSeGanhouOuPerdeu() == true) //Depois verifica se alguém ganhou
                fimDeJogo(); //Finaliza o jogo se for TRUE
        }//Fim do laço X
    }//Fim do método
    
    public void jogarComMaquina(){ //Jogar com a máquina
        //Variável num para determinar com o que a máquina vai jogar
        int num = escolherJogador(); //Escolha de jogador, 1 ou 2
        
        mapaDeJogo(); //Mostra o mapa vazio
        
        boolean continuarLoop; //Variável usada no loop de exceção
        
        for(int x = 0; x > -1;){//Laço X
            
            if(num == 1) //Determina com quem você joga e quem a máquina joga
                do{
                    try{
                        jogadaDoJogador1(); //Ou tu joga como X
                        continuarLoop = false;
                    }
                    catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                        JOptionPane.showMessageDialog(null, "Coloque um número possível dentro do jogo (0 - 2)", "Erro", JOptionPane.ERROR_MESSAGE);
                        continuarLoop = true;
//                    arrayIndexOutOfBoundsException.printStackTrace();
                    }
                }while(continuarLoop == true); //Fim do loop de exceção
            else
                do{
                    try{
                        jogadaDoJogador2(); //Ou tu joga como O
                        continuarLoop = false;
                    }
                    catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                        JOptionPane.showMessageDialog(null, "Coloque um número possível dentro do jogo (0 - 2)", "Erro", JOptionPane.ERROR_MESSAGE);
                        continuarLoop = true;
//                    arrayIndexOutOfBoundsException.printStackTrace();
                    }
                }while(continuarLoop == true); //Fim do loop de exceção
            
            mapaDeJogo(); //Após isso o mapa é atualizado
            
            if(verificarSeGanhouOuPerdeu() == true) //Depois verifica se alguém ganhou
                fimDeJogo(); //Finaliza o jogo se for TRUE
            
            jogadaDaMaquina(escolhaDaMaquina(num)); //A máquina joga e a escolha dela e feita com base na variável num
            mapaDeJogo(); //Após isso o mapa é atualizado
            
            if(verificarSeGanhouOuPerdeu() == true) //Depois verifica se alguém ganhou
                fimDeJogo(); //Finaliza o jogo se for TRUE
        }//Fim do laço X
    }//Fim do método
    
    public int escolherJogador(){
        int num = 0; //Variável usada para verificar se o jogador é válido depois
        boolean continuarLoop = true;
        do{
            try{
                //Mensagem de escolha de jogador
                num = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual jogador você deseja ser?\n 1 ou 2?\n (Jogador 1 = X e Jogador 2 = O)", 
                                                      "Início", JOptionPane.QUESTION_MESSAGE));
                continuarLoop = false;
            }
            catch(NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(null, "Escolha entre o jogador X (1) ou O (2)", "ERRO", JOptionPane.ERROR_MESSAGE);
                continuarLoop = true;
            }
        }while(continuarLoop == true);
        
        //Verifica se o jogador é válido
        if(verificarJogadorVálido(num) == false) 
            escolherJogador(); //Se não for chama o próprio método para fazer a escolha de novo
        else //Mensagem mostrando seu jogador, caso o jogador for válido
            JOptionPane.showMessageDialog(null, "Você é o jogador: " + num + " \nQue os jogos começem!", "Início", JOptionPane.PLAIN_MESSAGE);
        
        return num; //Retorna o número para ser usada depois para determinar com quem a máquina vai jogar
    }//Fim do método
    
    public boolean verificarJogadorVálido(int num){
        if(num < 1 || num > 2){ //Verifica se o jogador é 1 ou 2
            JOptionPane.showMessageDialog(null, "Número de jogador inválido,\nEscolha entre o jogador 1 ou 2.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false; //Se não for retorna false
        }
        return true; //Se for retorna true
    }//Fim do método
    
    public boolean verificarSeGanhouOuPerdeu(){
        //Verifica as linhas
        if(contadorDeLinhas() == 'X'){
            JOptionPane.showMessageDialog(null, "Parabéns jogador 1, você ganhou!(Lin)", "resultado", JOptionPane.PLAIN_MESSAGE);
            return true;
        }
        if(contadorDeLinhas() == 'O'){
            JOptionPane.showMessageDialog(null, "Parabéns jogador 2, você ganhou!(Lin)", "resultado", JOptionPane.PLAIN_MESSAGE);
            return true;
        }
        //Verifica as Colunas
        if(contadorDeColunas()== 'X'){
            JOptionPane.showMessageDialog(null, "Parabéns jogador 1, você ganhou!(Col)", "resultado", JOptionPane.PLAIN_MESSAGE);
            return true;
        }
        if(contadorDeColunas()== 'O'){
            JOptionPane.showMessageDialog(null, "Parabéns jogador 2, você ganhou!(Col)", "resultado", JOptionPane.PLAIN_MESSAGE);
            return true;
        }
        //Verifica as diagonais primárias
        if(contadorDeDiagonalPrimária()== 'X'){
            JOptionPane.showMessageDialog(null, "Parabéns jogador 1, você ganhou!(DiagPri)", "resultado", JOptionPane.PLAIN_MESSAGE);
            return true;
        }
        if(contadorDeDiagonalPrimária()== 'O'){
            JOptionPane.showMessageDialog(null, "Parabéns jogador 2, você ganhou!(DiagPri)", "resultado", JOptionPane.PLAIN_MESSAGE);
            return true;
        }
        //Verifica as diagonais secundárias
        if(contadorDeDiagonalSecundária()== 'X'){
            JOptionPane.showMessageDialog(null, "Parabéns jogador 1, você ganhou!(DiagSec)", "resultado", JOptionPane.PLAIN_MESSAGE);
            return true;
        }
        if(contadorDeDiagonalSecundária()== 'O'){
            JOptionPane.showMessageDialog(null, "Parabéns jogador 2, você ganhou!(DiagSec)", "resultado", JOptionPane.PLAIN_MESSAGE);
            return true;
        }
        //Verifica empate
        if(verificarEmpate() == true){
            JOptionPane.showMessageDialog(null, "Jogo empatado", "resultado", JOptionPane.PLAIN_MESSAGE);
            return true;
        }
        return false; //Se não ninguem ganhar ou empatar retorna false
    }//Fim do método
    
    public char contadorDeLinhas(){
        //Variáveis de contagem dos Xs e dos Os no mapa
        int countXLin = 0, //Contagem de X em linhas
            countOLin = 0, //Contagem de O em linhas
            countXLinUlt = 0, //Contagem de X na última linha
            countOLinUlt = 0; //Contagem de O na última linha
        
        for(int x = 0; x < vet.length; x++){ //Laço X
            
            if(countXLin == 3) //Verificar se o X ganhou
               return 'X'; 
            else
                countXLin = 0; //Se não o contador reseta
            if(countXLinUlt == 3) //Verificar se o X ganhou na última linha
                return 'X';
            else
                countXLinUlt = 0; //Se não o contador reseta
            
            if(countOLin == 3) //Verificar se o O ganhou
               return 'O'; 
            else
               countOLin = 0; //Se não o contador reseta
            if(countOLinUlt == 3) //Verificar se o X ganhou última linha
                return 'O';
            else
                countOLinUlt = 0; //Se não o contador reseta
            
            for(int y = 0; y < vet.length; y++){ //Laço Y
                
                if(vet[x][y] == 'X') //Verifica se tem Xs nas linhas
                    countXLin++;
                if(vet[2][y] == 'X')
                    countXLinUlt++;
                
                if(vet[x][y] == 'O') //Verifica se tem Os nas linhas
                    countOLin++;
                if(vet[2][y] == 'O')
                    countOLinUlt++;
            }//Fim do laço Y
        }//Fim do laço X
        return '0'; //Se ninguém ganhar retorna 0
    }//Fim do método
    
    public char contadorDeColunas(){
        //Variáveis de contagem dos Xs e dos Os no mapa
        int countXCol = 0, //Contagem de X em Colunas
            countOCol = 0, //Contagem de O em Colunas
            countXColUlt = 0, //Contagem de X na última coluna
            countOColUlt = 0; //Contagem de O na última coluna
        
        for(int x = 0; x < vet.length; x++){ //Laço X
            
            if(countXCol == 3) //Verificar se o X ganhou
               return 'X'; 
            else
                countXCol = 0; //Se não o contador reseta
            if(countXColUlt == 3) //Verificar se o X ganhou na última coluna
                return 'X';
            else
                countXColUlt = 0; //Se não o contador reseta
            
            if(countOCol == 3) //Verificar se o O ganhou
               return 'O'; 
            else
                countOCol = 0; //Se não o contador reseta
            if(countOColUlt == 3) //Verificar se o O ganhou na última coluna
                return 'O';
            else
                countXColUlt = 0; //Se não o contador reseta
            
            for(int y = 0; y < vet.length; y++){//Laço Y
                
                if(vet[y][x] == 'X') //Contagem dos Xs do jogo de cada coluna
                    countXCol++;
                if(vet[y][2] == 'X')
                    countXColUlt++;
                
                if(vet[y][x] == 'O') //Contagem dos Os do jogo de cada coluna
                    countOCol++;
                if(vet[y][2] == 'O')
                    countOColUlt++;
            }//Fim do laço Y
        }//Fim do laço X
        return '0'; //Se ninguém ganhar retorna 0
    }//Fim do método
    
    public char contadorDeDiagonalPrimária(){
        //Variáveis de contagem dos Xs e dos Os no mapa
        int countXDiagPri = 0, //Contagem de X em diagonal primário
            countODiagPri = 0; //Contagem de O em diagonal primário
        
        for(int x = 0; x < vet.length; x++){ //Laço X
            if(vet[x][x] == 'X') //Contagem dos Xs do jogo da diagonal primária
                countXDiagPri++;
            
            if(vet[x][x] == 'O') //Contagem dos Os do jogo da diagonal primária
               countODiagPri++;
        }//Fim do laço X
        
        if(countXDiagPri == 3) //Verificar se o X ganhou
            return 'X';     
        if(countODiagPri == 3) //Verificar se o O ganhou
            return 'O'; 
        
        return '0'; //Se ninguém ganhar retorna 0
    }//Fim do método
    
    public char contadorDeDiagonalSecundária(){
        //Variáveis de contagem dos Xs e dos Os no mapa
        int countXDiagSec = 0, //Contagem de X em diagonal secundário
            countODiagSec = 0; //Contagem de O em diagonal secundário
        
        for(int x = 0; x < vet.length; x++){ //Laço X
            if(vet[x][vet.length - x - 1] == 'X') //Contagem dos Xs do jogo da diagonal primária
                countXDiagSec++;
            
            if(vet[x][vet.length - x - 1] == 'O') //Contagem dos Os do jogo da diagonal primária
                countODiagSec++;
        }//Fim do laço X
        
        if(countXDiagSec == 3) //Verificar se o X ganhou
               return 'X'; 
        if(countODiagSec == 3) //Verificar se o O ganhou
            return 'O'; 
        
        return '0'; //Se ninguém ganhar retorna 0
    }//Fim do método
    
    public boolean verificarEmpate(){
        int count = 0; //Contagem de casa livres no mapa
        
        for(int x = 0; x < vet.length; x++){//Laço X
            for(int y = 0; y < vet.length; y++){//Laço Y
                if(vet[x][y] != '-') //Se as casas estiverem livres o jogo não é empatado e a contagem aumenta
                    count++;
            }//Fim do laço Y
        }//Fim do laço X
        
        return (count == 9); //Se a contagem for maior que 9 retorna True, se for menor de 0 retorna False
    }//Fim do método
    
    public boolean verificarJogadaInvalida(){
        //Verifica se a casa esta vazia antes de jogar
        if(vet[y][x] != '-'){
            JOptionPane.showMessageDialog(null, "Jogada inválida,\n Faça sua jogada numa casa vazia", "Status de jogada", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false; //Se não retorna FALSE
    }//Fim do método
    
    public boolean verificarJogadaInvalidaMaquina(){
        //Verifica se a casa esta vazia antes de jogar
        if(vet[y][x] != '-'){
            return true;
        }
        return false; //Se não retorna FALSE
    }//Fim do método
    
    public void mapaDeJogo(){
        System.out.println("y  0    1    2  x"); //Imprime as coordenadas
        //Laços para imprimir o mapa do jogo
        for(int x = 0; x < vet.length; x++){//Laço X
            //Adiciona o número das colunas
            System.out.print(x + "  ");
            for(int y = 0; y < vet.length; y++){//Laço Y
                
                System.out.print(vet[x][y] + "    "); //Imprime a matriz
            }//Fim do laço Y
            
            System.out.println(""); //Espaço em branco entre colunas da matriz
        }//Fim do laço X
    }//Fim do método
    
    public void jogadaDoJogador1(){
        System.out.println("Vez do Jogador 1");
        System.out.println("Escolha as coordenadas X para jogar");
        x = scan.nextInt();
        System.out.println("Agora as coordenadas Y para jogar");
        y = scan.nextInt();
        System.out.println("");
            
        if(verificarJogadaInvalida() != true)
            vet[y][x] = 'X';
        else
            jogadaDoJogador1();
    }//Fim do método
    
    public void jogadaDoJogador2(){
        System.out.println("Vez do Jogador 2");
        System.out.println("Escolha as coordenadas X para jogar");
        x = scan.nextInt();
        System.out.println("Agora as coordenadas Y para jogar");
        y = scan.nextInt();
        System.out.println("");
        
        if(verificarJogadaInvalida() != true)
            vet[y][x] = 'O';
        else
            jogadaDoJogador2();
    }//Fim do método
    
    public void jogadaDaMaquina(char ch){
        System.out.println("\nVez da Máquina");
        x = randomize.nextInt(3); //Randomiza onde a máquina irá jogar
        y = randomize.nextInt(3); //Randomiza onde a máquina irá jogar
        
        if(verificarJogadaInvalidaMaquina() != true)
            vet[y][x] = ch;
        else
            jogadaDaMaquina(ch);
    }//Fim do método
    
    public char escolhaDaMaquina(int num){
        if(num == 1) //Determina a escolha da máquina com base na escolha do jogador
            return 'O';
        else
            return 'X';
    }//Fim do método
    
    public void fimDeJogo(){
        System.exit(0); //Termina o jogo e o jogo é fechado
    }//Fim do método
}//Fim da classe