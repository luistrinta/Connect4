public class Tabela {
    public static char[][] arr;
    //posição da ultima jogada
    public static int lastMoveX;
    public static int lastMoveY;
    public char winner;
    public char lastplayer;


    Tabela() {
        arr = new char[6][7];
        for(int j = 0; j < 6 ; j++) {
            for(int i = 0; i < 7 ; i++)arr[j][i] = '-';
        }
    }

    Tabela(Tabela t) {
        arr = t.arr;
        lastMoveY = t.lastMoveY;
        lastMoveX = t.lastMoveX;
        lastplayer = t.lastplayer;
        winner = t.winner;
    }

    static void printTabela(Tabela t) {
        for(int j = 0; j < 6 ; j++) {
            for(int i = 0; i < 7 ; i++)System.out.print(t.arr[j][i] + " ");
            System.out.println();
        }
    }

    static void play(Tabela t, char jogada, int y) {


        int x = 0;
        t.lastMoveY = y - 1 ;


        while(x < 6 && t.arr[x][y - 1] == '-') {
            System.out.println(x);
            x++;
        }
        t.lastMoveX = x - 1;
        t.arr[t.lastMoveX][t.lastMoveY] = jogada;
        t.lastplayer = jogada;

    }




    //Boolean functions
    static boolean checkWinners(Tabela t) {
        if(winsLine(t, t.lastMoveX, t.lastMoveY, t.lastplayer))return true;
        if(winsRow(t, t.lastMoveX, t.lastMoveY, t.lastplayer))return true;
        if(winsDiag(t, t.lastMoveX, t.lastMoveY, t.lastplayer))return true;


        return false;
    }

    static boolean winsDiag(Tabela t, int lastMoveX, int lastMoveY, char lastplayer) {
        int checkerTopRight=0,checkerTopLeft=0;

        //Diagonal top-right to bot-left
        for(int i = -3 ; i <= 3 /*maior diagonal possível*/; i++) {
            //verifica se está in bounds
            if(lastMoveX+i>=0 && lastMoveY-i>=0 && lastMoveX+i<6 && lastMoveY-i<7){
                //char da posição a verificar é igual ao último jogador
                if(t.arr[lastMoveX+i][lastMoveY-i]==lastplayer)checkerTopRight++;
                else checkerTopRight=0;
                //se o checker chegar a 4, quer dizer que são 4 em linha, ganha
                if(checkerTopRight==4){
                    t.winner = lastplayer;
                    return true;
                }
            }else checkerTopRight=0;


            //Diagonal top-left to bot-right
            if(lastMoveX+i>=0 && lastMoveY+i>=0 && lastMoveX+i<6 && lastMoveY+i<7){
                if(t.arr[lastMoveX+i][lastMoveY+i]==lastplayer)checkerTopLeft++;
                else checkerTopLeft=0;
                if(checkerTopLeft==4){
                    t.winner = lastplayer;
                    return true;
                }
            }else checkerTopLeft=0;
        }
        return false;
    }
    static boolean winsRow(Tabela t, int lastMoveX, int lastMoveY, char lastplayer) {
        for(int i = 0 ; i <= 2 ; i++) {
            if(t.arr[i][lastMoveY] == lastplayer && t.arr[i + 1][lastMoveY] == lastplayer && t.arr[i + 2][lastMoveY] == lastplayer && t.arr[i + 3][lastMoveY] == lastplayer) {
                t.winner = lastplayer;
                return true;
            }
        }
        return false;
    }


    static boolean winsLine(Tabela t, int lastMoveX, int lastMoveY, char lastplayer) {
        for(int i = 0 ; i <= 3 ; i++) {
            if((t.arr[lastMoveX][i] == lastplayer) && (t.arr[lastMoveX][i + 1] == lastplayer) && (t.arr[lastMoveX][i + 2] == lastplayer) && (t.arr[lastMoveX][i + 3] == lastplayer)) {
                t.winner = lastplayer;
                return true;
            }
        }
        return false;
    }





    static boolean isCompleted(Tabela t) {
        for (int i = 0; i < 7 ; i++ ) {
            if(t.arr[0][i] == '-')return false;

        }
        return true;
    }



}
