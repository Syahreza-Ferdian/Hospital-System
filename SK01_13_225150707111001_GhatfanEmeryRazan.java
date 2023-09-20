import java.util.LinkedList;
import java.util.Scanner;

public class SK01_13_225150707111001_GhatfanEmeryRazan {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ListPasien queue = new ListPasien();

        menu: while (true) {
            System.out.printf("%sMenu Utama:%s", Color.BLUE, Color.RESET);
            System.out.print("\n1. Tambahkan Pasien\n2. Hapus Pasien\n3. Hapus Pasien berdasarkan layanan\n4. Tampilkan Antrian\n5. Exit");
            System.out.print("\n[1/2/3/4/5]: ");

            int input;
            try {
                input = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                in.nextLine();
                System.out.printf("\n%s%sPilih angka 1-5!%s\n\n", Color.RED_BG, Color.WHITE_BOLD, Color.RESET);
                continue;
            }

            switch(input) {
                case 1:
                    String name, service;
                    JenisAsuransi serviceType;
                    System.out.printf("\n%sMasukkan data pasien%s", Color.CYAN, Color.RESET);
                    System.out.print("\nNama\t\t: ");
                    name = in.nextLine();
                    
                    inputCycle: while (true) {
                        System.out.print("Jenis Layanan\t: ");
                        service = in.nextLine().toLowerCase();
                        
                        switch (service) {
                        case "vvip":
                            serviceType = JenisAsuransi.VVIP;
                            break;
                        case "vip":
                            serviceType = JenisAsuransi.VIP;
                            break;
                        case "biasa":
                            serviceType = JenisAsuransi.BIASA;
                            break;
                        default:
                            System.out.printf("\n%s%sPilih layanan BIASA, VIP, atau VVIP!%s\n\n", Color.RED_BG, Color.WHITE_BOLD, Color.RESET);
                            continue;   
                        }
                        break inputCycle;
                    }
                    
                    System.out.printf("\n%s%sBerhasil menambahkan pasien!%s\n\n", Color.GREEN_BG, Color.WHITE_BOLD, Color.RESET);
                    queue.tambahPasien(new Pasien(name, serviceType));
                    break;
                case 2:
                    int number;
                    inputCycle: while (true) {
                        System.out.print("Nomor antrian : ");
                        number = in.nextInt();
                        in.nextLine();
                        try {
                            queue.hapusPasien(queue.toPasienArray()[number - 1]);
                        } catch (Exception e) {
                            System.out.printf("\n%s%sPasien dengan nomor urut %d tidak dapat ditemukan!%s\n\n", Color.RED_BG, Color.WHITE_BOLD, number, Color.RESET);
                            break;
                        }

                        System.out.printf("\n%s%sBerhasil menghapus pasien!%s\n\n", Color.GREEN_BG, Color.WHITE_BOLD, Color.RESET);
                        break inputCycle;
                    }
                    break;
                case 3:
                    inputCycle: while (true) {
                        System.out.print("Jenis Layanan : ");
                        service = in.nextLine().toLowerCase();
                        
                        switch (service) {
                        case "vvip":
                            serviceType = JenisAsuransi.VVIP;
                            break;
                        case "vip":
                            serviceType = JenisAsuransi.VIP;
                            break;
                        case "biasa":
                            serviceType = JenisAsuransi.BIASA;
                            break;
                        default:
                            System.out.printf("\n%s%sPilih layanan BIASA, VIP, atau VVIP!%s\n\n", Color.RED_BG, Color.WHITE_BOLD, Color.RESET);
                            continue;   
                        }
                        break inputCycle;
                    }

                    try {
                        queue.hapusPasienAsuransi(serviceType);
                    } catch (Exception e) {
                        System.out.printf("\n%s%s%s%s\n\n", Color.RED_BG, Color.WHITE_BOLD, e.getMessage(), Color.RESET);
                        break;
                    }

                    System.out.printf("\n%s%sBerhasil menghapus pasien!%s\n\n", Color.GREEN_BG, Color.WHITE_BOLD, Color.RESET);
                    break;
                case 4:
                    System.out.printf("\n%s%s%s", Color.YELLOW, "Daftar Pasien", Color.RESET);
                    Pasien[] patients = queue.toPasienArray();
                    int length = patients.length;
                    if (length == 0) {
                        System.out.print("\nAntrian kosong...\n\n");
                        break;
                    }

                    for (int i = 0; i < length; i++) {
                        System.out.printf("\n%d. %s - %s", i + 1, patients[i].getJenisAsuransi(), patients[i].getName());
                    }
                    System.out.print("\n\n");
                    break;
                case 5:
                    System.out.printf("\n%s%sTerima Kasih telah menggunakan layanan kami 🥰%s\n", Color.YELLOW_BG, Color.WHITE_BOLD, Color.RESET);
                    break menu;
            }
        }

        in.close();
    }

}

enum Color {
    RESET("\033[0m"),
    RED("\033[0;31m"),
    RED_BG("\033[0;101m"),
    CYAN("\033[0;36m"),
    BLUE("\033[0;34m"),
    YELLOW("\033[0;33m"),
    YELLOW_BG("\033[0;103m"),
    GREEN_BG("\033[42m"),
    WHITE_BOLD("\033[1;37m");

    private final String ansiCode;

    private Color(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    @Override
    public String toString() {
        return ansiCode;
    }
}

class ListPasien implements List {
    private LinkedList<Pasien> queue;

    public ListPasien() {
        queue = new LinkedList<>();
    }

    public void tambahPasien(Pasien p) {
        int vvipIndex, vipIndex;

        switch (p.getJenisAsuransi()) {
            case BIASA:
                queue.addLast(p);
                break;
            case VVIP:
                vvipIndex = queue.lastIndexOf(new Pasien(null, JenisAsuransi.VVIP));
                if (vvipIndex != -1) {
                    sisipPasienPadaUrutanKe(p, vvipIndex + 1);
                    break;
                }

                queue.addFirst(p);
                break;
            case VIP:
                vipIndex = queue.lastIndexOf(new Pasien(null, JenisAsuransi.VIP));
                if (vipIndex != -1) {
                    sisipPasienPadaUrutanKe(p, vipIndex + 1);
                    break;
                }

                vvipIndex = queue.lastIndexOf(new Pasien(null, JenisAsuransi.VVIP));
                if (vvipIndex != -1) {
                    sisipPasienPadaUrutanKe(p, vvipIndex + 1);
                    break;
                }

                queue.addFirst(p);
                break;
            default:
                break;
        }
    }

    public void sisipPasienPadaUrutanKe(Pasien p, int urutan) {
        queue.add(urutan, p);
    }

    public void hapusPasien(Pasien p) {
        queue.removeIf(pasien -> (p.getName() == pasien.getName() && p.getJenisAsuransi() == pasien.getJenisAsuransi()));
    }

    public void hapusPasienAsuransi(JenisAsuransi asuransi) throws Exception {
        boolean removeAny = queue.removeIf(pasien -> (pasien.getJenisAsuransi() == asuransi));
        
        if (!removeAny) {
            throw new Exception(String.format("Pasien dengan jenis asuransi %s tidak ditemukan!", asuransi));
        }
    }

    public Pasien[] toPasienArray() {
        return queue.toArray(new Pasien[0]);
    }
}

enum JenisAsuransi {
    VVIP("VVIP"),
    VIP("VIP"),
    BIASA("BIASA");

    private final String str;

    JenisAsuransi(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}

class Pasien {
    private String name;
    private JenisAsuransi jenisAsuransi;

    public Pasien(String name, JenisAsuransi jenisAsuransi) {
        this.name = name;
        this.jenisAsuransi = jenisAsuransi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JenisAsuransi getJenisAsuransi() {
        return jenisAsuransi;
    }

    public void setJenisAsuransi(JenisAsuransi jenisAsuransi) {
        this.jenisAsuransi = jenisAsuransi;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Pasien) obj).jenisAsuransi == this.jenisAsuransi;
    }
}

interface List {
    void tambahPasien(Pasien p);

    void hapusPasien(Pasien p);

    void sisipPasienPadaUrutanKe(Pasien p, int urutan);

    void hapusPasienAsuransi(JenisAsuransi asuransi) throws Exception;

    Pasien[] toPasienArray();

    @Override
    String toString();
}
