package es.ieslavereda.examenrecuperacionprgserver2223.controller;

import es.ieslavereda.examenrecuperacionprgserver2223.model.Factura;
import es.ieslavereda.examenrecuperacionprgserver2223.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class FacturaController {
    @Autowired
    private FacturaService service;

    @GetMapping("factura/")
    public ResponseEntity<?>  getAllFacturas(){
        try{
            return new ResponseEntity<>(service.getAllFacturas(), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("factura/{id}")
    public ResponseEntity<?> deleteFactura(@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(service.deleteFactura(id), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("factura/")
    public ResponseEntity<?>  updateFactura(@RequestBody Factura factura){
        try{
            return new ResponseEntity<>(service.updateFactura(factura), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("factura/")
    public ResponseEntity<?>  addFactura(@RequestBody Factura factura){
        try{
            return new ResponseEntity<>(service.addFactura(factura), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
