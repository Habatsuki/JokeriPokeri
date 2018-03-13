package oliot_projekti;

//Peliautomaatti (Olio)
public class Pelikone {
    //Panos
    double dPanos = 0.00;
    double dMinPanos = 0.20, dMaxPanos = 1.0;
    double dRahamaara = 10;
    double dVoitot = 0;
    int iPelivaihe = 1;

    public Pelikone() {
        //this.dRahamaara = Loki.NostaRahaa();
    }
    //Set panos
    public void NostaPanosta() {
        double dTempMaxPanos;
        
        //Kasvata panosta
        this.dPanos = this.dPanos + 0.20;
        
        //Jos rahamäärä on pienempi kuin dMaxPanos niin dMaxPanos = dRahamaara
        if (this.dRahamaara < dMaxPanos) {
            dTempMaxPanos = this.dRahamaara;
        } else {
            dTempMaxPanos = this.dMaxPanos;
        }
        //Jos panos oli jo suurin mahdollinen niin aseta pienin panos
        if (this.dPanos > dTempMaxPanos) {
            this.dPanos = dMinPanos;
        }
        //Kirjoita uusi panos
        this.PaivitaLukukentat();
    }
    //Kutsu panoksen tarkistusta ennen tätä
    public void MaksaPanos() {
        this.dRahamaara = this.dRahamaara - this.dPanos;
        this.PaivitaLukukentat();
    }
    //Siirrä voitot pelikoneen rahamäärään
    public void VoitotRahamaaraan() {
        if (this.dVoitot > 0) {
            //Siirrä voitot rahamäärään
            this.dRahamaara = this.dRahamaara + this.dVoitot;
            this.dVoitot = 0;
        }
        //Päivitä voitot ja rahamäärät labeleihin
        this.PaivitaLukukentat();
    }
    public void LaskeVoitot(int iVoittoTyyppi) {
        int iVoittokerroin = 0;
        //Viitoset
        if (iVoittoTyyppi == 8) {
            iVoittokerroin = 50;
        //Värisuora
        } else if (iVoittoTyyppi == 7) {    
            iVoittokerroin = 30;
        //Neloset
        }  else if (iVoittoTyyppi == 6) {
            iVoittokerroin = 15;
        //Täyskäsi
        }  else if (iVoittoTyyppi == 5) {
            iVoittokerroin = 8;
        //Väri
        }  else if (iVoittoTyyppi == 4) {
            iVoittokerroin = 4;
        //Suora
        }  else if (iVoittoTyyppi == 3) {
            iVoittokerroin = 3;
        //Kolmoset
        }  else if (iVoittoTyyppi == 2) {
            iVoittokerroin = 2;
        //Kaksi paria
        }  else if (iVoittoTyyppi == 1) {
            iVoittokerroin = 2;
        //Ei voittoa
        } else {
            iVoittokerroin = 0;
        }
        //Lasketaan todellinen voitto
        this.dVoitot = iVoittokerroin * this.dPanos;
        //Päivitetään arvot
        this.PaivitaLukukentat();
    }
    //Tuplaa voitot
    public void TuplaaVoitot() {
        this.dVoitot = this.dVoitot * 2;
        //Päivitetään arvot
        this.PaivitaLukukentat();
    }
    public int getiPelivaihe() {
        return iPelivaihe;
    }
    public void setiPelivaihe(int iAsetaVaihe) {
        this.iPelivaihe = iAsetaVaihe;
        
        //Tarkista onko pelikoneessa rahaa
        if (this.dRahamaara <= 0) {
            this.iPelivaihe = 6;
        }
        
        //Aseta painikkeet lukituiksi
        if (this.iPelivaihe == 1) {
            //Jos rahat eivät riitä panokseen aseta panos rahamäärän suuruiseksi
            if (this.dRahamaara < this.dPanos) {
                this.dPanos = this.dRahamaara;
            }
            
            //Esim:
            //Gui_Main.jB2_Tuplaa.setEnabled(false);
            //Gui_Main.jB4_Jaa.setEnabled(true);
        } else if (this.iPelivaihe == 2) {

        } else if (this.iPelivaihe == 3) {

        } else if (this.iPelivaihe == 4) {
            //Gui_Main.AsetaOhjeteksti("Valitse suuri tai pieni");
        } else if (this.iPelivaihe == 5) {

        } else {
            //Rahat on loppu
            //Lukitse painikkeet
            //Kiitä pelaamisesta
        }
        this.PaivitaLukukentat();
    }
    public void PaivitaLukukentat() {
        String sArvo = String.format("%.2f", this.dPanos);
        String sVoitot = String.format("%.2f", this.dVoitot);
        String sRahamaara = String.format("%.2f", this.dRahamaara);
        
        Gui_Main.jLabel8.setText(String.format("%.2f", this.dPanos * 50) + " € Viitoset");
        Gui_Main.jLabel9.setText(String.format("%.2f", this.dPanos * 30) + " € Värisuora");
        Gui_Main.jLabel10.setText(String.format("%.2f", this.dPanos * 15) + " € Neloset");
        Gui_Main.jLabel11.setText(String.format("%.2f", this.dPanos * 8) + " € Täyskäsi");
        Gui_Main.jLabel12.setText(String.format("%.2f", this.dPanos * 4) + " € Väri");
        Gui_Main.jLabel13.setText(String.format("%.2f", this.dPanos * 3) + " € Suora");
        Gui_Main.jLabel14.setText(String.format("%.2f", this.dPanos * 2) + " € Kolmoset");
        Gui_Main.jLabel15.setText(String.format("%.2f", this.dPanos * 2) + " € Kaksi Paria");
        
        Gui_Main.jLabel18.setText("  " + sArvo + " €");
        Gui_Main.jLabel16.setText("  " + sVoitot);
        Gui_Main.jLabel17.setText("  " + sRahamaara);
    }
}
