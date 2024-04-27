package DAO;

import Model.LopModel;
import Model.danhhieuModel;
import Model.hsModel;
import Model.phModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class hsDao {
    public List<hsModel> getdshs() {
        List<hsModel> ds = new ArrayList<>();
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT *, year(namnhaphoc) as nnh from tre";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                hsModel hs = new hsModel();
                hs.setHoten(rs.getString("hoten"));
                hs.setIdLop(rs.getString("idLop"));
                hs.setDangtheohoc(rs.getBoolean("dangtheohoc"));
                hs.setLanam(rs.getBoolean("lanam"));
                hs.setDiachi(rs.getString("diachi"));
                hs.setNgaysinh(rs.getDate("ngaysinh").toLocalDate());
                hs.setId(rs.getString("idtre"));
                hs.setNamnhaphoc(rs.getString("nnh"));
                hs.setNoisinh(rs.getString("noisinh"));
                ds.add(hs);
            }
            for (hsModel hs : ds) {
                List<LopModel.GVCN> dsgv = new ArrayList<>();
                SQL = "SELECT idcbnv, HoTen from CBNV where idLop = ?";
                stmt = cn.prepareStatement(SQL);
                stmt.setString(1, hs.getIdLop());
                rs = stmt.executeQuery();
                String s = "";
                while (rs.next()) {
                    s += rs.getString(2) + "\n";
                    hs.setGvcnString(s);
                    dsgv.add(new LopModel.GVCN(rs.getString(1), rs.getString(2)));
                }
                hs.setGvcn(dsgv);

                List<phModel> dsph = new ArrayList<>();
                SQL = "SELECT * from PhuHuynh where idTre = ?";
                stmt = cn.prepareStatement(SQL);
                stmt.setString(1, hs.getId());
                rs = stmt.executeQuery();
                while (rs.next()) {
                    phModel ph = new phModel();
                    ph.setId(rs.getString("idTre"));
                    ph.setDiachi(rs.getString("diachi"));
                    ph.setNghe(rs.getString("nghenghiep"));
                    ph.setSdt(rs.getString("sdt"));
                    ph.setVaitro(rs.getString("vaitro"));
                    ph.setHoten(rs.getString("hoten"));
                    dsph.add(ph);
                }

                hs.setPh(dsph);
                List<danhhieuModel> dsdh = new ArrayList<>();
                SQL = "SELECT XepLoai.idDanhhieu, idTre, DanhHieu, year(NamHoc) as nam\n" + "FROM            XepLoai lEFT OUTER JOIN\n" + "                  DanhHieu ON XepLoai.idDanhHieu = DanhHieu.idDanhHieu where idtre = ?";
                stmt = cn.prepareStatement(SQL);
                stmt.setString(1, hs.getId());
                rs = stmt.executeQuery();
                while (rs.next()) {
                    danhhieuModel dh = new danhhieuModel();
                    dh.setId(rs.getString("idDanhhieu"));
                    dh.setDanhhieu(rs.getString("danhhieu"));
                    dh.setNam(rs.getString("nam"));
                    dsdh.add(dh);
                }
                hs.setDanhhieu(dsdh);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ds;
    }

    public boolean upHs(String id, String ten, String idlop, String dc, String nam, boolean i, boolean ii, LocalDate d, String ns) {
        Date ngs = Date.valueOf(d);
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "UPDATE [dbo].[Tre]\n" + "   SET [HoTen] = ?\n" + "      ,[idLop] = ?\n" + "      ,[DiaChi] = ?\n" + "      ,[NamNhapHoc] = ?\n" + "      ,[laNam] = ?\n" + "      ,[dangTheoHoc] = ?\n" + "      ,[NgaySinh] = ?\n" + "      ,[NoiSinh] = ?\n" + " WHERE idTre = ?";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            stmt.setString(1, ten);
            stmt.setString(2, idlop);
            stmt.setString(3, dc);
            stmt.setString(4, nam);

            stmt.setBoolean(5, i);
            stmt.setBoolean(6, ii);
            stmt.setDate(7, ngs);
            stmt.setString(8, ns);
            stmt.setString(9, id);
            if (stmt.executeUpdate() != 1) {
                return false;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
