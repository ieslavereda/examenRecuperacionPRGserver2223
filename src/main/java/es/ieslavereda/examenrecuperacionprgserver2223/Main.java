package es.ieslavereda.examenrecuperacionprgserver2223;

import es.ieslavereda.examenrecuperacionprgserver2223.model.Factura;
import es.ieslavereda.examenrecuperacionprgserver2223.model.LineaFactura;
import es.ieslavereda.examenrecuperacionprgserver2223.repository.FacturaRepository;
import es.ieslavereda.examenrecuperacionprgserver2223.repository.LineaFacturaRepository;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        FacturaRepository fr = new FacturaRepository();
        LineaFacturaRepository lf = new LineaFacturaRepository();
        Factura factura = new Factura();
        factura.setClienteId(1);

        try {
            System.out.println(fr.addFactura(factura));
            factura.setFechaFactura(Date.valueOf("2023-06-04"));
            System.out.println(fr.updateFactura(factura));
            LineaFactura lineaFactura = new LineaFactura(factura.getId(), 0,3,5,0,0);
            lf.addLineaFactura(lineaFactura);
            lineaFactura.setArticuloId(4);
            lineaFactura.setUnidades(5);
            lf.addLineaFactura(lineaFactura);
            System.out.println(lf.getLineaFacturaById(factura.getId()));
            System.out.println(fr.deleteFactura(factura.getId()));
            System.out.println(fr.getAllFacturas());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
