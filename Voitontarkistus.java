package oliot_projekti;

import java.awt.Color;
import java.util.*;
import javax.swing.*;
public class VoitonTarkistus {
    //Kirjoittaa palautettavan tekstipätkän josta selviää voiton tyyppi ja voittavat kortit
    public static String LukitseVoitot(ArrayList<Kortti> kasi){
        //tehdään kaksiuloitteinen taulukko johon korttien arvot saa helposti luettavaksi sisälle
        Integer[][] kortit = new Integer[5][3];
        
        //isketään taulukkoon korttien arvot, maat ja niiden sijainti
        int i = 0;
        for(Kortti loopkortti : kasi){
            kortit[i][0] = loopkortti.getiKortinArvo();
            kortit[i][1] = loopkortti.getiKortinMaa();
            kortit[i][2] = i;
            i++;
        }
        //järjestetään kortit arvo järjestykseen pienimmästä suurimpaan. joudumme muuttamaan miten .sort vertailee arvoja.
        Arrays.sort(kortit, new Comparator<Integer[]>() {
            @Override
            //arguments to this method represent the arrays to be sorted   
            public int compare(Integer[] o1, Integer[] o2) {
                    //get the item ids which are at index 0 of the array
                    Integer arvoyksi = o1[0];
                    Integer arvokaksi = o2[0];
                    // sort on item id
                    return arvoyksi.compareTo(arvokaksi);
            }
        });
        //Katsomme korkeimman voiton mitä kädellä saa
        if(viitoset(kortit)){
            return "801234";
        }
        if(varisuora(kortit)){
            return "701234";
        }
        String neloset = neloset(kortit);
        if(!neloset.equals("0")){
            neloset = "6" + voittokortit(neloset, kortit);
            return neloset;
        }
        if(tayskasi(kortit)){
            return "501234";
        }
        if(vari(kortit)){
            return "401234";
        }
        if(suora(kortit)){
            return "301234";
        }
        String kolmoset = kolmoset(kortit);
        if(!kolmoset.equals("0")){
            kolmoset = "2" + voittokortit(kolmoset, kortit);
            return kolmoset;
        }
        String kaksiparia = kaksiparia(kortit);
        if(!kaksiparia.equals("0")){
            kaksiparia = "1" + voittokortit(kaksiparia, kortit);
            return kaksiparia;
        }
        return "0";
    }

/* -----------------------------------------------------------------
   -------------------------Metodit---------------------------------
   -----------------------------------------------------------------
    
   Metodit katsovat onko taulukon korteissa kyseistä kättä. Kaksi paria, kolmoset ja neloset
   palauttaa Stringin joka sisältää käden luovien korttien paikan taulukossa tai nollan jos kättä ei ollut.
   muut metodit palauttavat arvon true jos käsi löytyi ja false jos ei löytynyt.*/
    
    private static String kaksiparia(Integer[][] kortit){
        // tarkistetaan onko jokeria
        if(kortit[0][0] == 0){
            // katsotaan löytyykö paria
            for(int i = 1;i <= 3;i++){
                if(kortit[i][0].intValue() == kortit[i+1][0]){
                    //palautetaan niiden korttien paikat. jokerin pari valitaan mielivaltaisesti.
                    if(i == 1){
                        return "11101";
                    }
                    else{
                        return "10111";
                    }
                }
            }
            return "0";
        }
        //jos ei ole jokeria, katsomme onko kaksi ensimmäistä korttia pari
        if(kortit[0][0].intValue() == kortit[1][0]){
            //jos on, katsomme onko toista paria
            if(kortit[2][0].intValue() == kortit[3][0]){
                return "11110";
            }
            else if(kortit[3][0].intValue() == kortit[4][0]){
                return "11011";
            }
        }
        //muuten katsomme löytyykö kaksiparia taulukon pohjalta
        else if(kortit[1][0].intValue() == kortit[2][0] && kortit[3][0].intValue() == kortit[4][0]){
            return "01111";
        }
        return "0";
    }
    
    private static String kolmoset(Integer[][] kortit){
        //katsomme onko jokeria
        if(kortit[0][0] == 0){
            //jos jokeri on etsimme onko taulukossa paria
            for(int i = 1;i <= 3;i++){
                if(kortit[i][0].intValue() == kortit[i+1][0]){
                    switch(i){
                        case 1: return "11100";
                        case 2: return "10110";
                        case 3: return "10011";
                    }
                }
            }
            return "0";
        }
        //muuten etsimme onko taulukossa kolmea peräkkäistä korttia joiden arvot ovat samat
        for(int i = 0;i <= 2;i++){
            if(kortit[i][0].intValue() == kortit[i+1][0] && kortit[i][0].intValue() == kortit[i+2][0]){
                switch(i){
                    case 0: return "11100";
                    case 1: return "01110";
                    case 2: return "00111";
                }
            }
        }
        return "0";
    }
    
    private static boolean suora(Integer[][] kortit){
        //suoran tarkastus tarkistaa onko pienin ja suurin kortti neljän päässä toisistaan
        if(kortit[0][0] == 0){
            //jokerin kanssa pieni ja suurin saa olla myös vain kolmen päässä toisistaan
            if(kortit[1][0] == (kortit[4][0] - 4) || kortit[1][0] == (kortit[4][0] - 3) || kortit[1][0] == 1 && kortit[2][0] >= 10){
                for(int i = 1; i <= 3;i++){
                    //tässä katsomme onko taulukon korteissa samoja arvoja
                    if(kortit[i][0].intValue() == kortit[i+1][0]){
                        //jos on, se tarkoittaa ettei suoraa löydy
                        return false;
                    }
                }
                return true;
            }
            else{
                return false;
            }
        }
        //teemme samat toimenpiteet ilman jokeria
        if(kortit[0][0] == kortit[4][0] - 4 || kortit[0][0] == 1 && kortit[1][0] == 10){
            for(int i = 0;i <= 3;i++){
                if(kortit[i][0].intValue() == kortit[i+1][0]){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private static boolean vari(Integer[][] kortit){
        if(kortit[0][0] == 0){
            //jos jokeri löytyy tarkistetaan onko muut kortit samaa maata
            return kortit[1][1].intValue() == kortit[2][1].intValue() && kortit[1][1].intValue() == kortit[3][1].intValue() && kortit[1][1].intValue() == kortit[4][1].intValue();
        }
        //tarkistetaan onko kaikkien korttien maat samat
        return kortit[0][1].intValue() == kortit[1][1].intValue() && kortit[0][1].intValue() == kortit[2][1].intValue() && kortit[0][1].intValue() == kortit[3][1].intValue() && kortit[0][1].intValue() == kortit[4][1].intValue();
    }
    
    private static boolean tayskasi(Integer[][] kortit){
        if(kortit[0][0] == 0){
            for(int i = 1;i <= 2;i++){
                //jo jokeri löytyy tarkistamme onko taulukossa kolme saman arvoista korttia
                if(kortit[i][0].intValue() == kortit[i+1][0] && kortit[i][0].intValue() == kortit[i+2][0]){
                    return true;
                }
            //jos taulukossa
            }
            return kortit[1][0].intValue() == kortit[2][0] && kortit[3][0].intValue() == kortit[4][0];
        }
        //tarkistetaan onko taulukon kolme ensimmäistä ja kaksi viimeistä korttia saman arvosia tai onko kaksi ensimmäistä ja kolme viimeistä samanarvoisia
        return kortit[0][0].intValue() == kortit[1][0] && kortit[1][0].intValue() == kortit[2][0] && kortit[3][0].intValue() == kortit[4][0] || kortit[0][0].intValue() == kortit[1][0] && kortit[2][0].intValue() == kortit[3][0] && kortit[3][0].intValue() == kortit[4][0];    
    }
    
    private static String neloset(Integer[][] kortit){
        if(kortit[0][0] == 0){
            //jos jokeri löytyy etsitään onko taulukossa kolme saman arvoista korttia
            for(int i = 1; i <= 2;i++){
                if(kortit[i][0].intValue() == kortit[i+1][0] && kortit[i][0].intValue() == kortit[i+2][0]){
                    if(i == 1){
                        return "11110";
                    }
                    else{
                        return "10111";
                    }
                }
            }
            return "0";
        }
        //etsitään onko taulukossa neljää peräkkäistä korttia joiden arvot ovat sama
        for(int i = 0; i <= 1;i++){
            if(kortit[i][0].intValue() == kortit[i+1][0] && kortit[i][0].intValue() == kortit[i+2][0] && kortit[i][0].intValue() == kortit[i+3][0]){
                if(i == 0){
                    return "11110";
                }
                else{
                    return "01111";
                }
            }
        }
        return "0";
        
        
    }
    
    private static boolean varisuora(Integer[][] kortit){
        //värisuoran ja suoran logiikka on sama
        if(kortit[0][0] == 0){
            if(kortit[1][0] == (kortit[4][0] - 4) || kortit[1][0] == (kortit[4][0] - 3) || kortit[1][0] == 1 && kortit[2][0] >= 10){
                for(int i = 1; i <= 3;i++){
                    //mutta tarkastamme täällä myös ettei korttien seasta löydy eri maisia kortteja
                    if(kortit[i][0].intValue() == kortit[i+1][0] || kortit[i][1].intValue() != kortit[i+1][1]){
                        return false;
                    }
                }
                return true;
            }
            else{
                return false;
            }
        }
        if(kortit[0][0] == kortit[4][0] - 4 || kortit[0][0] == 1 && kortit[1][0] == 10){
            for(int i = 0;i <= 3;i++){
                if(kortit[i][0].intValue() == kortit[i+1][0] || kortit[i][1].intValue() != kortit[i+1][1]){
                    return false;
                }
            }
            return true;
        }
        return false;
    
    }
    
    private static boolean viitoset(Integer[][] kortit){
        //tarkistamme onko ensimmäinen kortti jokeri ja muut kortit saman arvoisia keskenään
        return kortit[0][0] == 0 && kortit[1][0].intValue() == kortit[2][0].intValue() && kortit[1][0].intValue() == kortit[3][0].intValue() && kortit[1][0].intValue() == kortit[4][0].intValue();
    }
    
    private static String voittokortit(String kortit2, Integer[][] kortit){
        //otetaan voittokorttien sijainti ylös
        String vkortit = "";
        for(int index = 0;index < kortit2.length();index++){
            int arvo = Character.getNumericValue(kortit2.charAt(index));
            if(arvo == 1){
                //lukitsemisen voi hoitaa myös täällä, silloin metodi ei palauta mitään
                vkortit = vkortit + kortit[index][2];
            }
        }
        return vkortit;
    }
    
    //Pelataan tuplaus kierros ja palautetaan tuplauksen tulos
    //2 = Voitto, 1 = Tasapeli, 0 = Häviö
    public static int Tuplaus(int iTuplausValinta, ArrayList<Kortti> kortitPakka, ArrayList<Kortti> kortitKasi) {
        //Nosta kortti ensimmäiseen positioon
        KorttiKasittely.NostaKortti(kortitPakka, kortitKasi, 0);
        
        int iKorttiArvo = kortitKasi.get(0).getiKortinArvo();
        int iKorttiMaa = kortitKasi.get(0).getiKortinMaa();
        
        //iTuplausValinta
            //0 = [PIENI] painiketta painettu
            //1 = [SUURI] painiketta painettu
        
        //Jokeri voittaa aina
        if (iKorttiArvo == 0) {
            //2 = Voitto
            return 2;
        //Voitto jos painettu [PIENI] painiketta
        } else if (iTuplausValinta == 0 && iKorttiArvo < 7) {
            //2 = Voitto
            return 2;
        //Voitto jos painettu [SUURI] painiketta
        } else if (iTuplausValinta == 1 && iKorttiArvo > 7) {
            //2 = Voitto
            return 2;
        //Tasapeli jos punainen 7
        } else if (iKorttiArvo == 7 && iKorttiMaa <= 2) {
            //1 = tasapeli
            return 1;
        //Kortin koko on väärä tai musta seitsemän
        } else {
            //0 = häviö
            return 0;
        }
    }
    
    /*
    ----------------------------------------------------------------------------
    --------------------Potentiaaliset voitot-----------------------------------
    ----------------------------------------------------------------------------
    */
    
    public static void Potentiaaliset(ArrayList<Kortti> kasi){
        String voitot = "";
        int korttienmaara = 0;
        for(int i = 0;i <= 4;i++){
            if(KorttiKasittely.OnkoKorttiLukittu(i)){
                if(kasi.get(i).getiKortinArvo() != 0){
                    korttienmaara++;
                }
            }
        }
        Integer[][] kortit = new Integer[korttienmaara][2];
        int o = 0;
        for(int i = 0;i <= 4;i++){
            if(KorttiKasittely.OnkoKorttiLukittu(i)){
                if(kasi.get(i).getiKortinArvo() != 0){
                    kortit[o][0] = kasi.get(i).getiKortinArvo();
                    kortit[o][1] = kasi.get(i).getiKortinMaa();
                    o++;
                }
            }
        }
        if(korttienmaara > 1){
            Arrays.sort(kortit, new Comparator<Integer[]>() {
                @Override
                //arguments to this method represent the arrays to be sorted   
                public int compare(Integer[] o1, Integer[] o2) {
                        //get the item ids which are at index 0 of the array
                        Integer arvoyksi = o1[0];
                        Integer arvokaksi = o2[0];
                        // sort on item id
                        return arvoyksi.compareTo(arvokaksi);
            }
            });
        }
        if(korttienmaara <= 1){
            voitot = "12345678";
            labelVari(voitot);
        }
        else if(korttienmaara == 2){
            voitot = voitot + "1256";
            voitot = voitot + varijasuora(kortit, korttienmaara);
            if(vitoset(kortit,korttienmaara)){
                voitot = voitot + "8";
            }
            labelVari(voitot);
        }
        else if(korttienmaara == 3){
            voitot = voitot + "12" + varijasuora(kortit, korttienmaara);
            if(onkopari(kortit,korttienmaara)){
                voitot = voitot + "56";
            }
            if(vitoset(kortit,korttienmaara)){
                voitot = voitot + "8";
            }
            labelVari(voitot);
        }
        else if(korttienmaara == 4){
            if(onkopari(kortit, korttienmaara)){
                voitot = voitot + "12";
            }
            voitot = voitot + varijasuora(kortit, korttienmaara);
            if(tays4kasi(kortit, korttienmaara)){
                voitot = voitot + "5";
            }
            if(neloset4(kortit, korttienmaara)){
                voitot = voitot + "6";
            }
            if(vitoset(kortit, korttienmaara)){
                voitot = voitot + "8";
            }
            labelVari(voitot);
            
        }
        else if(korttienmaara == 5){
            voitot = LukitseVoitot(kasi);
            voitot = Character.toString(voitot.charAt(0));
            labelVari(voitot);
        }
    }
    
    /*
    ----------------------------------------------------------------------------
    ----------------Potentiaalisten voittojen metodit---------------------------
    ----------------------------------------------------------------------------
    */
    
    private static boolean vitoset(Integer[][] kortit,int korttienmaara){
        for(int i = 0;i < (korttienmaara - 1);i++){
            if(!kortit[i][0].equals(kortit[i+1][0])){
                return false;
            }
        }
        return true;
    }
    
    private static String varijasuora(Integer[][] kortit,int korttienmaara){
        String voitot = "";
        boolean vari = true;
        boolean suora = false;
        for(int i = 0;i < (korttienmaara - 1);i++){
            if(!kortit[i][1].equals(kortit[i+1][1])){
                vari = false;
                break;
            }
        }
        if(kortit[korttienmaara - 1][0] - kortit[0][0] < 5 || kortit[0][0] == 1 && (14 - kortit[1][0]) < 5){
            suora = !onkopari(kortit,korttienmaara);
        }
        if(suora){
            voitot = voitot + "3";
        }
        if(vari){
            voitot = voitot + "4";
        }
        if(vari && suora){
            voitot = voitot + "7";
        }
        return voitot;
    }
    private static boolean onkopari(Integer[][] kortit,int korttienmaara){
        for(int i = 0;i < (korttienmaara - 1);i++){
            if(kortit[i][0].equals(kortit[i+1][0])){
                return true;
            }
        }
        return false;
    }
    private static boolean tays4kasi(Integer[][] kortit,int korttienmaara){
        for(int i = 0;i < korttienmaara - 2;i++){
            if(kortit[i][0].intValue() == kortit[i+1][0] && kortit[i][0].intValue() == kortit[i+2][0]){
                return true;
            }
        }
        return kortit[1][0].intValue() == kortit[2][0] && kortit[3][0].intValue() == kortit[4][0];
    }
    private static boolean neloset4(Integer[][] kortit,int korttienmaara){
        for(int i = 0;i < korttienmaara - 2;i++){
            if(kortit[i][0].intValue() == kortit[i+1][0] && kortit[i][0].intValue() == kortit[i+2][0]){
                return true;
            }
        }
        return false;
    }
    
    public static void labelVari(String kadet){
        ArrayList<JLabel> nimet = new ArrayList<>();
        nimet.add(Gui_Main.jLabel8);
        nimet.add(Gui_Main.jLabel9);
        nimet.add(Gui_Main.jLabel10);
        nimet.add(Gui_Main.jLabel11);
        nimet.add(Gui_Main.jLabel12);
        nimet.add(Gui_Main.jLabel13);
        nimet.add(Gui_Main.jLabel14);
        nimet.add(Gui_Main.jLabel15);
        
        for(JLabel label:nimet){
            label.setBackground(Color.LIGHT_GRAY);
        }
        for(int index = 0;index < kadet.length();index++){
            switch (kadet.charAt(index)){
                case '1': nimet.get(7).setBackground(Color.yellow);
                          break;
                case '2': nimet.get(6).setBackground(Color.yellow);
                          break;
                case '3': nimet.get(5).setBackground(Color.yellow);
                          break;
                case '4': nimet.get(4).setBackground(Color.yellow);
                          break;
                case '5': nimet.get(3).setBackground(Color.yellow);
                          break;
                case '6': nimet.get(2).setBackground(Color.yellow);
                          break;
                case '7': nimet.get(1).setBackground(Color.yellow);
                          break;
                case '8': nimet.get(0).setBackground(Color.yellow);
                          break;
            }
        }
    }
    
}
