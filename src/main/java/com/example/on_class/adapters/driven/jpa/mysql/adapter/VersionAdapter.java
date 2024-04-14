package com.example.on_class.adapters.driven.jpa.mysql.adapter;

import com.example.on_class.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.example.on_class.adapters.driven.jpa.mysql.exception.IncorrectParamException;
import com.example.on_class.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.example.on_class.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.example.on_class.domain.model.Version;
import com.example.on_class.domain.spi.IVersionPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class VersionAdapter implements IVersionPersistencePort {
    private final IVersionRepository versionRepository;
    private final IVersionEntityMapper versionEntityMapper;
    @Override
    public void saveVersion(Version version) {
        versionRepository.save(versionEntityMapper.toEntity(version));
    }

    @Override
    public List<Version> getAllVersions(String bootcamp, Integer page, Integer size, Integer orderFlag, boolean ascendingFlag) {
        List<VersionEntity> versions;
        Sort sort;
        Pageable pagination;
        sort = switch (orderFlag) {
            case 0 -> ascendingFlag
                    ? Sort.by(Sort.Direction.ASC, "bootcamp.name")
                    : Sort.by(Sort.Direction.DESC, "bootcamp.name");
            case 1 -> ascendingFlag
                    ? Sort.by(Sort.Direction.ASC, "initialDate")
                    : Sort.by(Sort.Direction.DESC, "initialDate");
            case 2 -> ascendingFlag
                    ? Sort.by(Sort.Direction.ASC, "quota")
                    : Sort.by(Sort.Direction.DESC, "quota");
            default -> throw new IncorrectParamException();
        };
        pagination = PageRequest.of(page, size, sort);
        versions = bootcamp != null
                ? versionRepository.findAllByBootcampName(bootcamp, pagination).getContent()
                : versionRepository.findAll(pagination).getContent();
        return versionEntityMapper.toModelList(versions);
    }
}
