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
        this.PaivitaArvot();
    }
    //Tarkista että rahat riittävät panokseen
    public boolean TarkastaRiittaakoRahatPanokseen() {
        //Rahat riittävät
        if (this.dRahamaara >= this.dPanos) {
            //Ei tarvitse tehdä mitään
            return true;
        //Rahaa on mutta se ei riitä nykyiseen panokseen
        } else if (this.dRahamaara > 0) {
            //Asetetaan suurin mahdollinen panos
            this.dPanos = this.dRahamaara;
            //Aseta uusi panos
            //Gui_Main.jLab_18_PanosSumma.setText(String.valueOf(this.dPanos));
            this.dRahamaara = this.dRahamaara - this.dPanos;
            
            return true;
        //Rahat eivät riitä edes pienimpään panokseen
        } else {
            return false;
        }
    }
    //Kutsu panoksen tarkistusta ennen tätä
    public void MaksaPanos() {
        this.dRahamaara = this.dRahamaara - this.dPanos;
        this.PaivitaArvot();
    }
    //Voitot rahamäärään metodi
    public void AsetaVoitotRahamaara(double dAsetettavaVoitto) {
        this.dVoitot = dAsetettavaVoitto;
        this.PaivitaArvot();
    }
    //Siirrä voitot pelikoneen rahamäärään
    public void VoitotRahamaaraan() {
        if (this.dVoitot > 0) {
            //Siirrä voitot rahamäärään
            this.dRahamaara = this.dRahamaara + this.dVoitot;
            this.dVoitot = 0;
        }
        //Päivitä voitot ja rahamäärät labeleihin
        this.PaivitaArvot();
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
        this.PaivitaArvot();
    }
    //Tuplaa voitot
    public void TuplaaVoitot() {
        this.dVoitot = this.dVoitot * 2;
        //Päivitetään arvot
        this.PaivitaArvot();
    }
    public int getiPelivaihe() {
        return iPelivaihe;
    }
    public double getdPanos() {
        return dPanos;
    }
    public double getdVoitot() {
        return dVoitot;
    }
    public void setiPelivaihe(int iAsetaVaihe) {
        this.iPelivaihe = iAsetaVaihe;
        
        //Tarkista onko pelikoneessa rahaa
        if (this.dRahamaara == 0) {
            this.iPelivaihe = 6;
        }
            
        //Aseta painikkeet lukituiksi
        if (this.iPelivaihe == 1) {
            
            //Esim:
            //Gui_Main.jB2_Tuplaa.setEnabled(false);
            //Gui_Main.jB4_Jaa.setEnabled(true);
        } else if (this.iPelivaihe == 2) {

        } else if (this.iPelivaihe == 3) {

        } else if (this.iPelivaihe == 4) {

        } else if (this.iPelivaihe == 5) {

        } else {
            //Rahat on loppu
            //Lukitse painikkeet
            //Kiitä pelaamisesta
        }
    }
    public void PaivitaArvot() {
        String sArvo = String.format("%.2f", this.dPanos);
        String sVoitot = String.format("%.2f", this.dVoitot);
        String sRahamaara = String.format("%.2f", this.dRahamaara);
        
        Gui_Main.jLabel18.setText("  " + sArvo + " €");
        Gui_Main.jLabel16.setText("  " + sVoitot);
        Gui_Main.jLabel17.setText("  " + sRahamaara);
    }
}
