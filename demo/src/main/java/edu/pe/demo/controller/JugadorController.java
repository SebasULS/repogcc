package edu.pe.demo.controller;

import edu.pe.demo.Jugador;
import edu.pe.demo.service.JugadorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @PostMapping
    public ResponseEntity<Jugador> registrar(@RequestBody Jugador jugador) {
        Jugador nuevo = jugadorService.registrar(jugador);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping
    public ResponseEntity<List<Jugador>> listar() {
        return ResponseEntity.ok(jugadorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> buscar(@PathVariable Long id) {
        return jugadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> actualizar(
            @PathVariable Long id,
            @RequestBody Jugador datos) {

        if (jugadorService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(jugadorService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (jugadorService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        jugadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
