package application.Mediator;

public interface RequestHandler<TRequest, TResponse> {
    TResponse handle(TRequest request) throws Exception;
}