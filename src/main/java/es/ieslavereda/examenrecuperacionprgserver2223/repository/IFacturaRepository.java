package es.ieslavereda.examenrecuperacionprgserver2223.repository;

import es.ieslavereda.examenrecuperacionprgserver2223.model.Factura;

import java.sql.SQLException;
import java.util.List;

public interface IFacturaRepository {
    Factura addFactura(Factura factura) throws SQLException;
    Factura deleteFactura(int id) throws SQLException;
    Factura updateFactura(Factura factura) throws SQLException;
    List<Factura> getAllFacturas() throws SQLException;
}
