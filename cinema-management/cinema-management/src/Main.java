import system.HeThongQuanLyCungCapPhim;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                       ║");
        System.out.println("║        🎬 HỆ THỐNG QUẢN LÝ CUNG CẤP PHIM CHO RẠP CHIẾU 🎬             ║");
        System.out.println("║                                                                       ║");
        System.out.println("║                     Nhóm 9 __ lúc 10h30 31/10/2025                    ║");
        System.out.println("║               Thành viên:  Yên Hòa ____ Hán Đạt                       ║");
        System.out.println("║                                                                       ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");

        System.out.println("\n🎯 TÍNH NĂNG CHÍNH:");
        System.out.println("  ✅ Quản lý nhà phân phối với thông báo đa kênh");
        System.out.println("  ✅ Quản lý rạp chiếu với lịch sử & báo cáo");
        System.out.println("  ✅ Quản lý phim với đánh giá & tính toán");
        System.out.println("  ✅ Yêu cầu thuê phim với thanh toán đa phương thức");
        System.out.println("  ✅ Hợp đồng với tính thuế, chiết khấu");
        System.out.println("  ✅ Xác thực & phân quyền người dùng");
        System.out.println("  ✅ ìm kiếm nâng cao");
        System.out.println("  ✅ Thống kê & báo cáo chi tiết");

        System.out.println("\n🔐 THÔNG TIN ĐĂNG NHẬP MẪU:");
        System.out.println("  Tài khoản: ql001 | Mật khẩu: 123456 (Giám đốc)");
        System.out.println("  Tài khoản: ql002 | Mật khẩu: 123456 (Trưởng phòng)");
        System.out.println("  Tài khoản: ql003 | Mật khẩu: 123456 (Phó giám đốc)");

        System.out.println("\n⏳ Đang khởi động hệ thống...\n");

        try {
            Thread.sleep(1000);
            HeThongQuanLyCungCapPhim heThong = new HeThongQuanLyCungCapPhim();
            heThong.hienThiMenu();
        } catch (Exception e) {
            System.out.println("❌ Lỗi hệ thống: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
