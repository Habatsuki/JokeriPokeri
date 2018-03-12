package oliot_projekti;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

//Korttien käsittely (Funktiot)
public class KorttiKasittely {
    
    //Luo korttioliot suoraan korttipakkaan
    public static void LuoKorttipakka(ArrayList<Kortti> kortitPakka) {
        int iKorttiIndex = 0;        
        //For kortin maat (1-4)
        for (int iIndexMaat = 1; iIndexMaat <= 4; iIndexMaat++) {
            //For kortin arvot (1-13)
            for (int iIndexArvo = 1; iIndexArvo <= 13; iIndexArvo++) {
                //Luodaan 52 peruskortti oliota suoraan korttipakka arrayhin
                kortitPakka.add(iKorttiIndex, new Kortti(iKorttiIndex, iIndexArvo, iIndexMaat, false));
                iKorttiIndex++; //Lisätään kortit järjestyksessä indexin mukaisesti
            }
        }
        //Luodaan jokeri kortti
        kortitPakka.add(iKorttiIndex, new Kortti(iKorttiIndex, 0, 0, true));
    }
    
    //Nosta kortti haluttuun positioon käsikortteihin
    public static void NostaKortti(ArrayList<Kortti> kortitPakka, ArrayList<Kortti> kortitKasi, int iKorttiIndex) {    
        int iSatunnainenKortti;
        //Valitaan pakasta satunnainen indeksi
        iSatunnainenKortti = (int) (Math.random() * kortitPakka.size());
        //Siirrä nostettu kortti käsikortteihin
        kortitKasi.add(iKorttiIndex, kortitPakka.get(iSatunnainenKortti));
        //Aseta käsikortin kuva
        KorttiKasittely.AsetaKasiKorttiKuva(iKorttiIndex, kortitPakka.get(iSatunnainenKortti));
        //Poista kortti pakasta
        kortitPakka.remove(iSatunnainenKortti);
    }
    
    //Nostetaan kortteja kunnes käsikorteissa on viisi korttia
    public static void NostaKasiTayteenKortteja(ArrayList<Kortti> kortitPakka, ArrayList<Kortti> kortitKasi) {
        int iKorttiIndex = 0;
        while (kortitKasi.size() < 5) {
            //Tämä sen vuoksi että jäljelle jäävät kortit eivät siirry
            KorttiKasittely.NostaKortti(kortitPakka, kortitKasi, iKorttiIndex);
            //Aseta kortin kuva
            KorttiKasittely.AsetaKasiKorttiKuva(iKorttiIndex, kortitKasi.get(iKorttiIndex));
            iKorttiIndex++;
        }
    }
    
    //Korttien sekoitus takaisin pakkaan
    public static void SiirraKaikkiKortitPakkaan(ArrayList<Kortti> kortitPakka, ArrayList<Kortti> kortitKasi, ArrayList<Kortti> kortitMuu) {
        //Siirretään käsikortit pakkaan
        while (kortitKasi.size() > 0) {
            //Siirrä käsikortti pakkaan
            kortitPakka.add(kortitKasi.get(0));
            //Poista kortti käsikorteista
            kortitKasi.remove(0);
        }
        //Siirretään muut kortit pakkaan
        while (kortitMuu.size() > 0) {
            //Siirrä muu kortti pakkaan
            kortitPakka.add(kortitMuu.get(0));
            //Poista kortti muu korteista
            kortitMuu.remove(0);
        }
    }

//KOODISSA ONGELMIA JOTEN HOIDAN SEN MUUALLA MANUAALISESTI
//    public static void AsetaKasiKorttiTyhja(int iKorttiIndex) {
//        javax.swing.JLabel targetLabel;
//            
//        switch (iKorttiIndex) {
//            case 4:
//                targetLabel = Gui_Main.jLabel5;
//                break;
//            case 3:
//                targetLabel = Gui_Main.jLabel4;
//                break;
//            case 2:
//                targetLabel = Gui_Main.jLabel3;
//                break;
//            case 1:
//                targetLabel = Gui_Main.jLabel2;
//                break;
//            default:
//                targetLabel = Gui_Main.jLabel1;
//                break;
//        }
//        //Aseta kortin kuva tyhjäksi
//        Image im = new ImageIcon("/Kuvat/cardback.png").getImage();
//        
//        ImageIcon kortinKuva = new ImageIcon(im);
//        
//        targetLabel.setIcon(kortinKuva);
//    }
    
    public static void AsetaKasiKorttiKuva(int iKorttiIndex, Kortti tempKortti) {
        javax.swing.JLabel targetLabel;
        
        switch (iKorttiIndex) {
            case 4:
                targetLabel = Gui_Main.jLabel5;
                break;
            case 3:
                targetLabel = Gui_Main.jLabel4;
                break;
            case 2:
                targetLabel = Gui_Main.jLabel3;
                break;
            case 1:
                targetLabel = Gui_Main.jLabel2;
                break;
            default:
                targetLabel = Gui_Main.jLabel1;
                break;
        }
        
        //Mikä kortin tiedostonimi on?
        String sKuvannimi = tempKortti.getKorttiTiedostoNimi();
        
        //targetLabelissä on se haluttu label
        Image im=new ImageIcon(tempKortti.getClass().getResource("/Kuvat/"+sKuvannimi+".png")).getImage();
        ImageIcon kortinKuva = new ImageIcon(im);
        
        targetLabel.setIcon(kortinKuva);
    }
    
    //Tarkistetaan onko halutun indexin kortti lukittu
    public static boolean OnkoKorttiLukittu(int iKorttiIndex) {
        javax.swing.JCheckBox targetCheckbox;
        
        switch (iKorttiIndex) {
            case 4:
                targetCheckbox = Gui_Main.jCheckBox5;
                break;
            case 3:
                targetCheckbox = Gui_Main.jCheckBox4;
                break;
            case 2:
                targetCheckbox = Gui_Main.jCheckBox3;
                break;
            case 1:
                targetCheckbox = Gui_Main.jCheckBox2;
                break;
            default:
                targetCheckbox = Gui_Main.jCheckBox1;
                break;
        }
        return targetCheckbox.isSelected();
    }
    
    //Korttien sekoitus takaisin pakkaan
    public static void SiirraVaihdettavatKortitMuuKasaan(ArrayList<Kortti> kortitMuu, ArrayList<Kortti> kortitKasi) {
        //Looppaa checkboxeja
        //Checkboxeja loopataan käänteisessä järjestyksessä koska arraylist.remove(x) siirtää x jälkeiset kortit yhden vasemmalle (pienemmäksi)
        for (int iKorttiIndex = 4; iKorttiIndex >= 0; iKorttiIndex--) {
            if (KorttiKasittely.OnkoKorttiLukittu(iKorttiIndex)) {
                //Jos kortti on lukittu niin ei tehdä mitään
            } else {
                //Lisää vaihdettava käsikortti muu kortteihin
                kortitMuu.add(kortitKasi.get(iKorttiIndex));
                //Poista kortti kädestä
                kortitKasi.remove(iKorttiIndex);
                //Piirrä käsikortti tyhjäksi
                    //KOODISSA ONGELMIA JOTEN HOIDAN SEN MUUALLA MANUAALISESTI
                    //KorttiKasittely.AsetaKasiKorttiTyhja(iKorttiIndex);
            }
        }
    }
    
    //Korttien sekoitus takaisin pakkaan
    //Uudet kortit nostetaan index paikkoihin niihin paikkoihin josta äsken vaihdettiin kortteja
    public static void NostaUudetKortitVaihdettujenTilalle(ArrayList<Kortti> kortitPakka, ArrayList<Kortti> kortitKasi) {
        javax.swing.JCheckBox targetCheckbox;
        
        //Looppaa checkboxeja
        for (int iKorttiIndex = 0; iKorttiIndex <= 4; iKorttiIndex++) {
            switch (iKorttiIndex) {
                case 0:
                    targetCheckbox = Gui_Main.jCheckBox1;
                    break;
                case 1:
                    targetCheckbox = Gui_Main.jCheckBox2;
                    break;
                case 2:
                    targetCheckbox = Gui_Main.jCheckBox3;
                    break;
                case 3:
                    targetCheckbox = Gui_Main.jCheckBox4;
                    break;
                default:
                    targetCheckbox = Gui_Main.jCheckBox5;
                    break;
            }
            if (targetCheckbox.isSelected()) {
                //Jos lukittu niin ei tarvitse tehdä mitään
            } else {
                //Nosta kortti tähän index positioon
                KorttiKasittely.NostaKortti(kortitPakka, kortitKasi, iKorttiIndex);
            }
        }
    }
    
    //Tulosta halutun kortti arrayn kaikki kortit
    public static void TulostaKortit(ArrayList<Kortti> tulostettavatKortit, String sSijainti) {
        System.out.println("");
        System.out.println(sSijainti + " on " + tulostettavatKortit.size() + " korttia, jotka ovat:");
        for (Kortti kortti : tulostettavatKortit) {
            System.out.println(kortti.getKorttiMaa() + " " + kortti.getKorttiNimi());
        }
    }
}
