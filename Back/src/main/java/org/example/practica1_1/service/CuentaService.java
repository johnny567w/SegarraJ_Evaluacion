package org.example.practica1_1.service;

import org.example.practica1_1.repo.ClienteRepository;
import org.example.practica1_1.repo.CuentaRepository;
import org.example.practica1_1.repo.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TransaccionRepository transaccionRepository;

    public List<Cuenta> listar() {
        return cuentaRepository.findAll();
    }

    public Cuenta obtenerPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }


    public void eliminarCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

    public void depositar(Long id, double monto) {
        Cuenta cuenta = obtenerPorId(id);
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        cuentaRepository.save(cuenta);
        registrarTransaccion("DEPOSITO", monto, null, cuenta);
    }

    public void retirar(Long id, double monto) {
        Cuenta cuenta = obtenerPorId(id);
        if (cuenta.getSaldo() < monto) {
            throw new RuntimeException("Saldo insuficiente");
        }
        cuenta.setSaldo(cuenta.getSaldo() - monto);
        cuentaRepository.save(cuenta);
        registrarTransaccion("RETIRO", monto, cuenta, null);
    }

    public void transferir(Long origenId, Long destinoId, double monto) {
        Cuenta origen = obtenerPorId(origenId);
        Cuenta destino = obtenerPorId(destinoId);

        if (origen.getSaldo() < monto) {
            throw new RuntimeException("Saldo insuficiente para transferir");
        }

        origen.setSaldo(origen.getSaldo() - monto);
        destino.setSaldo(destino.getSaldo() + monto);
        cuentaRepository.saveAll(List.of(origen, destino));

        registrarTransaccion("TRANSFERENCIA", monto, origen, destino);
    }

    private void registrarTransaccion(String tipo, double monto, Cuenta origen, Cuenta destino) {
        Transaccion tx = new Transaccion();
        tx.setTipo(tipo);
        tx.setMonto(monto);
        tx.setFecha(LocalDateTime.now());
        tx.setCuentaOrigen(origen);
        tx.setCuentaDestino(destino);
        transaccionRepository.save(tx);
    }
    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

}