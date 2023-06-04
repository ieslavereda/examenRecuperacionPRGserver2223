package es.ieslavereda.examenrecuperacionprgserver2223.model;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Factura implements Serializable {
    private int id;
    private Date fechaFactura;
    private double importe;
    private int clienteId;
}
