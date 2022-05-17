package co.com.sofkau.entrenamiento.curso.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.entrenamiento.curso.values.*;

public class AgregarDirectriz extends Command {
  private final CursoId cursoId;
  private final Directiz directiz;

  public AgregarDirectriz(CursoId cursoId, Directiz directiz) {
    this.cursoId = cursoId;
    this.directiz = directiz;
  }

  public CursoId getCursoId() {
    return cursoId;
  }

  public Directiz getDirectiz() {
    return directiz;
  }
}
