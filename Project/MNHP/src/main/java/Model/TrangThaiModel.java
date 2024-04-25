package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TrangThaiModel {
    private String id;
    private String TrangThai;

    @Override
    public String toString() {
        return TrangThai;
    }
}
