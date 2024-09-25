package matcha.banking.be.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SearchPaymentDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String category;

    private String forUser;

}
