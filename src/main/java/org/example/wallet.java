package org.example;

import java.util.ArrayList;

public class wallet {

    String owner;
    public ArrayList<String> listkartu;

    public ArrayList<Integer> listkoin;
    public ArrayList<Integer> listlembar;

    //cuma owner yang bisa ganti ganti namanya
    public wallet(String owner){
        this.owner = owner;
        listkartu = new ArrayList<>();
        listkoin = new ArrayList<>();
        listlembar = new ArrayList<>();
    }


    //nambahin sendiri
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

    //buat nambahin kartunya ke list baru
    public void tambahKartu(String namakartu){
        if(!listkartu.contains(namakartu)){
            this.listkartu.add(namakartu);
        }
    }

    //buat ngilangin kartunya dari list
    public String ambilkartu(String namakartu){
        //pake boolean biar kalo ada namanya dia ngapus kalo ga ada dia akan false
        boolean isDeleted = this.listkartu.remove(namakartu);
        if(isDeleted){
            return namakartu;
        }
        return null;
    }

    public void tambahUangRupiah(int jumlahUang){
        if(jumlahUang <= 0 ){
            throw new Error("Jumlah Uang Tidak Boleh Minus");
        }
        if (jumlahUang >= 1000){
            listlembar.add(jumlahUang);
        } else {
            listkoin.add(jumlahUang);
        }
    }

    public int ambiluang(int jumlahUang){
        boolean isUangLembarTaken = this.listlembar.remove(
                Integer.valueOf(jumlahUang));
        if (isUangLembarTaken) {
            return jumlahUang;
        }
        boolean isUangKoinTaken = this.listkoin.remove(
                Integer.valueOf(jumlahUang));
        if (isUangKoinTaken) {
            return jumlahUang;
        }

        return 0;
    }
    public int tampilkanUang(){
        int totaluang = 0;

        for (Integer uang : listkoin){
            totaluang += uang;
        }
        for (Integer uang : listlembar){
            totaluang += uang;
        }
        return totaluang;
    }

}
