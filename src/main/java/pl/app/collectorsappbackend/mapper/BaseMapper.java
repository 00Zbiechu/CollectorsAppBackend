package pl.app.collectorsappbackend.mapper;

public interface BaseMapper<E, D> {

    E toEntity(D dto);

    D toDTO(E entity);
}
