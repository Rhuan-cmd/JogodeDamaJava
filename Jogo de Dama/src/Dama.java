import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dama extends Jogador{
    private LinkedList<List<String>> taboleiro = new LinkedList<>();
    private String player0 = "";
    private String player1 = "";
    private String peca0 = "";
    private String peca1 = "";
    private String ganhador = "";

    public Dama(Jogador player0, Jogador player1){
        setPlayer0(player0.getName());
        setPlayer1(player1.getName());
        setPeca0(setPecaID(0));
        setPeca1(setPecaID(1));
        if (getPeca0().equals(getPeca1())){setPeca0(getPeca0() + "1"); setPeca1(getPeca1() + "2");}
        taboleiro.addAll(List.of(
            new ArrayList<>(List.of("-", getPeca1(), "-", getPeca1(), "-", getPeca1(), "-", getPeca1())),
            new ArrayList<>(List.of(getPeca1(), "-", getPeca1(), "-", getPeca1(), "-", getPeca1(), "-")),
            new ArrayList<>(List.of("-", getPeca1(), "-", getPeca1(), "-", getPeca1(), "-", getPeca1())),
            new ArrayList<>(List.of("■", "-", "■", "-", "■", "-", "■", "-")),
            new ArrayList<>(List.of("-", "■", "-", "■", "-", "■", "-", "■")),
            new ArrayList<>(List.of(getPeca0(), "-", getPeca0(), "-", getPeca0(), "-", getPeca0(), "-")),
            new ArrayList<>(List.of("-", getPeca0(), "-", getPeca0(), "-", getPeca0(), "-", getPeca0())),
            new ArrayList<>(List.of(getPeca0(), "-", getPeca0(), "-", getPeca0(), "-", getPeca0(), "-"))
        ));
        
        atualizarDama();
    }

    public void jogada(int linhaSelec, int colunaSelec, int linhaDest, int colunaDest){
        linhaDest = 8 - linhaDest;
        colunaDest--;
        linhaSelec = 8 - linhaSelec;
        colunaSelec--;
        //System.out.println(linhaDest - linhaSelec);
        //System.out.println(colunaDest - colunaSelec);
        boolean pecaS = taboleiro.get(linhaSelec).get(colunaSelec).equals(super.getId() == 0 ? getPeca0() : getPeca1());
        boolean pecaD = taboleiro.get(linhaDest).get(colunaDest).equals(super.getId() == 0 ? getPeca1() : getPeca0());
        boolean pecaDD = taboleiro.get(linhaDest).get(colunaDest).equals(super.getId() == 0 ? setDamaStyle(getPeca1()) : setDamaStyle(getPeca0()));
        int casasLinha = (linhaDest - linhaSelec) == -1 ? (linhaDest - linhaSelec) * -1 : (linhaDest - linhaSelec);
        int casasColuna = (colunaDest - colunaSelec) == -1 ? (colunaDest - colunaSelec) * -1 : (colunaDest - colunaSelec);
        int casasLivresL = (linhaDest < linhaSelec ? (linhaDest - 1) : (linhaDest + 1));
        int casasLivresDD = (super.getId() == 0 ? (colunaDest + 1): (colunaDest - 1));
        int casasLivresDE = (super.getId() == 0 ? (colunaDest - 1): (colunaDest + 1));
        casasLivresL = Math.abs(casasLivresL);
        casasLivresDD = casasLivresDD >= 8 ? Math.abs(casasLivresDD - 1) : Math.abs(casasLivresDD);
        casasLivresDE = casasLivresDE < 0 ? Math.abs(casasLivresDE + 1) : Math.abs(casasLivresDE);
        if (casasLivresDE >= 8){casasLivresDE -= 1;}
        String stringCasa = taboleiro.get(casasLivresL).get(verificaColunaIr(colunaSelec, colunaDest, casasLivresDD, casasLivresDE));
        
        boolean podeMover = stringCasa.equals("■");

        boolean eDama = super.getId() == 0 ? taboleiro.get(linhaSelec).get(colunaSelec).equals(setDamaStyle(getPeca0())) : 
        taboleiro.get(linhaSelec).get(colunaSelec).equals(setDamaStyle(getPeca1()));

        boolean regrasTaboleiro = linhaDest < taboleiro.size() && linhaSelec < taboleiro.size() &&
        colunaDest < taboleiro.get(linhaDest).size() && colunaSelec < taboleiro.get(linhaSelec).size() &&
        ((taboleiro.get(linhaDest).get(colunaDest).equals("■") && pecaS) || (!pecaD && !pecaS));

        //System.out.println(pecaD);
        //System.out.println(pecaS);
        //System.out.println(podeMover);
        //System.out.println("linha "+casasLivresL);
        //System.out.println("coluna direita " +casasLivresDD);
        //System.out.println("coluna esquerda "+casasLivresDE);
        //System.out.println(stringCasa);
        //System.out.println(pecaS);
        //System.out.println(pecaD);

        if (regrasTaboleiro && eDama && Math.abs(casasLinha) >= 1 && Math.abs(casasLinha) <= 8 && Math.abs(casasColuna) >= 1 &&
        Math.abs(casasColuna) <= 8){

            int clnum = colunaSelec + 1;

            LinkedList<List<String>> copiaTaboleiro = new LinkedList<>();
            ArrayList<String> campos = new ArrayList<>();
            
            boolean liberado = true;

            for (List<String> linha : taboleiro){
                copiaTaboleiro.add(new ArrayList<>(linha));
            }

            for (int l = linhaSelec - 1; l > linhaDest; l--){
                if (!(taboleiro.get(l).get(clnum).equals(super.getId() == 0 ? getPeca0() : getPeca1()))){
                    copiaTaboleiro.get(l).set(clnum, "■");
                    campos.add(taboleiro.get(l).get(clnum));
                    clnum++;
                }else{
                    liberado = false;
                    break;
                }
            }

            for (int l = linhaSelec + 1; l < linhaDest; l++){
                if (!(taboleiro.get(l).get(clnum).equals(super.getId() == 0 ? getPeca0() : getPeca1()))){
                    copiaTaboleiro.get(l).set(clnum, "■");
                    campos.add(taboleiro.get(l).get(clnum));
                    clnum++;
                }else{
                    liberado = false;
                    break;
                }
            }

            if (liberado){
                for (int i = 0; i < campos.size() - 1; i++){
                    if (!(campos.get(i).equals(campos.get(i + 1))) || campos.get(i + 1).equals("■")){
                        liberado = true;
                    }else {
                        liberado = false;
                        break;
                    }
                }
            }

            
            if (liberado){
                taboleiro = new LinkedList<>();
                for (List<String> linha : copiaTaboleiro){
                    taboleiro.add(new ArrayList<>(linha));
                }
                taboleiro.get(linhaSelec).set(colunaSelec, "■");
                taboleiro.get(linhaDest).set(colunaDest, super.getId() == 0 ? setDamaStyle(getPeca0()): setDamaStyle(getPeca1()));

                super.mudarPlayer();
            }else{
                System.out.println("\n\n#####Não é possivel fazer esse movimento#####");
            }

        }else if(regrasTaboleiro && casasLinha == 1 && casasColuna == 1 && (super.getId() == 0 ?
        taboleiro.get(linhaSelec).get(colunaSelec).equals(getPeca0()) : 
        taboleiro.get(linhaSelec).get(colunaSelec).equals(getPeca1())) &&
        (super.getId() == 0 ? linhaDest < linhaSelec : linhaDest > linhaSelec)){

            taboleiro.get(linhaSelec).set(colunaSelec, "■");
            taboleiro.get(linhaDest).set(colunaDest, super.getId() == 0 ? getPeca0() : getPeca1());

            super.mudarPlayer();
        
        }else if (pecaS && podeMover && (pecaD || pecaDD)){
            taboleiro.get(linhaSelec).set(colunaSelec, "■");
            taboleiro.get(linhaDest).set(colunaDest, "■");
            taboleiro.get(casasLivresL).set(verificaColunaIr(colunaSelec, colunaDest, casasLivresDD, casasLivresDE),
            super.getId() == 0 ? getPeca0() : getPeca1());
            if (super.getId() == 0){
                if (colunaSelec < colunaDest){
                    if ((linhaDest - 1) == 0){
                        taboleiro.get(linhaDest - 1).set(colunaDest + 1, super.getId() == 0 ? setDamaStyle(getPeca0()): setDamaStyle(getPeca1()));
                    }
                }else{
                    if ((linhaDest - 1) == 0){
                        taboleiro.get(linhaDest - 1).set(colunaDest - 1, super.getId() == 0 ? setDamaStyle(getPeca0()): setDamaStyle(getPeca1()));
                    }
                }
            }else{
                if (colunaSelec > colunaDest){
                    if ((linhaDest + 1) == 7){
                        taboleiro.get(linhaDest + 1).set(colunaDest - 1, super.getId() == 0 ? setDamaStyle(getPeca0()): setDamaStyle(getPeca1()));
                    }
                }else{
                    if ((linhaDest + 1) == 7){
                        taboleiro.get(linhaDest + 1).set(colunaDest + 1, super.getId() == 0 ? setDamaStyle(getPeca0()): setDamaStyle(getPeca1()));
                    }
                }
            }
            super.mudarPlayer();

        }else{
            System.out.println("\n\n#####Não é possivel fazer esse movimento#####");
        }

        super.mudarPlayer();
        if (super.getId() == 0){
            if (linhaDest == 0){
                taboleiro.get(linhaDest).set(colunaDest, super.getId() == 0 ? setDamaStyle(getPeca0()): setDamaStyle(getPeca1()));
            }
        }else{
            if (linhaDest == 7){
                taboleiro.get(linhaDest).set(colunaDest, super.getId() == 0 ? setDamaStyle(getPeca0()): setDamaStyle(getPeca1()));
            }
        }
        super.mudarPlayer();

        atualizarDama();
        
    }

    private void atualizarDama(){
        int coordenada = 8;
        System.out.println("\n\n    Vez do Player " + getNomeID() + "\n");
        System.out.print(coordenada + "   ");

        for (int l = 0; l < taboleiro.size(); l++){
            for (int c = 0; c < taboleiro.get(l).size(); c++){
                System.out.print(taboleiro.get(l).get(c) + " ");
                if (c >= taboleiro.get(l).size() - 1){
                    System.out.println("");
                    if (coordenada > 1){
                        coordenada--;
                        System.out.print((coordenada) + "   ");
                    }
                }
            }
        }

        System.out.println("");
        System.out.print("    ");
        for (int i = 1; i < 9; i++){
            System.out.print(i + " ");
        }
    }

    private int verificaColunaIr(int colunaS, int colunaD, int casasD, int casasE){
        int ir = 0;

        if (super.getId() == 0){
            if (colunaS < colunaD){
                ir = casasD;
            }else{
                ir = casasE;
            }
        }else{
            if (colunaS > colunaD){
                ir = casasD;
            }else{
                ir = casasE;
            }
        }

        return ir;
    }

    public boolean ganhou(){
        boolean ganhar = false;
        int quantPecas0 = 0;
        int quantPecas1 = 0;
        for (int l = 0; l < taboleiro.size(); l++){
            for (int c = 0; c < taboleiro.get(l).size(); c++){
                if (taboleiro.get(l).get(c).equals(getPeca0()) || taboleiro.get(l).get(c).equals(setDamaStyle(getPeca0()))){
                    quantPecas0++;
                }
                if (taboleiro.get(l).get(c).equals(getPeca1()) || taboleiro.get(l).get(c).equals(setDamaStyle(getPeca1()))){
                    quantPecas1++;
                }
            }
        }
        if (quantPecas0 == 0){
            ganhar = true;
            setGanhador(getPlayer1());
        
        }else if (quantPecas1 == 0){
            ganhar = true;
            setGanhador(getPlayer0());
        
        }else{
            ganhar = false;
        }
        return ganhar;
    }

    private String getNomeID(){
        String nome = "";
        if (super.getId() == 0){
            nome = getPlayer0();
        }else{
            nome = getPlayer1();
        }
        return nome;
    }

    private String setPecaID(int id){
        String peca = "";
        if (id == 0){
            peca = String.valueOf(getPlayer0().charAt(0));
        }else{
            peca = String.valueOf(getPlayer1().charAt(0));
        }

        return peca;
    }

    private String setDamaStyle(String ico){
        return "\033[4m"+ico+"\033[0m";
    }

    private String getPlayer0() {
        return player0;
    }

    private void setPlayer0(String player0) {
        this.player0 = player0;
    }

    private String getPlayer1() {
        return player1;
    }

    private void setPlayer1(String player1) {
        this.player1 = player1;
    }

    private String getPeca0() {
        return peca0;
    }

    private void setPeca0(String peca0) {
        this.peca0 = peca0;
    }

    private String getPeca1() {
        return peca1;
    }

    private void setPeca1(String peca1) {
        this.peca1 = peca1;
    }

    public String getGanhador() {
        return ganhador;
    }

    private void setGanhador(String ganhador) {
        this.ganhador = ganhador;
    }

}