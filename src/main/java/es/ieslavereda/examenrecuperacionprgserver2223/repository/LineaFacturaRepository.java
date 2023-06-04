package es.ieslavereda.examenrecuperacionprgserver2223.repository;

import es.ieslavereda.examenrecuperacionprgserver2223.model.Factura;
import es.ieslavereda.examenrecuperacionprgserver2223.model.LineaFactura;
import es.ieslavereda.examenrecuperacionprgserver2223.model.MyDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineaFacturaRepository {

    public void addLineaFactura(LineaFactura lfactura) throws SQLException {
        String sql = " { call insertar_linea(?,?,?) } ";

        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            CallableStatement callableStatement = connection.prepareCall(sql)){

            callableStatement.setInt(1, lfactura.getFacturaId());
            callableStatement.setInt(2,lfactura.getArticuloId());
            callableStatement.setInt(3,(int) lfactura.getUnidades());
            callableStatement.execute();

            List<LineaFactura> lineas = getLineaFacturaById(lfactura.getFacturaId());
            double suma = lineas.stream().mapToDouble(LineaFactura::getImporteLinea).sum();
            FacturaRepository fr = new FacturaRepository();
            Factura factura = fr.getFacturaById(lfactura.getFacturaId());
            factura.setImporte(suma);
            fr.updateFactura(factura);
        }

    }

    public List<LineaFactura> getLineaFacturaById(int facturaId) throws SQLException {
        String sql = " select * from lin_fac where facturaId = ? ";
        List<LineaFactura> lineas = new ArrayList<>();
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,facturaId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lineas.add(LineaFactura.builder().facturaId(facturaId).linea(rs.getInt(2)).
                        articuloId(rs.getInt(3)).unidades(rs.getDouble(4)).
                        pvp(rs.getDouble(5)).importeLinea(rs.getDouble(6)).build());
            }

        }
        return lineas;
    }

}
