package es.ieslavereda.examenrecuperacionprgserver2223.model;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LineaFactura implements Serializable {

    private int facturaId;
    private int linea;
    private int articuloId;
    private double unidades;
    private double pvp;
    private double importeLinea;

}
