package application.Exhibition;

import application.Silo.Silo;

import java.util.List;
import java.util.Optional;

public interface ExhibitionRepository {
    void add(Exhibition exhibition);
    void remove(Exhibition exhibition);
    void update(Exhibition oldExhibition, Exhibition newExhibition) throws Exception;
    Optional<Exhibition> getByName(String name);
    List<Exhibition> getAllExhibition();
}
