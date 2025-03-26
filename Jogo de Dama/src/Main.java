import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        
        Jogador[] jogador = new Jogador[2];
        jogador[0] = new Jogador();
        jogador[1] = new Jogador();
        System.out.print("Nome do Jogador 1: ");
        jogador[0].setName(input.next());
        System.out.print("Nome do Jogador 2: ");
        jogador[1].setName(input.next());
        Dama jogo = new Dama(jogador[0], jogador[1]);

        while (true) {
            System.out.print("\n\nSelecione a LINHA e COLUNA da peça desejada: ");
            String Selec = input.next();
            
            System.out.print("\n\nSelecione a LINHA e COLUNA de DESTINO da peça: ");
            String Dest = input.next();

            Selec = Selec.replaceAll("//s", "");
            Dest = Dest.replaceAll("//s", "");
            if (Selec.length() == 2 && Dest.length() == 2){
                int linhaS = Character.getNumericValue(Selec.charAt(0));
                int colunaS = Character.getNumericValue(Selec.charAt(1));
                int linhaD = Character.getNumericValue(Dest.charAt(0));
                int colunaD = Character.getNumericValue(Dest.charAt(1));
                jogo.jogada(linhaS, colunaS, linhaD, colunaD);
            }else{
                System.out.println("\nFomato de Escrita Errada! Utilize EX: 31");
            }

            if (jogo.ganhou()){
                System.out.println("\n\n-------------------------------------");
                System.out.println("\n\n     Ganhador: "+ jogo.getGanhador());
                System.out.println("\n\n-------------------------------------");
                input.close();
                break;
            }
        }
    }
}