package pl.app.collectorsappbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.app.collectorsappbackend.mapper.BaseMapper;

import java.util.List;

@RequiredArgsConstructor
abstract class BaseService<E, D> {

    protected abstract JpaRepository<E, Long> getRepository();

    protected abstract BaseMapper<E, D> getMapper();

    public List<D> getAll() {
        return getDTOList();
    }

    public D save(D dto) {
        var entity = getMapper().toEntity(dto);
        return getMapper().toDTO(getRepository().save(entity));
    }

    private List<D> getDTOList() {
        return getRepository().findAll().stream().map(getMapper()::toDTO).toList();
    }
}
