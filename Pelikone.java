/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jokeripokeri;

/**
 *
 * @author Jari
 */
public class Pelikone {
    //Panos

    double dPanos = 0.00;
    double dMinPanos = 0.20, dMaxPanos = 1.0;
    //0.20 default
    //Rahamäärä (double)
    double dRahamaara = 10;
    //Default 0
    //Valinnainen: Edellinen käyttäjä jättänyt rahaa Math.random
    //Voitot (double)
    double dVoitot = 0;
    //Default 0
    int iPelivaihe = 1;

    public Pelikone() {
    }
    //Set panos

    public void NostaPanosta() {
        //Kasvata panosta
        this.dPanos = this.dPanos + 0.20;
        //Jos panos oli jo suurin mahdollinen niin aseta pienin panos
        if (this.dPanos > dMaxPanos) {
            this.dPanos = dMinPanos;
        }
        String sArvo = String.format("%.2f", this.dPanos);
        Gui.jLabel18.setText("  " + sArvo + " €");
    }
    //Voitot rahamäärään metodi

    public void AsetaVoitotRahamaara(double dAsetettavaVoitto) {
        this.dVoitot = dAsetettavaVoitto;
    }
    //Siirrä voitot pelikoneen rahamäärään

    public void VoitotRahamaaraan() {
        if (this.dVoitot > 0) {
            //Siirrä voitot rahamäärään

            this.dRahamaara = this.dRahamaara + this.dVoitot;
            this.dVoitot = 0;

        }

        String sVoitot = String.format("%.2f", this.dVoitot);

        String sRahamaara = String.format("%.2f", this.dRahamaara);
        Gui.jLabel17.setText("  " + sRahamaara);
        Gui.jLabel16.setText("  " + sVoitot);

    }
    //Voitot lompakkoon

    public void VoitotLompakkoon(Lompakko targetLompakko) {
        //Kutsu lompakko olion setteriä
//            targetLompakko.LisaaRahamaara(this.dRahamaara);
        this.dRahamaara = 0;
    }

    public int getiPelivaihe() {
        return iPelivaihe;
    }
            public double getdPanos() {
            return dPanos;
        }

    public void setiPelivaihe(int iAsetaVaihe) {
        this.iPelivaihe = iAsetaVaihe;
                    if (this.iPelivaihe == 1) {
                //Esim:
                //Gui_Main.jB2_Tuplaa.setEnabled(false);
                //Gui_Main.jB4_Jaa.setEnabled(true);
            } else if (this.iPelivaihe == 2) {

            } else if (this.iPelivaihe == 3) {

            } else if (this.iPelivaihe == 4) {

            } else {

            }
    }
}

