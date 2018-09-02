package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.dto.ExtensionDTO;
import com.telerik.extensionrepository.model.Extension;

import java.util.List;

public interface RestExtensionService {

    List<ExtensionDTO> getAllApproved();

    List<ExtensionDTO> getAll();

    ExtensionDTO getExtensionByName(String name);

    List<ExtensionDTO> convertExtListToDto(List<Extension> extensions);
}
