import org.example.wallet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;

public class walletTest {

    //ganti nama owner
    @Test
    void TestsetOwner(){
        wallet Wallet = new wallet("Coba 1");
        Wallet.setOwner("Coba 2");
        Assertions.assertEquals("Coba 2", Wallet.getOwner(),"nama owner kartu salah");
    }
    //test kartu berhasil apa engga ditambahin
    @Test
    void TestTambahKartu() {
        wallet Wallet = new wallet("Adi");
        Wallet.tambahKartu("Kartu 1");
        Assertions.assertTrue(Wallet.listkartu.contains("Kartu 1"), "kartu tidak berhasil ditambahkan");
    }

    //test kartu masuk double apa engga (error)
    @Test
    void testTambahKartuDouble() {
        wallet Wallet = new wallet("Budy");
        Wallet.tambahKartu("Kartu 1");
        Wallet.tambahKartu("Kartu 1");

        int jumlahKartu1 = Collections.frequency(Wallet.listkartu, "Kartu 1");
        Assertions.assertEquals(1, jumlahKartu1, "Kartu Masuk Double");
    }

    //test kartu hilang apa engga
    @Test
    void testAmbilkartu() {
        wallet Wallet = new wallet("citra");
        Wallet.tambahKartu("Kartu 1");
        Wallet.ambilkartu("Kartu 1");
        Assertions.assertFalse(Wallet.listkartu.contains("Kartu 1"), "Kartu Tidak berhasil diambil");
    }

    //test kalau kartunya ga ada
    @Test
    void testAmbilKartuTapiTidakAda() {
        wallet Wallet = new wallet("Dodi");
        Assertions.assertNull( Wallet.ambilkartu("Kartu 1"));
    }


    //test kalau masukin angka kurang dari 0 maka akan muncul error msg
    @Test
    void testTambahUangRupiahKurangDari0(){
        wallet Wallet = new wallet("Edo");
        Assertions.assertThrows(Error.class, () ->{
            Wallet.tambahUangRupiah(-1);
        });
    }

    //menambahkan uang kertas
    @Test
    void testTambahRupiahLembar(){
        wallet Wallet = new wallet("Fina");
        Wallet.tambahUangRupiah(1200);
        int lastLembar = Wallet.listlembar.get(Wallet.listlembar.size() - 1);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(Wallet.listlembar),
                () -> Assertions.assertEquals(1200, lastLembar, "mengecek uang masuk atau tidak")
        );
    }


    //menambahkan uang koin
    @Test
    void testTambahRupiahKoin(){
        wallet Wallet = new wallet("hilal");
        Wallet.tambahUangRupiah(300);
        int lastKoin = Wallet.listkoin.get(Wallet.listkoin.size() - 1);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(Wallet.listkoin),
                () -> Assertions.assertEquals(300, lastKoin, "uang tidak berhasil masuk")
        );
    }


    //ambil uang kertas
    @Test
    void testAmbilUangLembar(){
        wallet Wallet = new wallet("Mulan");
        Wallet.tambahUangRupiah(3000);
        int AmbilUangKertas = Wallet.ambiluang(3000);
        Assertions.assertEquals(3000, AmbilUangKertas);
    }

    //ambil uang kertas tapi ga ada di list
    @Test
    void testAmbilUangLembarTidakDilist(){
        wallet Wallet = new wallet("Nunung");
        Wallet.tambahUangRupiah(3000);
        int AmbilUangKertas = Wallet.ambiluang(4000);
        Assertions.assertFalse(AmbilUangKertas!=0);
    }

    //ambil uang koin
    @Test
    void testAmbilUangKoin(){
        wallet Wallet = new wallet("Ogah");
        Wallet.tambahUangRupiah(200);
        int AmbilUangKoin = Wallet.ambiluang(200);
        Assertions.assertEquals(200, AmbilUangKoin);
    }

    //ambil uang koin tapi ga ada di list
    @Test
    void testAmbilUangKoinTidakDiList(){
        wallet Wallet = new wallet("Putri");
        Wallet.tambahUangRupiah(500);
        Wallet.tambahUangRupiah(200);
        int AmbilUangKoin = Wallet.ambiluang(700);
        Assertions.assertFalse(AmbilUangKoin!=0);
    }


    //Test nampilin jumlah seluruh uangnya koin + lembar
    @Test
    void testNampilinUang(){
        wallet Wallet = new wallet("Syania");
        Wallet.tambahUangRupiah(4200);
        Wallet.tambahUangRupiah(3200);
        int TotalUang = Wallet.tampilkanUang();
        Assertions.assertEquals(7400, TotalUang);
    }
}
