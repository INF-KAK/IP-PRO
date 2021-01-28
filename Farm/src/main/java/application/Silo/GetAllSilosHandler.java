package application.Silo;

import application.Mediator.RequestHandler;

import java.util.List;

public class GetAllSilosHandler implements RequestHandler<GetAllSilosQuery, List<Silo>> {
    private SiloRepository repository;

    public GetAllSilosHandler(SiloRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Silo> handle(GetAllSilosQuery getAllSilosQuery) {
        return repository.getAllSilos();
    }
}

