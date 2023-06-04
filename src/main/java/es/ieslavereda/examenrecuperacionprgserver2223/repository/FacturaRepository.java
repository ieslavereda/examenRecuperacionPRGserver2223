package es.ieslavereda.examenrecuperacionprgserver2223.repository;

import es.ieslavereda.examenrecuperacionprgserver2223.model.Factura;
import es.ieslavereda.examenrecuperacionprgserver2223.model.MyDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FacturaRepository implements IFacturaRepository{

    public Factura addFactura(Factura factura) throws SQLException {
        String sql = " { call crear_factura(?,?) } ";

        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            CallableStatement callableStatement = connection.prepareCall(sql)){

            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2,factura.getClienteId());
            callableStatement.execute();
            factura.setId(callableStatement.getInt(1));
       }
        return factura;
    }

    public Factura deleteFactura(int id) throws SQLException {
        String sql = " { call eliminar_factura(?) } ";
        Factura factura = getFacturaById(id);
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            CallableStatement callableStatement = connection.prepareCall(sql)){
            callableStatement.setInt(1,id);
            callableStatement.execute();
        }
        return factura;
    }

    @Override
    public Factura updateFactura(Factura factura) throws SQLException {
        String sql = " update factura set fechaFactura = ?, importe = ?, clienteId = ? where id = ? ";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDate(1,factura.getFechaFactura());
            ps.setDouble(2,factura.getImporte());
            ps.setInt(3,factura.getClienteId());
            ps.setInt(4,factura.getId());
            ps.executeUpdate();
        }
        return factura;
    }

    @Override
    public List<Factura> getAllFacturas() throws SQLException {
        List<Factura> facturas = new ArrayList<>();
        String sql = " select * from factura";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery(sql)){
            while(rs.next()){
                facturas.add(Factura.builder().id(rs.getInt(1)).fechaFactura(rs.getDate(2)).importe(rs.getDouble(3)).clienteId(rs.getInt(4)).build());
            }
        }
        return facturas;
    }

    public Factura getFacturaById(int id) throws SQLException{
        String sql = " select * from factura where id = ? ";
        Factura factura = null;
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                factura = Factura.builder().id(id).fechaFactura(rs.getDate(2)).importe(rs.getDouble(3)).clienteId(rs.getInt(4)).build();
            }
        }
        return factura;
    }
}
