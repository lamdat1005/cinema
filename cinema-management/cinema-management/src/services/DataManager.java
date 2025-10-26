package services;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.cinema.RapChieuPhim;
import models.contract.HopDongCungCap;
import models.distributor.NhaPhanPhoi;
import models.film.Phim;
import models.user.Quanly;

public class DataManager {
    private static final String NPP_FILE = "data/nhaphanphoi.txt";
    private static final String RAP_FILE = "data/rapchieu.txt";
    private static final String PHIM_FILE = "data/phim.txt";
    private static final String QUANLY_FILE = "data/quanly.txt";
    private static final String HOPDONG_FILE = "data/hopdong.txt";

    // Tạo thư mục cha nếu chưa tồn tại
    private static void ensureParentDirExists(String filePath) {
        File parent = new File(filePath).getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }

    // Lưu Nhà phân phối
    public static void saveNhaPhanPhoi(List<NhaPhanPhoi> list) {
        ensureParentDirExists(NPP_FILE);  
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(NPP_FILE))) {
            for (NhaPhanPhoi npp : list) {
                writer.println(npp.getMa() + "|" + npp.getTen() + "|" +
                    npp.getDiaChi() + "|" + npp.getSoDienThoai() + "|" + npp.getEmail());
            }
        } catch (java.io.IOException e) {
            System.out.println("❌ Loi luu NPP: " + e.getMessage());
        }
    }

    // Lưu Phim
    public static void savePhim(List<Phim> list) {
        ensureParentDirExists(PHIM_FILE);  
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(PHIM_FILE))) {
            for (Phim p : list) {
                writer.println(p.getMa() + "|" + p.getTen() + "|" + p.getThoiLuong() + "|" +
                    p.getTheLoai() + "|" + p.getNgayPhatHanh() + "|" +
                    p.getSoLuongBanKhaDung() + "|" + p.getGiaMotBan());
            }
        } catch (java.io.IOException e) {
            System.out.println("❌ Loi luu phim: " + e.getMessage());
        }
    }

    // Lưu Rạp
    public static void saveRap(List<RapChieuPhim> list) {
        ensureParentDirExists(RAP_FILE);  
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(RAP_FILE))) {
            for (RapChieuPhim r : list) {
                writer.println(r.getMa() + "|" + r.getTen() + "|" + r.getDiaChi() + "|" +
                    r.getSoDienThoai() + "|" + r.getEmail());
            }
        } catch (java.io.IOException e) {
            System.out.println("❌ Loi luu Rap: " + e.getMessage());
        }
    }
    // Lưu Quản lý
    public static void saveQuanLy(List<Quanly> list, List<RapChieuPhim> danhSachRap) {
        ensureParentDirExists(QUANLY_FILE);
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(QUANLY_FILE))) {
            if (list != null) {
                for (Quanly ql : list) {
                    if (ql == null) continue;
                    String maRap = (ql.getRapQuanLy() != null) ? ql.getRapQuanLy().getMa() : "";
                    writer.println(ql.getMa() + "|" + ql.getTen() + "|" + 
                        ql.getSoDienThoai() + "|" + ql.getEmail() + "|" + 
                        ql.getChucVu() + "|" + maRap);
                }
            }
        } catch (java.io.IOException e) {
            System.out.println("❌ Loi luu Quan ly: " + e.getMessage());
        }
    }

    //lưu hợp đồng
    public static void saveHopDong(List<HopDongCungCap> list, List<NhaPhanPhoi> danhSachNPP, List<RapChieuPhim> danhsachRap, List<Phim> danhSachPhim){
        ensureParentDirExists("data/hopdong.txt"); // Đảm bảo thư mục cha tồn tại nếu không thì tạo nó
        try{
            java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(HOPDONG_FILE));
            for (HopDongCungCap hd : list) {
            String dsMaYeuCau = String.join(",", hd.getDanhSachMaYeuCau());
            writer.println(hd.getMaHopDong() + "|" + 
                          hd.getNhaPhanPhoi().getMa() + "|" + 
                          hd.getRap().getMa() + "|" + 
                          hd.getPhim().getMa() + "|" + 
                          hd.getSoLuongBan() + "|" + 
                          hd.getNgayBatDau() + "|" + 
                          hd.getNgayKetThuc() + "|" + 
                          hd.getGiaThue() + "|" + 
                          hd.getTrangThai().name());
        }
        writer.close();
        } catch (java.io.IOException e) {
            System.out.println("❌ Lỗi lưu hợp đồng: " + e.getMessage());
        }

    }
    public static List<NhaPhanPhoi> loadNhaPhanPhoi() {
        List<NhaPhanPhoi> list = new ArrayList<>();
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(NPP_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    list.add(new NhaPhanPhoi(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
            System.out.println("✅ Da tai " + list.size() + " nha phan phoi");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("ℹ️ File NPP chua ton tai");
        } catch (Exception e) {
            System.out.println("❌ Loi doc NPP: " + e.getMessage());
        }
        return list;
    }

    public static List<Phim> loadPhim() {
        List<Phim> list = new ArrayList<>();
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(PHIM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    list.add(new Phim(parts[0], parts[1], Integer.parseInt(parts[2]),
                        parts[3], "Unknown", "Unknown", LocalDate.parse(parts[4]),
                        Integer.parseInt(parts[5]), Double.parseDouble(parts[6])));
                }
            }
            System.out.println("✅ Da tai " + list.size() + " phim");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("ℹ️ File Phim chua ton tai");
        } catch (Exception e) {
            System.out.println("❌ Loi doc Phim: " + e.getMessage());
        }
        return list;
    }

    public static List<RapChieuPhim> loadRap() {
        List<RapChieuPhim> list = new ArrayList<>();
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(RAP_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    list.add(new RapChieuPhim(parts[0], parts[1], parts[2], parts[3], parts[4], 8));
                }
            }
            System.out.println("✅ Da tai " + list.size() + " rap");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("ℹ️ File Rap chua ton tai");
        } catch (Exception e) {
            System.out.println("❌ Loi doc Rap: " + e.getMessage());
        }
        return list;
    }

    // Đọc Quản lý
    public static List<Quanly> loadQuanLy(List<RapChieuPhim> danhSachRap) {
        List<Quanly> list = new ArrayList<>();
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(QUANLY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    Quanly ql = new Quanly(
                        parts[0].trim(),  // ma
                        parts[1].trim(),  // ten
                        parts[2].trim(),  // soDienThoai
                        parts[3].trim(),  // email
                        parts[4].trim()   // chucVu
                    );
                    
                    // Phân công rạp nếu có thông tin
                    if (parts.length >= 6 && !parts[5].trim().isEmpty()) {
                        String maRap = parts[5].trim();
                        for (RapChieuPhim rap : danhSachRap) {
                            if (rap.getMa().equalsIgnoreCase(maRap)) {
                                ql.phanCongRap(rap);
                                break;
                            }
                        }
                    }
                    list.add(ql);
                }
            }
            System.out.println("✅ Da tai " + list.size() + " quan ly");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("ℹ️ File Quan ly chua ton tai");
        } catch (Exception e) {
            System.out.println("❌ Loi doc Quan ly: " + e.getMessage());
        }
        return list;
    }

    // đọc hợp đồng
    public static List<HopDongCungCap> loadHopDong(List<NhaPhanPhoi> danhSachNPP, List<RapChieuPhim> danhSachRap, List<Phim> danhSachPhim) {
        List<HopDongCungCap> list = new ArrayList<>();
        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("data/hopdong.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 8) {
                    // Tìm NPP, Rap, Phim theo mã
                    NhaPhanPhoi npp = danhSachNPP.stream()
                        .filter(n -> n.getMa().equals(parts[1]))
                        .findFirst().orElse(null);
                        //Ý nghĩa: Biến danhSachNPP (giả sử là một List<NhaPhanPhoi>) được chuyển đổi thành một Stream (luồng dữ liệu).
                        //Cơ chế: Nó giữ lại (lọc) những phần tử trong Stream mà thỏa mãn điều kiện bên trong.
                        //n: Đại diện cho mỗi đối tượng NhaPhanPhoi trong Stream.
                        //n.getMa(): Lấy mã (ID) của nhà phân phối hiện tại.
                        //.equals(parts[1]): So sánh mã này với giá trị ở vị trí thứ hai trong mảng parts (giả sử parts[1] chứa mã nhà phân phối cần tìm).
                    RapChieuPhim rap = danhSachRap.stream()
                        .filter(r -> r.getMa().equals(parts[2]))
                        .findFirst().orElse(null);
                    Phim phim = danhSachPhim.stream()
                        .filter(p -> p.getMa().equals(parts[3]))
                        .findFirst().orElse(null);
                    
                    if (npp != null && rap != null && phim != null) {
                        HopDongCungCap hd = new HopDongCungCap(
                            parts[0], // maHopDong
                            npp, rap, phim,
                            Integer.parseInt(parts[4]), // soLuongBan
                            LocalDate.parse(parts[5]), // ngayBatDau
                            LocalDate.parse(parts[6]), // ngayKetThuc
                            Double.parseDouble(parts[7]) // giaThue
                        );
                        if (parts.length >= 9 && parts[8].equals("DANGHIEULUC")) {
                            hd.kichHoat();
                        }
                        if (parts.length >= 10 && !parts[9].isEmpty()) {
                            // part[9] là danh sách mã yêu cầu, ngăn cách bằng dấu phẩy
                            String[] maYeuCauArr = parts[9].split(",");
                            for (String maYC : maYeuCauArr) {
                                hd.themMaYeuCau(maYC.trim());
                            }
                        }

                        list.add(hd);
                        npp.themHopDong(hd);
                    }
                }
            }
            reader.close();
            // Ghi dữ liệu hợp đồng vào file
            System.out.println("Da tai " + list.size() + " hop dong");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File Hop dong chua ton tai");
        } catch (Exception e) {
            System.out.println("Loi doc Hop dong: " + e.getMessage());
        }
        return list;
    }

}