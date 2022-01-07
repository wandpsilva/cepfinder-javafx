package gui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String status;
    private String ok;
    private String code;
    private String state;
    private String city;
    private String district;
    private String address;
    private String statusText;

    @Override
    public String toString() {
        return  "Estado = '" + state + '\'' +
                ", Cidade = '" + city + '\'' +
                ", Distrito = '" + district + '\'' +
                ", Rua = '" + address + '\'';
    }
}