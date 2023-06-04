package es.ieslavereda.examenrecuperacionprgserver2223.service;

import es.ieslavereda.examenrecuperacionprgserver2223.model.Factura;
import es.ieslavereda.examenrecuperacionprgserver2223.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository repository;

    public Factura addFactura(Factura factura) throws SQLException{
        return repository.addFactura(factura);
    }
    public Factura deleteFactura(int id) throws SQLException{
        return repository.deleteFactura(id);
    }
    public Factura updateFactura(Factura factura) throws SQLException{
        return repository.updateFactura(factura);
    }
    public List<Factura> getAllFacturas() throws SQLException{
        return repository.getAllFacturas();
    }

}
