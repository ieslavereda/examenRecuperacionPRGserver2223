package es.ieslavereda.examenrecuperacionprgserver2223;

import es.ieslavereda.examenrecuperacionprgserver2223.model.Factura;
import es.ieslavereda.examenrecuperacionprgserver2223.model.LineaFactura;
import es.ieslavereda.examenrecuperacionprgserver2223.model.MyDataSource;
import es.ieslavereda.examenrecuperacionprgserver2223.repository.FacturaRepository;
import es.ieslavereda.examenrecuperacionprgserver2223.repository.LineaFacturaRepository;

import java.io.*;
import java.sql.*;
import java.util.List;

public class LoadSaveTest {


    public static void main(String[] args) {

        try {
            saveFacturas("facturas.txt");
            saveTotalCliente("total.csv");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFacturas(String datafile) throws IOException, SQLException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datafile))) {
            FacturaRepository fr = new FacturaRepository();
            LineaFacturaRepository lfr = new LineaFacturaRepository();
            List<Factura> facturas = fr.getAllFacturas();
            for (Factura factura : facturas) {
                bw.write(factura.toString());
                bw.newLine();
                List<LineaFactura> lineas = lfr.getLineaFacturaById(factura.getId());
                for (LineaFactura lf : lineas) {
                    bw.write(lf.toString());
                    bw.newLine();
                }
            }
        }
    }

    public static void saveTotalCliente(String filename) throws SQLException, IOException {
        String sql = "SELECT clienteId,sum(importe) FROM bbddjava.factura group by clienteId;";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
             Connection connection = MyDataSource.getMyDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    String cadena = rs.getInt(1)+","+rs.getDouble(2);
                    bw.write(cadena);
                    bw.newLine();
                }
        }
    }
}
