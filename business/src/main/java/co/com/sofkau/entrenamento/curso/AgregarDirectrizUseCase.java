package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofkau.entrenamiento.curso.Curso;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectriz;
import co.com.sofkau.entrenamiento.curso.values.Directiz;
import co.com.sofkau.entrenamiento.curso.values.MentoriaId;

public class AgregarDirectrizUseCase extends UseCase<RequestCommand<AgregarDirectriz>, ResponseEvents> {
  @Override
  public void executeUseCase(RequestCommand<AgregarDirectriz> requestCommand) {
    var command = requestCommand.getCommand();
    var curso = Curso.from(
      command.getCursoId(), repository().getEventsBy(command.getCursoId().value())
    );

    var mentoriaId = new MentoriaId();
    var directriz = new Directiz("directiz");
    curso.agregarDirectrizDeMentoria(mentoriaId, directriz);

    emit().onResponse(new ResponseEvents(curso.getUncommittedChanges()));
  }
}
