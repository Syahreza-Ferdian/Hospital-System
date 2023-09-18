import java.util.LinkedList;
import java.util.Scanner;

public class SK01_13_225150707111001_GhatfanEmeryRazan implements List{
    LinkedList <Pasien> listPasien = new LinkedList<>();

    @Override
    public void hapusPasien(Pasien p) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hapusPasienAsuransi(JenisLayanan asuransi) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sisipPasienPadaUrutanKe(Pasien p, int urutan) {
        // TODO Auto-generated method stub
        listPasien.add(urutan, p);
    }

    @Override
    public void tambahPasien(Pasien p) {
        // TODO Auto-generated method stub
        if(p.getJenisLayanan() == JenisLayanan.BIASA) listPasien.addLast(p);
        else if(p.getJenisLayanan() == JenisLayanan.VIP) {
            if(listPasien.lastIndexOf(new Pasien(JenisLayanan.VVIP)) != -1) {
                if(listPasien.lastIndexOf(new Pasien(JenisLayanan.VIP)) != -1) {
                    listPasien.add(listPasien.lastIndexOf(new Pasien(JenisLayanan.VIP)) + 1, p);
                }
                else listPasien.add(listPasien.lastIndexOf(new Pasien(JenisLayanan.VVIP)) + 1, p);
            }
            else listPasien.addFirst(p);
        }
        else if(p.getJenisLayanan() == JenisLayanan.VVIP) {
            if(listPasien.lastIndexOf(new Pasien(JenisLayanan.VVIP)) != -1) {
                listPasien.add(listPasien.lastIndexOf(new Pasien(JenisLayanan.VVIP)) + 1, p);
            }
            else listPasien.addFirst(p);
        }
    }

    public static void main(String[] args) {
        
    }

}

class Pasien {
    private String name;
    private JenisLayanan jenisLayanan;

    public Pasien(String name, JenisLayanan jenisLayanan) {
        this.name = name;
        this.jenisLayanan = jenisLayanan;
    }

    public Pasien(JenisLayanan jenisLayanan) {
        this.jenisLayanan = jenisLayanan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JenisLayanan getJenisLayanan() {
        return jenisLayanan;
    }

    public void setJenisLayanan(JenisLayanan jenisLayanan) {
        this.jenisLayanan = jenisLayanan;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Pasien) obj).jenisLayanan == this.jenisLayanan;
    }
}

enum JenisLayanan {
    VVIP,
    VIP,
    BIASA
}

interface List {
    void tambahPasien(Pasien p);
    void hapusPasien(Pasien p);
    void sisipPasienPadaUrutanKe(Pasien p, int urutan);
    void hapusPasienAsuransi(JenisLayanan asuransi);
    @Override
    String toString();
}
