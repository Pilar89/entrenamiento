package co.com.sofkau.entrenamiento.curso.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.values.Directiz;

public class DirectrizCreada extends DomainEvent {
  private final Directiz directiz;

  public DirectrizCreada(Directiz directiz) {
    super("co.com.sofkau.entrenamiento.curso.events.DirectrizCreada");
    this.directiz = directiz;
  }

  public Directiz getDirectiz() {
    return directiz;
  }
}
