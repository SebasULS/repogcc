package edu.pe.demo.service;

import edu.pe.demo.Jugador;
import edu.pe.demo.repository.JugadorRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public Jugador registrar(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public List<Jugador> listar() {
        return jugadorRepository.findAll();
    }

    public Optional<Jugador> buscarPorId(Long id) {
        return jugadorRepository.findById(id);
    }

    public Jugador actualizar(Long id, Jugador datos) {

        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Jugador no encontrado"));

        jugador.setNombre(datos.getNombre());
        jugador.setApellido(datos.getApellido());
        jugador.setEdad(datos.getEdad());
        jugador.setPosicion(datos.getPosicion());
        jugador.setEquipo(datos.getEquipo());
        jugador.setNacionalidad(datos.getNacionalidad());

        return jugadorRepository.save(jugador);
    }

    public void eliminar(Long id) {
        jugadorRepository.deleteById(id);
    }
}
