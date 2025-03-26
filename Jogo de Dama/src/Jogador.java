public class Jogador {
    private int id = 0;
    private String name = "";


    protected void mudarPlayer(){
        if (getId() == 0){
            setId(1);
        }else{
            setId(0);
        }
    }

    protected int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}