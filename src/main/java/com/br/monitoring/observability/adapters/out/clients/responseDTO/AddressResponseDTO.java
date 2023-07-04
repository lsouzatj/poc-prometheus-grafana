package com.br.monitoring.observability.adapters.out.clients.responseDTO;

import lombok.Data;

@Data
public class AddressResponseDTO {
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
