package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectriz;
import co.com.sofkau.entrenamiento.curso.events.CursoCreado;
import co.com.sofkau.entrenamiento.curso.events.DirectrizAgregadaAMentoria;
import co.com.sofkau.entrenamiento.curso.events.MentoriaCreada;
import co.com.sofkau.entrenamiento.curso.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AgregarDirectrizUseCaseTest {

  @InjectMocks
  private AgregarDirectrizUseCase useCaseAgregarDirectriz;

  @InjectMocks
  private AgregarMentoriaUseCase useCaseAgregarMentoria;

  @Mock
  private DomainEventRepository repository;

  @Test
  void agregarUnaDirectrizHappyPass() {
    when(repository.getEventsBy("ddddd")).thenReturn(history());
    useCaseAgregarDirectriz.addRepository(repository);
    ;

    CursoId coursoId = CursoId.of("ddddd");
    var directiz = new Directiz("eeeeee");
    var commandAgregarDirectriz = new AgregarDirectriz(coursoId, directiz);

    var eventsAgregarDirectriz = UseCaseHandler.getInstance()
      .setIdentifyExecutor(commandAgregarDirectriz.getCursoId().value())
      .syncExecutor(useCaseAgregarDirectriz, new RequestCommand<>(commandAgregarDirectriz))
      .orElseThrow()
      .getDomainEvents();

    var event = (DirectrizAgregadaAMentoria) eventsAgregarDirectriz.get(0);
    Assertions.assertNotNull(event);
  }

  private List<DomainEvent> history() {
    Nombre nombre = new Nombre("DDD");
    Descripcion descripcion = new Descripcion("Curso complementario para el training");
    var event = new CursoCreado(
      nombre,
      descripcion
    );
    event.setAggregateRootId("xxxxx");

    Nombre nombreMentoria = new Nombre("Aprendiendo de casos de usos");
    Fecha fecha = new Fecha(LocalDateTime.now(), LocalDate.now());
    MentoriaId mentoriaId = new MentoriaId("mentoriaId");
    var eventMentoria = new MentoriaCreada(mentoriaId, nombreMentoria, fecha);

    return List.of(event, eventMentoria);
  }

}
