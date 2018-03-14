package oliot_projekti;

import java.util.ArrayList;
import java.awt.Image;
import javax.swing.*;

public class Funktiot {
    
    //Tarkista että edes yksi kortti on lukittu
    public static boolean TarkistaOnkoMikaanKorttiLukittu() {
        if (Gui_Main.jCheckBox1.isSelected()) {
            return true;
        } else if (Gui_Main.jCheckBox2.isSelected()) {
            return true;
        } else if (Gui_Main.jCheckBox3.isSelected()) {
            return true;
        } else if (Gui_Main.jCheckBox4.isSelected()) {
            return true;
        } else if (Gui_Main.jCheckBox5.isSelected()) {
            return true;
        } else {
            return false;
        }
    }
    
    //Kortin kuvan muuttaminen selkäkortiksi
    public static void AsetaKorttiSelkaKuva(int iIndex) {
        javax.swing.JLabel jLabel_Temp;
        
        switch (iIndex) {
            case 4:
                jLabel_Temp = Gui_Main.jLabel5;
                break;
            case 3:
                jLabel_Temp = Gui_Main.jLabel4;
                break;
            case 2:
                jLabel_Temp = Gui_Main.jLabel3;
                break;
            case 1:
                jLabel_Temp = Gui_Main.jLabel2;
                break;
            default:
                jLabel_Temp = Gui_Main.jLabel1;
                break;
        }
        
        ImageIcon kortinKuva = new ImageIcon("/Kuvat/cardback.png");
        jLabel_Temp.setIcon(kortinKuva); // NOI18N
    }
//SAMA KOODI ON VIELÄ KORTTIKASITTELY LUOKASSA
//    //Kortin kuvan muuttaminen kortin kuvaksi
//    public static void AsetaKorttiNaamaKuva(int iIndex, Kortti tempKortti) {
//        javax.swing.JLabel jLabel_Temp;
//        
//        //Mikä kortin tiedostonimi on?
//        String sKuvannimi = tempKortti.getKorttiTiedostoNimi();
//        
//        switch (iIndex) {
//            case 4:
//                jLabel_Temp = Gui_Main.jLabel5;
//                break;
//            case 3:
//                jLabel_Temp = Gui_Main.jLabel4;
//                break;
//            case 2:
//                jLabel_Temp = Gui_Main.jLabel3;
//                break;
//            case 1:
//                jLabel_Temp = Gui_Main.jLabel2;
//                break;
//            default:
//                jLabel_Temp = Gui_Main.jLabel1;
//                break;
//        }
//        //targetLabelissä on se haluttu label
//        Image im=new ImageIcon(tempKortti.getClass().getResource("/Kuvat/"+sKuvannimi+".png")).getImage();
//        ImageIcon kortinKuva = new ImageIcon(im);
//        
//        jLabel_Temp.setIcon(kortinKuva);
//    }
    //Kortin lukitseminen/lukituksen vaihtaminen
    public static void KorttiVaihdaLukitusta(javax.swing.JCheckBox jCheckbox_Temp, ArrayList<Kortti> kasi) {
        if (jCheckbox_Temp.isSelected()) {
            jCheckbox_Temp.setSelected(false);
            VoitonTarkistus.Potentiaaliset(kasi);
        } else {
            jCheckbox_Temp.setSelected(true);
            VoitonTarkistus.Potentiaaliset(kasi);
        };
    }
    //Kortin lukituksen asemmatinen halutuksi
    public static void KortitAsetaLukitusPois(ArrayList<Kortti> kasi) {
        Gui_Main.jCheckBox5.setSelected(false);
        Gui_Main.jCheckBox4.setSelected(false);
        Gui_Main.jCheckBox3.setSelected(false);
        Gui_Main.jCheckBox2.setSelected(false);
        Gui_Main.jCheckBox1.setSelected(false);
        //Tähän kutsu potentiaalivoittojen laskemiselle
        VoitonTarkistus.Potentiaaliset(kasi);
    }
    //Kortin lukituksen asemmatinen halutuksi
    public static void KortitAsetaLukitus(int iIndex, boolean bTemp, ArrayList<Kortti> kasi) {
        javax.swing.JCheckBox jCheckbox_Temp;
                
        switch (iIndex) {
            case 4:
                jCheckbox_Temp = Gui_Main.jCheckBox5;
                break;
            case 3:
                jCheckbox_Temp = Gui_Main.jCheckBox4;
                break;
            case 2:
                jCheckbox_Temp = Gui_Main.jCheckBox3;
                break;
            case 1:
                jCheckbox_Temp = Gui_Main.jCheckBox2;
                break;
            default:
                jCheckbox_Temp = Gui_Main.jCheckBox1;
                break;
        }
        jCheckbox_Temp.setSelected(bTemp);
        //Tähän kutsu potentiaalivoittojen laskemiselle
        VoitonTarkistus.Potentiaaliset(kasi);
    }
    //Lukitse ne kortit jotka VoitonTarkistus.LukitseVoitot koodi on merkinnyt
    public static void LukitseVoittokortit(String sVoittokortit) {
        //Esim. 801234
        //8 = Voiton tyyppi
        //01234 = Kortit jotka pitää lukita
        
        //Ohita voiton suuruus ja tarkista vain lukittavat kortit
        for(int index = 1;index < sVoittokortit.length();index++){
            int arvo = Character.getNumericValue(sVoittokortit.charAt(index));
            Funktiot.KortitAsetaLukitus(arvo, true);
        }
    }
    //Ohjeteksin asettaminen halutuksi
    //EI TOIMI VIELÄ
    public static void AsetaOhjeteksti(String sTeksti) {
        Gui_Main.jLabel24.setText(sTeksti);
    }
}
