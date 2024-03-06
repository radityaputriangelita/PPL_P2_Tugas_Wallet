import org.example.wallet;
import org.junit.jupiter.api.*;
import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.Collections;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class walletTest {

    private wallet Wallet;


    //Buat terlebih dahulu objek wallet
    @BeforeAll
    void initClass(){
        Wallet = new wallet("Angel");
        System.out.println("before all works");
    }

    //menambahkan nama owner untuk walletnya
    @BeforeEach
    void initMethod(){
        System.out.println("before each works");
    }


    //buat biar setelah objek dibuat dia akan menghapus semua isi di dalamnya.
    @AfterEach
    void clearEach() {
        Wallet.listkartu.clear();
        Wallet.listkoin.clear();
        Wallet.listlembar.clear();
        System.out.println("after each works");
    }
    @AfterAll
    void clearAll()
    {
        Wallet = null;
        System.out.println("after all works");
    }

    //ganti nama owner
    @Test
    void testSetOwner(){
        Wallet.setOwner("Coba 2");
        Assertions.assertEquals("Coba 2", Wallet.getOwner(),"nama owner kartu salah");
        System.out.println("test mengubah nama owner berhasil");
    }
    //test kartu berhasil apa engga ditambahin
    @Test
    void testTambahKartu() {
        Wallet.tambahKartu("Kartu 1");
        Assertions.assertTrue(Wallet.listkartu.contains("Kartu 1"), "kartu tidak berhasil ditambahkan");
        System.out.println("test menambah kartu berhasil");
    }

    //test kartu masuk double apa engga (error)
    @Test
    void testTambahKartuDouble() {
        Wallet.tambahKartu("Kartu 1");
        Wallet.tambahKartu("Kartu 1");

        int jumlahKartu1 = Collections.frequency(Wallet.listkartu, "Kartu 1");
        Assertions.assertEquals(1, jumlahKartu1, "Kartu Masuk Double");
        System.out.println("test hanya menambah satu dari kartu dua kartu sama berhasil");
    }

    //test kartu hilang apa engga
    @Test
    void testAmbilkartu() {
        Wallet.tambahKartu("Kartu 1");
        Wallet.ambilkartu("Kartu 1");
        Assertions.assertFalse(Wallet.listkartu.contains("Kartu 1"), "Kartu Tidak berhasil diambil");
        System.out.println("test mengambil kartu berhasil");
    }

    //test kalau kartunya ga ada
    @Test
    void testAmbilKartuTapiTidakAda() {
        Assertions.assertNull( Wallet.ambilkartu("Kartu 1"));
        System.out.println("test mengeluarkan null saat mengambil kartu yang tidak ada berhasil");
    }


    //test kalau masukin angka kurang dari 0 maka akan muncul error msg
    @Test
    void testTambahUangRupiahKurangDari0(){
        Assertions.assertThrows(Error.class, () ->{
            Wallet.tambahUangRupiah(-1);
        });
        System.out.println("test error msg saat tambah uang kurang dari 0");
    }

    //menambahkan uang kertas
    @Test
    void testTambahRupiahLembar(){
        Wallet.tambahUangRupiah(1200);
        int lastLembar = Wallet.listlembar.get(Wallet.listlembar.size() - 1);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(Wallet.listlembar),
                () -> Assertions.assertEquals(1200, lastLembar, "mengecek uang masuk atau tidak")
        );
        System.out.println("test nambah uang rupiah berhasil");
    }


    //menambahkan uang koin
    @Test
    void testTambahRupiahKoin(){
        Wallet.tambahUangRupiah(300);
        int lastKoin = Wallet.listkoin.get(Wallet.listkoin.size() - 1);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(Wallet.listkoin),
                () -> Assertions.assertEquals(300, lastKoin, "uang tidak berhasil masuk")
        );
        System.out.println("test nambah uang koin berhasil");
    }


    //ambil uang kertas
    @Test
    void testAmbilUangLembar(){
        Wallet.tambahUangRupiah(3000);
        int AmbilUangLembar = Wallet.ambiluang(3000);
        Assertions.assertEquals(3000, AmbilUangLembar);
        System.out.println("test ambil uang lembar berhasil");
    }

    //ambil uang kertas tapi ga ada di list
    @Test
    void testAmbilUangLembarTidakDilist(){
        Wallet.tambahUangRupiah(3000);
        int AmbilUangKertas = Wallet.ambiluang(4000);
        Assertions.assertFalse(AmbilUangKertas!=0);
        System.out.println("test false jika mengambil uang lembar tidak di list berhasil");
    }

    //ambil uang koin
    @Test
    void testAmbilUangKoin(){
        Wallet.tambahUangRupiah(200);
        int AmbilUangKoin = Wallet.ambiluang(200);
        Assertions.assertEquals(200, AmbilUangKoin);
        System.out.println("test mengambil uang koin berhasil");
    }

    //ambil uang koin tapi ga ada di list
    @Test
    void testAmbilUangKoinTidakDiList(){
        Wallet.tambahUangRupiah(500);
        Wallet.tambahUangRupiah(200);
        int AmbilUangKoin = Wallet.ambiluang(700);
        Assertions.assertFalse(AmbilUangKoin!=0);
        System.out.println("test false jika mengambil uang koin tidak di list berhasil");
    }


    //Test nampilin jumlah seluruh uangnya koin + lembar
    @Test
    void testNampilinUang(){
        Wallet.tambahUangRupiah(4200);
        Wallet.tambahUangRupiah(3200);
        int TotalUang = Wallet.tampilkanUang();
        Assertions.assertEquals(7400, TotalUang);
        System.out.println("test nampilin jumlah uang berhasil");
    }
}
