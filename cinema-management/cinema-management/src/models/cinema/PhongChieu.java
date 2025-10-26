package models.cinema;

import base.ThucThe;

public class PhongChieu extends ThucThe {
    private int soGhe;
    private String loaiPhong;
    private RapChieuPhim rap;
    
    public PhongChieu(String maPhong, String tenPhong, int soGhe, String loaiPhong) {
        super(maPhong, tenPhong);
        this.soGhe = validSoGhe(soGhe);
        this.loaiPhong = validLoaiPhong(loaiPhong);
    }
    
    public RapChieuPhim getRap() {
        return rap;
    }
    
    public void setRap(RapChieuPhim rap) {
        this.rap = rap;
    }

    
    private int validSoGhe(int sg) {
        if (sg <= 0 || sg > 500) {
            throw new IllegalArgumentException("Số ghế phải từ 1-500");
        }
        return sg;
    }
    
    private String validLoaiPhong(String lp) {
        if (lp == null || lp.trim().isEmpty()) {
            throw new IllegalArgumentException("Loại phòng không được để trống");
        }
        return lp.trim();
    }
    
    @Override
    public void hienThiThongTin() {
        System.out.printf("║ %-10s │ %-20s │ %5d │ %-15s ║%n",
            ma, ten, soGhe, loaiPhong);
    }
    
    @Override
    public boolean kiemTraHopLe() {
        return ma != null && !ma.isEmpty() && soGhe > 0;
    }
    
    public int getSoGhe() { return soGhe; }

}
