package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.dto.ExtensionDTO;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.RestExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestExtensionServiceImpl implements RestExtensionService {

    private ExtensionRepository extensionRepository;

    @Autowired
    public RestExtensionServiceImpl(ExtensionRepository extensionRepository){
        this.extensionRepository = extensionRepository;
    }

    @Override
    public List<ExtensionDTO> getAllApproved() {

        return convertExtListToDto(extensionRepository.getAllApproved());
    }

    @Override
    public List<ExtensionDTO> getAll() {
        return convertExtListToDto(extensionRepository.getAllExtensions());
    }

    @Override
    public ExtensionDTO getExtensionByName(String name) {
        return new ExtensionDTO(extensionRepository.getExtByName(name));
    }

    @Override
    public List<ExtensionDTO> convertExtListToDto(List<Extension> extensions) {

        List<ExtensionDTO> allDto = new ArrayList<>();

        for (Extension ext:
                extensions) {

            allDto.add(new ExtensionDTO(ext));
        }

        return allDto;
    }


}
