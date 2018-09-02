package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.dto.ExtensionDTO;

import java.util.List;

public interface RestExtensionService {

    List<ExtensionDTO> getAllApproved();
}
