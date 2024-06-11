package Model;

import DAO.DbHelper;
import javafx.scene.control.CheckBox;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChamCongModel {
    private String id;
    private String ten;
    private List<CheckBox> ds = new ArrayList<>();
    private Integer dem = 0;
    private CheckBox select1 = new CheckBox();
    private CheckBox select2 = new CheckBox();
    private CheckBox select3 = new CheckBox();
    private CheckBox select4 = new CheckBox();
    private CheckBox select5 = new CheckBox();
    private CheckBox select6 = new CheckBox();
    private CheckBox select7 = new CheckBox();
    private CheckBox select8 = new CheckBox();
    private CheckBox select9 = new CheckBox();
    private CheckBox select10 = new CheckBox();
    private CheckBox select11 = new CheckBox();
    private CheckBox select12 = new CheckBox();
    private CheckBox select13 = new CheckBox();
    private CheckBox select14 = new CheckBox();
    private CheckBox select15 = new CheckBox();
    private CheckBox select16 = new CheckBox();
    private CheckBox select17 = new CheckBox();
    private CheckBox select18 = new CheckBox();
    private CheckBox select19 = new CheckBox();
    private CheckBox select20 = new CheckBox();
    private CheckBox select21 = new CheckBox();
    private CheckBox select22 = new CheckBox();
    private CheckBox select23 = new CheckBox();
    private CheckBox select24 = new CheckBox();
    private CheckBox select25 = new CheckBox();
    private CheckBox select26 = new CheckBox();
    private CheckBox select27 = new CheckBox();
    private CheckBox select28 = new CheckBox();
    private CheckBox select29 = new CheckBox();
    private CheckBox select30 = new CheckBox();
    private CheckBox select31 = new CheckBox();
    int songay = 0;

    public String getDem() {
        return String.valueOf(dem) + " / " + String.valueOf(songay);
    }

    public void setDem(Integer dem, int sn) {
        songay = sn;
        this.dem = dem;
    }

    public ChamCongModel() {
        setIdSelect();
        setDs();
    }

    public void napDiLam(Integer thang, Integer nam) {
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT day(Ngay) FROM [MNHP].[dbo].[CongNV]\n" +
                    "where idCBNV = ? and diLam = 1 and year(Ngay) = ? and month(Ngay) = ?";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            stmt.setString(1, id);
            stmt.setInt(2, nam);
            stmt.setInt(3, thang);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                for (CheckBox cc : ds) {
                    if (cc.getId().equals(String.valueOf(rs.getInt(1)))) {
                        cc.setSelected(true);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setIdSelect() {
        select1.setId("1");
        select2.setId("2");
        select3.setId("3");
        select4.setId("4");
        select5.setId("5");
        select6.setId("6");
        select7.setId("7");
        select8.setId("8");
        select9.setId("9");
        select10.setId("10");
        select11.setId("11");
        select12.setId("12");
        select13.setId("13");
        select14.setId("14");
        select15.setId("15");
        select16.setId("16");
        select17.setId("17");
        select18.setId("18");
        select19.setId("19");
        select20.setId("20");
        select21.setId("21");
        select22.setId("22");
        select23.setId("23");
        select24.setId("24");
        select25.setId("25");
        select26.setId("26");
        select27.setId("27");
        select28.setId("28");
        select29.setId("29");
        select30.setId("30");
        select31.setId("31");

    }

    public List<CheckBox> getDs() {
        return ds;
    }

    public void setDs() {
        select1.setDisable(true);
        select2.setDisable(true);
        select3.setDisable(true);
        select4.setDisable(true);
        select5.setDisable(true);
        select6.setDisable(true);
        select7.setDisable(true);
        select8.setDisable(true);
        select9.setDisable(true);
        select10.setDisable(true);
        select11.setDisable(true);
        select12.setDisable(true);
        select13.setDisable(true);
        select14.setDisable(true);
        select15.setDisable(true);
        select16.setDisable(true);
        select17.setDisable(true);
        select18.setDisable(true);
        select19.setDisable(true);
        select20.setDisable(true);
        select21.setDisable(true);
        select22.setDisable(true);
        select23.setDisable(true);
        select24.setDisable(true);
        select25.setDisable(true);
        select26.setDisable(true);
        select27.setDisable(true);
        select28.setDisable(true);
        select29.setDisable(true);
        select30.setDisable(true);
        select31.setDisable(true);

        ds.add(select1);
        ds.add(select2);
        ds.add(select3);
        ds.add(select4);
        ds.add(select5);
        ds.add(select6);
        ds.add(select7);
        ds.add(select8);
        ds.add(select9);
        ds.add(select10);
        ds.add(select11);
        ds.add(select12);
        ds.add(select13);
        ds.add(select14);
        ds.add(select15);
        ds.add(select16);
        ds.add(select17);
        ds.add(select18);
        ds.add(select19);
        ds.add(select20);
        ds.add(select21);
        ds.add(select22);
        ds.add(select23);
        ds.add(select24);
        ds.add(select25);
        ds.add(select26);
        ds.add(select27);
        ds.add(select28);
        ds.add(select29);
        ds.add(select30);
        ds.add(select31);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public CheckBox getSelect1() {
        return select1;
    }

    public CheckBox getSelect2() {
        return select2;
    }

    public CheckBox getSelect3() {
        return select3;
    }

    public CheckBox getSelect4() {
        return select4;
    }

    public CheckBox getSelect5() {
        return select5;
    }

    public CheckBox getSelect6() {
        return select6;
    }

    public CheckBox getSelect7() {
        return select7;
    }

    public CheckBox getSelect8() {
        return select8;
    }

    public CheckBox getSelect9() {
        return select9;
    }

    public CheckBox getSelect10() {
        return select10;
    }

    public CheckBox getSelect11() {
        return select11;
    }

    public CheckBox getSelect12() {
        return select12;
    }

    public CheckBox getSelect13() {
        return select13;
    }

    public CheckBox getSelect14() {
        return select14;
    }

    public CheckBox getSelect15() {
        return select15;
    }

    public CheckBox getSelect16() {
        return select16;
    }

    public CheckBox getSelect17() {
        return select17;
    }

    public CheckBox getSelect18() {
        return select18;
    }

    public CheckBox getSelect19() {
        return select19;
    }

    public CheckBox getSelect20() {
        return select20;
    }

    public CheckBox getSelect21() {
        return select21;
    }

    public CheckBox getSelect22() {
        return select22;
    }

    public CheckBox getSelect23() {
        return select23;
    }

    public CheckBox getSelect24() {
        return select24;
    }

    public CheckBox getSelect25() {
        return select25;
    }

    public CheckBox getSelect26() {
        return select26;
    }

    public CheckBox getSelect27() {
        return select27;
    }

    public CheckBox getSelect28() {
        return select28;
    }

    public CheckBox getSelect29() {
        return select29;
    }

    public CheckBox getSelect30() {
        return select30;
    }

    public CheckBox getSelect31() {
        return select31;
    }

    public void setSelect1(boolean selected) {
        this.select1.setSelected(selected);
    }

    public void setSelect2(boolean selected) {
        this.select2.setSelected(selected);
    }

    public void setSelect3(boolean selected) {
        this.select3.setSelected(selected);
    }

    public void setSelect4(boolean selected) {
        this.select4.setSelected(selected);
    }

    public void setSelect5(boolean selected) {
        this.select5.setSelected(selected);
    }

    public void setSelect6(boolean selected) {
        this.select6.setSelected(selected);
    }

    public void setSelect7(boolean selected) {
        this.select7.setSelected(selected);
    }

    public void setSelect8(boolean selected) {
        this.select8.setSelected(selected);
    }

    public void setSelect9(boolean selected) {
        this.select9.setSelected(selected);
    }

    public void setSelect10(boolean selected) {
        this.select10.setSelected(selected);
    }

    public void setSelect11(boolean selected) {
        this.select11.setSelected(selected);
    }

    public void setSelect12(boolean selected) {
        this.select12.setSelected(selected);
    }

    public void setSelect13(boolean selected) {
        this.select13.setSelected(selected);
    }

    public void setSelect14(boolean selected) {
        this.select14.setSelected(selected);
    }

    public void setSelect15(boolean selected) {
        this.select15.setSelected(selected);
    }

    public void setSelect16(boolean selected) {
        this.select16.setSelected(selected);
    }

    public void setSelect17(boolean selected) {
        this.select17.setSelected(selected);
    }

    public void setSelect18(boolean selected) {
        this.select18.setSelected(selected);
    }

    public void setSelect19(boolean selected) {
        this.select19.setSelected(selected);
    }

    public void setSelect20(boolean selected) {
        this.select20.setSelected(selected);
    }

    public void setSelect21(boolean selected) {
        this.select21.setSelected(selected);
    }

    public void setSelect22(boolean selected) {
        this.select22.setSelected(selected);
    }

    public void setSelect23(boolean selected) {
        this.select23.setSelected(selected);
    }

    public void setSelect24(boolean selected) {
        this.select24.setSelected(selected);
    }

    public void setSelect25(boolean selected) {
        this.select25.setSelected(selected);
    }

    public void setSelect26(boolean selected) {
        this.select26.setSelected(selected);
    }

    public void setSelect27(boolean selected) {
        this.select27.setSelected(selected);
    }

    public void setSelect28(boolean selected) {
        this.select28.setSelected(selected);
    }

    public void setSelect29(boolean selected) {
        this.select29.setSelected(selected);
    }

    public void setSelect30(boolean selected) {
        this.select30.setSelected(selected);
    }

    public void setSelect31(boolean selected) {
        this.select31.setSelected(selected);
    }

}
