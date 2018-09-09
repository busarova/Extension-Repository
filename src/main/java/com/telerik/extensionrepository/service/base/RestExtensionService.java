package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.dto.ExtensionDTO;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.exceptions.RepositoryException;

import java.util.List;

public interface RestExtensionService {

    List<ExtensionDTO> getAllApproved() throws RepositoryException;

    List<ExtensionDTO> getAll() throws RepositoryException;

    ExtensionDTO getExtensionByName(String name);

    List<ExtensionDTO> convertExtListToDto(List<Extension> extensions);
}
