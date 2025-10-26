package interfaces;
import java.time.LocalDate;

public interface IXuatBaoCao {
    void xuatBaoCaoTheoNgay(LocalDate ngay);
    void xuatBaoCaoTheoThang(int thang, int nam);
    void xuatBaoCaoTongQuat();
}