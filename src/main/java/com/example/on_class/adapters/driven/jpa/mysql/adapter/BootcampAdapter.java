package com.example.on_class.adapters.driven.jpa.mysql.adapter;

import com.example.on_class.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.example.on_class.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.on_class.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.example.on_class.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.example.on_class.domain.model.Bootcamp;
import com.example.on_class.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class BootcampAdapter implements IBootcampPersistencePort {
    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;
    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        bootcampRepository.save(bootcampEntityMapper.toEntity(bootcamp));
    }

    @Override
    public List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean orderFlag, boolean ascendingFlag) {
        List<BootcampEntity> bootcamps;
        Sort sort;
        Pageable pagination;
        if (orderFlag) {
            sort = ascendingFlag
                    ? Sort.by(Sort.Direction.ASC, "name")
                    : Sort.by(Sort.Direction.DESC, "name");
            pagination = PageRequest.of(page, size, sort);
            bootcamps = bootcampRepository.findAll(pagination).getContent();
        } else {
            pagination = PageRequest.of(page, size);
            bootcamps = bootcampRepository.findAllOrderByCapacities(pagination, ascendingFlag).getContent();
        }

        if (bootcamps.isEmpty()) {
            throw new NoDataFoundException();
        }
        return bootcampEntityMapper.toModelList(bootcamps);
    }
}
