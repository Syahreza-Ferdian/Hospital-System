public class SK01_13_225150707111001_GhatfanEmeryRazan {

}

class Pasien {
    private String name;
    private JenisLayanan jenisLayanan;

    public Pasien(String name, JenisLayanan jenisLayanan) {
        this.name = name;
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
